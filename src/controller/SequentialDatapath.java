package controller;

import java.math.BigInteger;
import java.util.ArrayList;

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
		while (keepRunning) {
			runOneCycle();
		}

		return 0;
	}

	public int runOneCycle() {
		if (mem_wb.IR != null)
			WB();
		if (ex_mem.IR != null)
			MEM();
		if (id_ex.IR != null)
			EX();
		if (if_id.IR != null)
			ID();
		IF();

		cycles++;

		return 0;
	}

	public int IF() {
		if (instructionMemory.lastAddress.compareTo(pc) < 0) {
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

			System.out.println("IF STAGE: \n" + if_id.toString());

			pipelineMapManager.addEntry(cycles, PipelineMapManager.IF_STAGE, if_id.IR);
		}
		return 0;
	}

	public int ID() {
		id_ex.IR = if_id.IR;

		if (!id_ex.IR.isLastInstruction) {
			id_ex.NPC = if_id.NPC;

			// need to add special case when instruction is DSLL and floating

			// get from R register and get from F register
			if (Integer.parseInt(if_id.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DSLL) {
				id_ex.A = registers.getR(if_id.IR.getBinarySegment(11, 15));
				id_ex.B = registers.getR(if_id.IR.getBinarySegment(16, 20));
				System.out.println(if_id.IR.getOpcodeInBinary());
				System.out.println(if_id.IR);
				System.out.println(if_id.IR.getBinarySegment(11, 15));
				System.out.println(if_id.IR.getBinarySegment(16, 20));
			} else {
				id_ex.A = registers.getR(if_id.IR.getA());
				id_ex.B = registers.getR(if_id.IR.getB());
			}

			id_ex.IMM = signExtend.getImmAndExtend(if_id.IR.getIMM());

			System.out.println("ID STAGE: \n" + id_ex.toString());

			pipelineMapManager.addEntry(cycles, PipelineMapManager.ID_STAGE, id_ex.IR);
		}
		return 0;
	}

	public int EX() {
		ex_mem.IR = id_ex.IR;

		if (!ex_mem.IR.isLastInstruction) {
			ex_mem.B = id_ex.B;

			switch (id_ex.IR.getInstructionType()) {
			case MIPSInstruction.BRANCH:
				ex_mem.ALUOutput = id_ex.NPC.add(id_ex.IMM.multiply(BigInteger.valueOf(4)));
				ex_mem.Cond = zeroCondition.check(id_ex.A);
				break;
			case MIPSInstruction.JUMP:
				ex_mem.ALUOutput = id_ex.IMM.multiply(BigInteger.valueOf(4));
				ex_mem.Cond = true;
				break;
			default:
				// long param1 = multiplexer.select(id_ex.NPC, id_ex.A,
				// id_ex.IR);
				BigInteger param1 = id_ex.A;

				System.out.println(id_ex.IR.getCondForMultiplexer());

				BigInteger param2 = multiplexer.select(id_ex.B, id_ex.IMM, id_ex.IR.getCondForMultiplexer());

				System.out.println(id_ex.IR + " " + id_ex.B + " " + id_ex.IMM + " " + id_ex.IR.getCondForMultiplexer() + " " + id_ex.IR.getInstructionType());
				System.out.println("PARAM: " + param2);
				ex_mem.ALUOutput = alu.apply(param1, param2, id_ex.IR);
				ex_mem.Cond = false;
			}

			System.out.println("EX STAGE: \n" + ex_mem.toString());

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
				mem_wb.LMD = dataMemory.getDataFromMemory(ex_mem.B);
				break;
			}

			System.out.println("MEM STAGE: \n" + mem_wb.toString());
			pipelineMapManager.addEntry(cycles, PipelineMapManager.MEM_STAGE, mem_wb.IR);
		}
		return 0;
	}

	public int WB() {
		// TODO: MEM/WB apparently writes IR back to Registers??
		// I wasn't sure what this meant so I'm leaving this out for now

		// This is a Multiplexer... kinda
		if (!mem_wb.IR.isLastInstruction) {

			switch (mem_wb.IR.getInstructionType()) {
			case MIPSInstruction.REGISTER_REGISTER:
				if (Integer.parseInt(mem_wb.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DMULT) {
					// TODO: separate top from bottom
					registers.setHILO(mem_wb.ALUOutput);
				} else
					registers.setR(mem_wb.IR.getBinarySegment(16, 20), mem_wb.ALUOutput);
				break;
			case MIPSInstruction.REGISTER_IMMEDIATE:

				System.out.println("WB: " + mem_wb.IR.getB() + " " + mem_wb.ALUOutput);
				if (Integer.parseInt(mem_wb.IR.getBinarySegment(26, 31).toString()) == RTypeInstruction.DSLL)
					registers.setR(mem_wb.IR.getBinarySegment(16, 20), mem_wb.ALUOutput);
				else
					registers.setR(mem_wb.IR.getB(), mem_wb.ALUOutput);
				break;
			case MIPSInstruction.LOAD:

				// need to check to which register will be store R or F
				registers.setR(mem_wb.IR.getB(), mem_wb.LMD);
				registers.setF(mem_wb.IR.getB(), mem_wb.LMD);
				break;
			}

			pipelineMapManager.addEntry(cycles, PipelineMapManager.WB_STAGE, mem_wb.IR);
		} else
			keepRunning = false;

		return 0;
	}

}
