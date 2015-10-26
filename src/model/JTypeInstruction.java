package model;

public class JTypeInstruction extends MIPSInstruction {
	
	public static final int J = 2;
	
	
	int opcode; 
	long offset; // 26
	long opcodeBinary;
	String opcodeHex;
	
}
