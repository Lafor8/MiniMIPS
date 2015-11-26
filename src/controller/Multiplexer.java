package controller;

import java.math.BigInteger;

import model.MIPSInstruction;

public class Multiplexer {

	/*
	public long select(long B, long IMM, String IR) {
		
		switch(MIPSInstruction.getInstructionType(IR)){
		case "LOAD":
		case "STORE": return IMM;
		case "REGISTER_REGISTER":return B;
		case "REGISTER_IMMEDIATE": return IMM;
		}
		return 0;
	}
	*/
	
	public BigInteger select(BigInteger first, BigInteger second, Boolean cond) {
			
			if(cond)
				return first;
			else
				return second;
			
		}

}
