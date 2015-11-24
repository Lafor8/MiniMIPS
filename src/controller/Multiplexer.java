package controller;

import model.MIPSInstruction;

public class Multiplexer {

	public long select(long B, long IMM, long IR) {
		
		switch(MIPSInstruction.getInstructionType(IR)){
		case "LOAD":
		case "STORE": return IMM;
		case "REGISTER_REGISTER":return B;
		case "REGISTER_IMMEDIATE": return IMM;
		}
		return 0;
	}

}
