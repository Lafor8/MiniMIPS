package controller;

import java.math.BigInteger;

import model.*;

public class SequentialDatapath {
	// Program Counter
	private BigInteger pc = new BigInteger("0000000000000000");
	
	// Internal Registers
	private InternalRegisters if_id;
	private InternalRegisters id_ex;
	private InternalRegisters ex_mem;
	private InternalRegisters mem_wb;
	
	// Registers and Memory
	private InstructionMemory instructionMemory;
	private Registers registers;
	private DataMemory dataMemory;

	// Operators
	private ALU alu;
	private AdderALU adderAlu;
	private Multiplexer multiplexer;
	private ZeroCondition zeroCondition;
	private SignExtend signExtend;

	// Auxillary variables
	private boolean keepRunning;
	private int cycles;

	// Pipeline Mapping
	private PipelineMapManager pipelineMapManager;
	
	public SequentialDatapath(){
		pipelineMapManager = pipelineMapManager.getInstance();
	}
	
	public int run() {
		pc = 0;
		
		cycles = 0;
		while (keepRunning) {
			runOneCycle();
		}

		return 0;
	}
	
	public int runOneCycle(){
		if(mem_wb != null)
			WB();
		if(ex_mem != null)
			MEM();
		if(id_ex != null)
			EX();
		if(if_id != null)
			ID();
		IF();
		
		cycles++;
		
		return 0;
	}

	public int IF() {
		
		if(MIPSInstruction.getInstructionType(ex_mem.IR) == "BRANCH" && ex_mem.Cond){
			if_id.NPC = ex_mem.ALUOutput;
		}else
		{
			if_id.NPC = adderAlu.add(pc, 4);
		}
		
		
		if_id.IR = instructionMemory.getInstructionAddress(pc);
		// need to create the MIPSInstruction object
		
		pipelineMapManager.addEntry(cycles, PipelineMapManager.IF_STAGE, if_id.IR);
		
		return 0;
	}

	public int ID() {
		id_ex.IR = if_id.IR;
		id_ex.NPC = if_id.NPC;
		
		// need to add special case when instruction is DSLL and floating 
		
		
		// get from R register
		id_ex.A = registers.getR(if_id.IR.getA());
		id_ex.B = registers.getR(if_id.IR.getB());
		
		//get from F register
		id_ex.IMM = SignExtend.getImmAndExtend(if_id.IR);

		pipelineMapManager.addEntry(cycles, PipelineMapManager.ID_STAGE, id_ex.IR);
		
		return 0;
	}

	public int EX() {
		ex_mem.IR = id_ex.IR;
		ex_mem.B = id_ex.B;
		
		switch (MIPSInstruction.getInstructionType(id_ex.IR)) {
			case MIPSInstruction.BRANCH:
				ex_mem.ALUOutput = id_ex.NPC.add(id_ex.IMM.multiply(BigInteger.valueOf(4)));
				ex_mem.Cond = zeroCondition.check(id_ex.A);
				break;
			case MIPSInstruction.JUMP:
				ex_mem.ALUOutput = id_ex.IMM.multiply(BigInteger.valueOf(4));
				ex_mem.Cond = true;
				break; 
			default:
				//long param1 = multiplexer.select(id_ex.NPC, id_ex.A, id_ex.IR);
				BigInteger param1 = id_ex.A; 
				BigInteger param2 = multiplexer.select(id_ex.B, id_ex.IMM, MIPSInstruction.getCondForMultiplexer(id_ex.IR));
				ex_mem.ALUOutput = alu.apply(param1, param2, id_ex.IR);
				ex_mem.Cond = false;
		}
		
		pipelineMapManager.addEntry(cycles, PipelineMapManager.EX_STAGE, ex_mem.IR);

		return 0;
	}

	public int MEM() {
		mem_wb.IR = ex_mem.IR;
		mem_wb.ALUOutput = ex_mem.ALUOutput;
		
		pc = multiplexer.select(if_id.NPC, ex_mem.ALUOutput, ex_mem.Cond);
		
		switch(MIPSInstruction.getInstructionType(ex_mem.IR)){
			case MIPSInstruction.LOAD:
				mem_wb.LMD = dataMemory.getDataFromMemory(ex_mem.ALUOutput);
				break;
			case MIPSInstruction.STORE:
				mem_wb.LMD = dataMemory.getDataFromMemory(ex_mem.B);
				break;
		}

		pipelineMapManager.addEntry(cycles, PipelineMapManager.MEM_STAGE, mem_wb.IR);
		
		return 0;
	}

	public int WB() {
		// TODO: MEM/WB apparently writes IR back to Registers??
		// 		I wasn't sure what this meant so I'm leaving this out for now
		
		// This is a Multiplexer... kinda
		switch(MIPSInstruction.getInstructionType(mem_wb.IR)){
		case MIPSInstruction.REGISTER_REGISTER:
			registers.setRegister(mem_wb.ALUOutput, mem_wb.IR, mem_wb.IR16_20);
			break;
		case MIPSInstruction.REGISTER_IMMEDIATE:
			registers.setRegister(mem_wb.ALUOutput, mem_wb.IR, mem_wb.IR11_15);
			break;
		case MIPSInstruction.LOAD:
			registers.setRegister(mem_wb.LMD, mem_wb.IR, mem_wb.IR11_15);
			break;
		}
		
		pipelineMapManager.addEntry(cycles, PipelineMapManager.WB_STAGE, mem_wb.IR);
		
		return 0;
	}
}
