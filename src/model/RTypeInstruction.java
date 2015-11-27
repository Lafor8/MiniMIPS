package model;

import java.math.BigInteger;

public class RTypeInstruction extends MIPSInstruction {

	// final values are for the func variable

	public static final int DADDU = 45;
	public static final int DMULT = 28;
	public static final int OR = 37;
	public static final int SLT = 42;

	// extended R-type
	public static final int ADDS = 0;
	public static final int MULS = 2;
	public static final int OP = 17;
	public static final int EXTOP = 16;
	
	// shift instructions
	public static final int DSLL = 56;

	
	public int op;
	public int val1;
	public int val2;
	public int val3;
	public int val4;
	public int func;
	public long opcodeBinary;
	public String opcodeHex;

	public RTypeInstruction(int op, int val1, int val2, int val3, int val4, int func) {
		this.op = op;
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		this.val4 = val4;
		this.func = func;
		
		opcodeBinary += op << 26;
		opcodeBinary += val1 << 21;
		opcodeBinary += val2 << 16;
		opcodeBinary += val3 << 11;
		opcodeBinary += val4 << 6;
		opcodeBinary += func;
		opcode = opcodeBinary;
		opcodeHex = Long.toHexString(opcodeBinary);
	}
	
	public BigInteger getA(){
		
		if (func == 56)
			return BigInteger.valueOf(val2);
		else if (func == 0 || func == 1)
			return BigInteger.valueOf(val3);
		else
			return BigInteger.valueOf(val1);
	}
	
	public BigInteger getB(){
		
		if (func == 65)
			return BigInteger.valueOf(val4);
		else if (func == 0 || func == 1)
			return BigInteger.valueOf(val2);
		else 
			return BigInteger.valueOf(val2);
	}
	
	public BigInteger getIMM(){
		int immediate = 0;
		
		immediate += val3 << 11;
		immediate += val4 << 6;
		immediate += func;
		
		return BigInteger.valueOf(immediate);
	}

}
