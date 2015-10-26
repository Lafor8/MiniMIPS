package controller;

import com.sun.javafx.css.Selector;

import model.MIPSInstruction;
import model.*;

public class SequentialDatapath {
	// Program Counter
	private long pc;
	
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
			
		return 0;
	}

	public int IF() {
		if_id.npc = adderAlu.add(pc, 4);
		if_id.ir = instructionMemory.getInstructionAddress(pc);

		return 0;
	}

	public int ID() {
		id_ex.ir = if_id.ir;
		id_ex.npc = if_id.npc;
		
		id_ex.a = Registers.getA(if_id.ir);
		id_ex.b = Registers.getB(if_id.ir);
		id_ex.imm = SignExtend.getImmAndExtend(if_id.ir);

		return 0;
	}

	public int EX() {
		ex_mem.ir = id_ex.ir;
		ex_mem.b = id_ex.b;
		
		switch (MIPSInstruction.getInstructionType(id_ex.ir)) {
			case MIPSInstruction.BRANCH:
				ex_mem.aluOutput = id_ex.npc + id_ex.imm << 2;
				ex_mem.cond = zeroCondition.check(id_ex.a);
				break;
			case MIPSInstruction.JUMP:
				ex_mem.aluOutput = id_ex.imm << 2;
				ex_mem.cond = 1;
				break; 
			default:
				long param1 = multiplexer.select(id_ex.npc, id_ex.a, id_ex.ir);
				long param2 = multiplexer.select(id_ex.b, id_ex.imm, id_ex.ir);
				ex_mem.aluOutput = alu.apply(param1, param2, id_ex.ir);
		}

		return 0;
	}

	public int MEM() {
		mem_wb.ir = ex_mem.ir;
		mem_wb.aluOutput = ex_mem.aluOutput;
		
		pc = multiplexer.select(if_id.npc, ex_mem.aluOutput, ex_mem.cond);
		
		switch(MIPSInstruction.getInstructionType(ex_mem.ir)){
			case MIPSInstruction.LOAD:
				mem_wb.lmd = dataMemory.getDataFromMemory(ex_mem.aluOutput);
				break;
			case MIPSInstruction.STORE:
				mem_wb.lmd = dataMemory.getDataFromMemory(ex_mem.b);
				break;
		}
		
		return 0;
	}

	public int WB() {
		// TODO: MEM/WB apparently writes IR back to Registers??
		// 		I wasn't sure what this meant so I'm leaving this out for now
		
		// This is a Multiplexer... kinda
		switch(MIPSInstruction.getInstructionType(mem_wb.ir)){
		case MIPSInstruction.REGISTER_REGISTER:
			registers.setRegister(mem_wb.aluOutput, mem_wb.ir, mem_wb.IR16_20);
			break;
		case MIPSInstruction.REGISTER_IMMEDIATE:
			registers.setRegister(mem_wb.aluOutput, mem_wb.ir, mem_wb.IR11_15);
			break;
		case MIPSInstruction.LOAD:
			registers.setRegister(mem_wb.lmd, mem_wb.ir, mem_wb.IR11_15);
			break;
		}
		
		return 0;
	}
}
