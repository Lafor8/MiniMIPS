package model;

public class JTypeInstruction extends MIPSInstruction {
	
	public static final int J = 2;
	
	int op; 
	long offset; // 26
	long opcodeBinary;
	String opcodeHex;
	
	public JTypeInstruction(int op, int offset) {
		this.op = op;
		this.offset = offset;
		
		opcodeBinary += op << 26;
		opcodeBinary += offset;
		opcode = opcodeBinary;
		
		opcodeHex = Long.toHexString(opcodeBinary);
	}
	
}
