package controller;

import java.math.BigInteger;
import java.util.HashMap;
import model.MIPSInstruction;

public class InstructionMemory {
	
	HashMap<BigInteger, MIPSInstruction> instructionMemory;
	
	public MIPSInstruction getInstructionAddress(BigInteger index){
		return instructionMemory.get(index);
	}

	public void setInstructionAddress(BigInteger index, MIPSInstruction value){
		instructionMemory.put(index, value);
	}

}
