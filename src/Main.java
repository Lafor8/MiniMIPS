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
		
		
		
		System.out.println("BEQ R1, R2, L1".matches("[A-z0-9:\\._,\\s]+"));
	}
}