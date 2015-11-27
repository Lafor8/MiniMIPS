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

		System.out.println("ITYPE: " + Long.toHexString(opcodeBinary));
		//NumberFormat formatter = new HexaDecimalFormat("#0.0000000000000");    
		//String paddedInst = String.format("%032X", Integer.toHexString(immediate));
		System.out.println("ITYPE: " + Integer.toHexString(immediate));
		System.out.println("ITYPE: " +  Integer.toHexString(immediate & 0xFFFF));
		System.out.println("ITYPE: " +  Integer.toHexString(immediate & 0xFFFF));
		System.out.println("ITYPE: " +  (immediate & 0x0FFFF));
		//opcodeBinary += Integer.toHexString(immediate).substring(16);
		opcodeBinary+= immediate & 0xFFFF;
		opcode = opcodeBinary;
		
		opcodeHex = Long.toHexString(opcodeBinary);
		
		System.out.println("ITYPE: " + op + " " + val1+ " " + val2+ " " +immediate);
	}
	
	public int getA(){
		return val1;
	}
	
	public int getIMM(){
		return immediate;
	}
}
