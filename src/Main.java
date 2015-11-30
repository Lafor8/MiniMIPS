import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.InstructionIdentifier;
import controller.MiniMipsController;

import model.MIPSInstruction;
import model.RTypeInstruction;

public class Main {

	public static void main(String[] args) {
		MiniMipsController miniMips = new MiniMipsController();
		miniMips.run();
		
		
		String regRegisterStrict = "[RrFf]0*(([0-9])|([12][0-9])|(3[0-1]))\\s*";
		System.out.println("BEQ R1, R2, L1".matches("[A-z0-9:\\._,\\s]+"));
		for(int i =0; i < 40; ++i)
		System.out.println(i+ " "+("R0"+i).matches(regRegisterStrict));
	}
}