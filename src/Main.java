import java.math.BigInteger;
import java.util.ArrayList;

import controller.InstructionIdentifier;

import model.MIPSInstruction;
import model.RTypeInstruction;

public class Main {

	public static void main(String[] args) {
		
		String a ="0000000000000100";
		
		BigInteger b = new BigInteger(a,2);
		
		System.out.println(b.bitLength());
	}
}
