package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import model.*;

public class SequentialDatapath {
	// Program Counter
	public BigInteger pc = new BigInteger("0000000000000000");

	// Internal Registers
	public InternalRegisters if_id;
	public InternalRegisters id_ex;
	public InternalRegisters ex_mem;
	public InternalRegisters mem_wb;

	// Registers and Memory
	public InstructionMemory instructionMemory;
	public Registers registers;
	public DataMemory dataMemory;

	// Operators
	public ALU alu;
	public AdderALU adderAlu;
	public Multiplexer multiplexer;
	public ZeroCondition zeroCondition;
	public SignExtend signExtend;

	// Auxillary variables
	public boolean keepRunning;
	public int cycles;

	// Data Dependency
	public HashMap<BigInteger, Double> dependencyDeclarationR;
	public HashMap<BigInteger, Double> dependencyDeclarationF;

	// Pipeline Mapping
	public PipelineMapManager pipelineMapManager;

	public SequentialDatapath() {
		pipelineMapManager = pipelineMapManager.getInstance();
		instructionMemory = new InstructionMemory();
		registers = new Registers();
		registers.Initialize();
		dataMemory = new DataMemory();

		alu = new ALU();
		adderAlu = new AdderALU();
		multiplexer = new Multiplexer();
		zeroCondition = new ZeroCondition();
		signExtend = new SignExtend();

		if_id = new InternalRegisters();
		id_ex = new InternalRegisters();
		ex_mem = new InternalRegisters();
		mem_wb = new InternalRegisters();

		dependencyDeclarationR = new HashMap<>();
		dependencyDeclarationF = new HashMap<>();

		runQueue = new LinkedList<>();
		runQueue.add(PipelineMapManager.IF_STAGE);
	}

	public void loadInstructions(ArrayList<MIPSInstruction> mipsInst) {
		for (MIPSInstruction inst : mipsInst) {
			instructionMemory.setInstructionAddress(inst.address, inst);
		}
	}

	public int run() {
		pc = BigInteger.ZERO;

		cycles = 0;
		keepRunning = true;
		// int rem = 1;
		while (runQueue.size() > 0) {
			runOneCycle();
		}

		return 0;
	}

	public LinkedList<Integer> runQueue;

	public int runOneCycle() {
		LinkedList<Integer> nextRunQueue = new LinkedList<>();

		System.out.println("Cycle #" + (cycles + 1));
		for (Integer stg : runQueue) {
			System.out.print(PipelineMapManager.getStageName(stg) + "\t");
		}
		System.out.println();

		while (runQueue.size() > 0) {
			int stage = runQueue.remove();

			switch (stage) {
			case PipelineMapManager.IF_STAGE:
				IF();
				nextRunQueue.add(PipelineMapManager.ID_STAGE);
				nextRunQueue.add(PipelineMapManager.IF_STAGE);
				break;
			case PipelineMapManager.ID_STAGE:
				int action = ID();
				if (action == PipelineMapManager.ST_STAGE) {
					nextRunQueue.add(PipelineMapManager.ID_STAGE);
					runQueue.clear();
				} else
					nextRunQueue.add(PipelineMapManager.EX_STAGE);
				break;
			case PipelineMapManager.EX_STAGE:
				EX();
				nextRunQueue.add(PipelineMapManager.MEM_STAGE);
				break;
			case PipelineMapManager.MEM_STAGE:
				MEM();
				nextRunQueue.add(PipelineMapManager.WB_STAGE);
				break;
			case PipelineMapManager.WB_STAGE:
				IF();
				break;
			case PipelineMapManager.ST_STAGE:
				ST();
				break;
			case PipelineMapManager.BR_STAGE:
				BR();
				break;
			}
		}
		// if (mem_wb.IR != null)
		// WB();
		// if (ex_mem.IR != null)
		// MEM();
		// if (id_ex.IR != null)
		// if (stallAt == null || stallAt.salt != id_ex.IR.salt) {
		// System.out.println("runOneCycle: "+ stallAt);
		// if (stallAt != null )System.out.println("runOneCycle: "+ stallAt.salt + " " + id_ex.IR.salt);
		// EX();
		//
		// }
		// if (if_id.IR != null)
		// ID();
		// IF();

		cycles++;

		runQueue = nextRunQueue;

		return runQueue.size();
	}

	public int ST() {
		return -1;
	}

	public int BR() {
		return -1;
	}

	public int IF() {

		if (instructionMemory.lastAddress.compareTo(pc) == 0) {
			if_id.IR = new MIPSInstruction();
			if_id.IR.isLastInstruction = true;
		} else {
			if_id.IR = instructionMemory.getInstructionAddress(pc);

			if (ex_mem.IR != null && ex_mem.IR.getInstructionType() == MIPSInstruction.BRANCH && ex_mem.Cond) {
				if_id.NPC = ex_mem.ALUOutput;
				pc = if_id.NPC;
			} else {
				if_id.NPC = adderAlu.add(pc, 4);
				pc = if_id.NPC;
			}

			pipelineMapManager.addEntry(cycles, PipelineMapManager.IF_STAGE, if_id.IR);
		}
		return 0;
	}

	public int branchCount = 2;

	public int ID() {
		id_ex.IR = if_id.IR;

		System.out.println("ID: " + cycles + " " + id_ex.IR);

		//if (!id_ex.IR.isLastInstruction) {
			id_ex.NPC = if_id.NPC;

			// Loading & possible stall

			BigInteger AIndex, BIndex;

			if (Integer.parseInt(if_id.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DSLL) {
				AIndex = if_id.IR.getBinarySegment(11, 15);
				BIndex = if_id.IR.getBinarySegment(16, 20);
			} else {
				AIndex = if_id.IR.getA();
				BIndex = if_id.IR.getB();
			}

			// Check if need to stall

			if (id_ex.IR.isFloatInst) {
				if (this.dependencyDeclarationF.containsKey(AIndex) && this.dependencyDeclarationF.containsKey(BIndex)) {
					return PipelineMapManager.ST_STAGE;
				} else {
					// stall = false;
					// stallAt = null;
				}
				id_ex.A = registers.getF(AIndex);
				id_ex.B = registers.getF(BIndex);
			} else {
				if (this.dependencyDeclarationR.containsKey(AIndex) && this.dependencyDeclarationR.containsKey(BIndex)) {
					// stall = true;
					// System.out.println("HELLLO");
					// stallAt = id_ex.IR;
					return PipelineMapManager.ST_STAGE;
				} else {
					// stall = false;
					// this.stallAt = null;
				}
				id_ex.A = registers.getR(AIndex);
				id_ex.B = registers.getR(BIndex);
			}

			id_ex.IMM = signExtend.getImmAndExtend(if_id.IR.getIMM());

			// DECLARING WRITE DEPENDENCY

			BigInteger target = BigInteger.valueOf(-1);
			boolean isFloat = id_ex.IR.isFloatInst;

			switch (id_ex.IR.getInstructionType()) {
			case MIPSInstruction.REGISTER_REGISTER:
				// TODO: check dependency for DMULT

				target = id_ex.IR.getBinarySegment(16, 20);
				break;
			case MIPSInstruction.REGISTER_IMMEDIATE:
				if (Integer.parseInt(id_ex.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DSLL)
					target = id_ex.IR.getBinarySegment(16, 20);
				else
					target = id_ex.IR.getB();
				break;
			// case MIPSInstruction.STORE:
			case MIPSInstruction.LOAD:
				target = id_ex.IR.getB();
				break;
			}

			if (isFloat) {
				this.dependencyDeclarationF.put(target, id_ex.IR.salt);
			} else
				this.dependencyDeclarationR.put(target, id_ex.IR.salt);

			pipelineMapManager.addEntry(cycles, PipelineMapManager.ID_STAGE, id_ex.IR);
		//}
		return 0;
	}

	public int EX() {
		ex_mem.IR = id_ex.IR;

		if (!ex_mem.IR.isLastInstruction) {
			ex_mem.B = id_ex.B;

			switch (id_ex.IR.getInstructionType()) {
			case MIPSInstruction.BRANCH:
				ex_mem.ALUOutput = id_ex.NPC.add(id_ex.IMM.multiply(BigInteger.valueOf(4)));
				if (branchCount > 0) {
					ex_mem.Cond = zeroCondition.check(id_ex.A);
					branchCount--;
				}

				System.out.println("ID STAGE: " + id_ex.IR + " " + id_ex.A + " " + id_ex.B + " " + id_ex.IMM);
				System.out.println("BRANCH: " + ex_mem.ALUOutput + " " + id_ex.A + " " + ex_mem.Cond);
				break;
			case MIPSInstruction.JUMP:
				ex_mem.ALUOutput = id_ex.IMM.multiply(BigInteger.valueOf(4));
				ex_mem.Cond = true;
				break;
			default:
				BigInteger param1 = id_ex.A;
				BigInteger param2 = multiplexer.select(id_ex.B, id_ex.IMM, id_ex.IR.getCondForMultiplexer());
				ex_mem.ALUOutput = alu.apply(param1, param2, id_ex.IR);
				ex_mem.Cond = false;
			}
			System.out.println(ex_mem.IR);
			pipelineMapManager.addEntry(cycles, PipelineMapManager.EX_STAGE, ex_mem.IR);
		}
		return 0;
	}

	public int MEM() {
		mem_wb.IR = ex_mem.IR;

		if (!mem_wb.IR.isLastInstruction) {
			mem_wb.ALUOutput = ex_mem.ALUOutput;

			// System.out.println("MEM: " + ex_mem.Cond);
			// pc = multiplexer.select(if_id.NPC, ex_mem.ALUOutput,
			// ex_mem.Cond);

			switch (ex_mem.IR.getInstructionType()) {
			case MIPSInstruction.LOAD:
				mem_wb.LMD = dataMemory.getDataFromMemory(ex_mem.ALUOutput);
				break;
			case MIPSInstruction.STORE:
				dataMemory.setDataToMemory(mem_wb.ALUOutput, ex_mem.B);
				break;
			}

			// System.out.println(mem_wb.IR);

			pipelineMapManager.addEntry(cycles, PipelineMapManager.MEM_STAGE, mem_wb.IR);
		}
		return 0;
	}

	public int WB() {
		// TODO: MEM/WB apparently writes IR back to Registers??
		// I wasn't sure what this meant so I'm leaving this out for now

		// This is a Multiplexer... kinda
		if (!mem_wb.IR.isLastInstruction) {

			BigInteger target = BigInteger.valueOf(-1);
			BigInteger value = BigInteger.ZERO;

			switch (mem_wb.IR.getInstructionType()) {
			case MIPSInstruction.REGISTER_REGISTER:
				if (Integer.parseInt(mem_wb.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DMULT) {
					// TODO: separate top from bottom
					registers.setHILO(mem_wb.ALUOutput);
				} else {
					target = mem_wb.IR.getBinarySegment(16, 20);
					value = mem_wb.ALUOutput;
				}
				break;
			case MIPSInstruction.REGISTER_IMMEDIATE:
				if (Integer.parseInt(mem_wb.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DSLL)
					target = mem_wb.IR.getBinarySegment(16, 20);
				else
					target = mem_wb.IR.getB();
				value = mem_wb.ALUOutput;
				break;
			case MIPSInstruction.LOAD:
				target = mem_wb.IR.getB();
				value = mem_wb.LMD;

				break;
			}

			if (!target.equals(BigInteger.valueOf(-1))) {
				if (mem_wb.IR.isFloatInst) {
					registers.setF(target, value);
					Double depIndex = this.dependencyDeclarationF.get(target);
					if (depIndex != null && depIndex.equals(mem_wb.IR.salt))
						this.dependencyDeclarationF.remove(target);
				} else {
					registers.setR(target, value);
					Double depIndex = this.dependencyDeclarationR.get(target);
					if (depIndex != null && depIndex.equals(mem_wb.IR.salt))
						this.dependencyDeclarationR.remove(target);
				}
			}

			// Clear dependency

			// System.out.println(mem_wb.IR);
			pipelineMapManager.addEntry(cycles, PipelineMapManager.WB_STAGE, mem_wb.IR);
		} else
			keepRunning = false;

		return 0;
	}

}
