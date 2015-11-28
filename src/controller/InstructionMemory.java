package controller;

import java.math.BigInteger;
import java.util.HashMap;
import model.MIPSInstruction;

public class InstructionMemory {
	
	public HashMap<BigInteger, MIPSInstruction> instructionMemory;
	
	public InstructionMemory(){
		instructionMemory = new HashMap<>();
	}
	
	public MIPSInstruction getInstructionAddress(BigInteger index){
		System.out.println("Loading: " + index + " " + instructionMemory.get(index));
		return instructionMemory.get(index);
	}

	public void setInstructionAddress(BigInteger index, MIPSInstruction value){
		instructionMemory.put(index, value);
	}

}
