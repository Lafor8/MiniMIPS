import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.InstructionIdentifier;

import model.MIPSInstruction;
import model.RTypeInstruction;

public class Main {

	public static void main(String[] args) {

		String str = "<actions>::=<action><action>|X|<game>|alpha";
		str = str.split("=")[1];
		Pattern pattern = Pattern.compile("<.*?>|\\|.*?\\|");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		System.out.println();
		
		String a ="0000000000000100";
		
		BigInteger b = new BigInteger(a,2);
		
		System.out.println(b.bitLength());
		String str2 = "L1:ADD.S F1,F2 , F3 ";
		str = str.split("=")[1];
		Pattern pattern2 = Pattern.compile("<.*?>|\\|.*?\\|");
		Matcher matcher2 = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
