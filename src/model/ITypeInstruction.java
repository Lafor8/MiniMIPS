package model;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

	public ITypeInstruction(int op, int val1, int val2, int immediate) {
		this.op = op;
		this.val1 = val1;
		this.val2 = val2;
		this.immediate = immediate;
		
		long opcodeBinary = 0;
		
		if(op == 49 || op == 57)
			this.isFloatInst = true;
		
		opcodeBinary += op << 26;
		opcodeBinary += val1 << 21;
		opcodeBinary += val2 << 16;
		opcodeBinary += immediate & 0x0FFFF;
		opcodeBinary = opcodeBinary & 0x0FFFFFFFFL; 
		opcode = BigInteger.valueOf(opcodeBinary);
	}
	
	public BigInteger getA(){
		return BigInteger.valueOf(val1);
	}
	
	public BigInteger getB(){
		return BigInteger.valueOf(val2);
	}
	
	public BigInteger getIMM(){
		return BigInteger.valueOf(immediate);
	}
}
