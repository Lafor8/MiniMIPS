package model;

public class JTypeInstruction extends MIPSInstruction {
	
	public static final int J = 2;
	
	int op; 
	long offset; // 26
	long opcodeBinary;
	String opcodeHex;
	
	
	int val1;
	int val2;
	int imm;
	
	public JTypeInstruction(int op, int offset) {
		this.op = op;
		this.offset = offset;
		
		opcodeBinary += op << 26;
		opcodeBinary += offset & 0x3FFFFFF;
		opcode = opcodeBinary;
		
		opcodeHex = Long.toHexString(opcodeBinary);
	}
		
}
