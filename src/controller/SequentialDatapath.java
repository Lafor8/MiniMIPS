package controller;

import com.sun.javafx.css.Selector;

import model.MIPSInstruction;

public class SequentialDatapath {
	private long pc;
	private InternalRegisters internalRegisters;
	private boolean keepRunning;

	private InstructionMemory instructionMemory;
	private Registers registers;
	private DataMemory dataMemory;

	private ALU alu;
	private AdderALU adderAlu;
	private Multiplexer multiplexer;
	private ZeroCondition zeroCondition;
	private SignExtend signExtend;

	public int runCycles() {
		pc = 0;

		while (keepRunning) {
			IF();
			ID();
			EX();
			MEM();
			WB();
		}

		return 0;
	}

	public int IF() {
		internalRegisters.npc = adderAlu.add(pc, 4);
		internalRegisters.ir = instructionMemory.getInstructionAddress(pc);

		return 0;
	}

	public int ID() {
		internalRegisters.a = Registers.getA(internalRegisters.ir);
		internalRegisters.b = Registers.getB(internalRegisters.ir);
		internalRegisters.imm = SignExtend.getImmAndExtend(internalRegisters.ir);

		return 0;
	}

	public int EX() {
		switch (MIPSInstruction.getInstructionType(internalRegisters.ir)) {
			case MIPSInstruction.BRANCH:
				internalRegisters.aluOutput = internalRegister.npc + internalRegiters.imm << 2;
				internalRegisters.cond = zeroCondition.check(internalRegisters.a);
				break;
			case MIPSInstruction.JUMP:
				internalRegisters.aluOutput = internalRegiters.imm << 2;
				internalRegisters.cond = 1;
				break; 
			default:
				long param1 = multiplexer.select(internalRegisters.npc, internalRegisters.a, internalRegisters.ir);
				long param2 = multiplexer.select(internalRegisters.b, internalRegisters.imm, internalRegisters.ir);
				internalRegisters.aluOutput = alu.apply(param1, param2,internalRegisters.ir);
		}

		return 0;
	}

	public int MEM() {
		pc = multiplexer.select(internalRegisters.npc, internalRegisters.aluOutput, internalRegisters.cond);
		
		switch(MIPSInstruction.getInstructionType(internalRegisters.ir)){
			case MIPSInstruction.LOAD:
				internalRegisters.lmd = dataMemory.getDataFromMemory(internalRegisters.aluOutput);
				break;
			case MIPSInstruction.STORE:
				internalRegisters.lmd = dataMemory.getDataFromMemory(internalRegisters.b);
				break;
		}
		
		return 0;
	}

	public int WB() {
		// This is a Multiplexer... kinda
		switch(MIPSInstruction.getInstructionType(internalRegisters.ir)){
		case MIPSInstruction.REGISTER_REGISTER:
			registers.setRegister(internalRegisters.aluOutput, internalRegisters.ir,internalRegisters.IR16_20);
			break;
		case MIPSInstruction.REGISTER_IMMEDIATE:
			registers.setRegister(internalRegisters.aluOutput, internalRegisters.ir,internalRegisters.IR11_15);
			break;
		case MIPSInstruction.LOAD:
			registers.setRegister(internalRegisters.lmd, internalRegisters.ir,internalRegisters.IR11_15);
			break;
		}
		
		return 0;
	}
}
