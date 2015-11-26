package model;

import java.math.BigInteger;

public class ITypeInstruction extends MIPSInstruction {
	
	public static final int BEQ = 4;
	public static final int LW = 35;
	public static final int LWU = 39;
	public static final int SW = 43;

	// immediate
	public static final int ANDI = 12;
	public static final int DADDIU = 25;

	// floating point
	public static final int LS = 49;
	public static final int SS = 57;

	int op;
	int val1;
	int val2;
	int immediate;
	long opcodeBinary;
	String opcodeHex;

	public ITypeInstruction(int op, int val1, int val2, int immediate) {
		this.op = op;
		this.val1 = val1;
		this.val2 = val2;
		this.immediate = immediate;
		
		opcodeBinary += op << 26;
		opcodeBinary += val1 << 21;
		opcodeBinary += val2 << 16;
		opcodeBinary += immediate;
		opcode = opcodeBinary;
		
		opcodeHex = Long.toHexString(opcodeBinary);
	}
	
	public int getA(){
		return val1;
	}
	
	public int getIMM(){
		return immediate;
	}
}
