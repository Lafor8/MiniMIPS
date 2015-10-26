package model;

public class ITypeInstruction extends MIPSInstruction {

	// final values are for the opcode variable except for the shift instruction

	public static final int BEQ = 4;
	public static final int LW = 35;
	public static final int LWU = 39;
	public static final int SW = 43;

	// immediate
	public static final int ANDI = 12;
	public static final int DADDIU = 25;

	// shift instructions
	public static final int DSLL = 56;

	// floating point
	public static final int LS = 49;
	public static final int SS = 57;

	int opcode;
	int val1;
	int val2;
	int immediate;
	long opcodeBinary;
	String opcodeHex;

	public ITypeInstruction(int opcode, int val1, int val2, int immediate) {
		this.opcode = opcode;
		this.val1 = val1;
		this.val2 = val2;
		// TODO:
		// this.immediate = immediate;
		// opcodeBinary += opcode << 26;
		// opcodeBinary += val1 << 26;
		// opcodeBinary += val2 << 26;
		// opcodeBinary += val2 << 26;
		opcodeHex = Long.toHexString(opcodeBinary);
	}
}
