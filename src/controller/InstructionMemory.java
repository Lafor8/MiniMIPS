package controller;

import java.math.BigInteger;
import java.util.HashMap;
import model.MIPSInstruction;

public class InstructionMemory {
	
	public HashMap<BigInteger, MIPSInstruction> instructionMemory;
	public BigInteger lastAddress;
	
	public InstructionMemory(){
		instructionMemory = new HashMap<>();
		lastAddress = BigInteger.valueOf(4);
	}
	
	public MIPSInstruction getInstructionAddress(BigInteger index){
		System.out.println("Loading: " + index + " " + instructionMemory.get(index));
		return instructionMemory.get(index);
	}

	public void setInstructionAddress(BigInteger index, MIPSInstruction value){
		instructionMemory.put(index, value);
		lastAddress = lastAddress.add(BigInteger.valueOf(4));
	}

}
