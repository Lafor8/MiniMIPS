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
//		MiniMipsController miniMips = new MiniMipsController();
//		miniMips.run();
		
System.out.println("Ax09.:#~$ ".matches("[A-z0-9:\\.#\\s~$]+"));
//
//System.out.println("  s".matches("\\s?."));

		
		//System.out.println("LABEL : ADD.D R1,R3 , 1000(R20)".matches("([A-z0-9.]+\\s*)(,\\s*[A-z0-9.]\\s*(,[A-z0-9.]+\\s*\\([RrFf][0-9]{1,2}\\))?)?"));
//	System.out.println("// sad ".matches("((//)|;).*"));
//	System.out.println("; sad ".matches("(//)|(;).*"));
//	System.out.println("//sad ".matches("((//)|;).*"));
//	System.out.println(";sad ".matches("(//)|(;).*"));
//	System.out.println("/ sad ".matches("((//)|;).*"));
//	System.out.println("sad; ".matches("(//)|(;).*"));
		

		
		String regLabel = "[A-z0-9\\._]+\\s*";
		String sep = ":\\s*";
		String regCommand = "[A-z\\._]+\\s*";
		String regRegister ="[RrFf][0-9]+\\s*";
		String regImmediate ="(0x)?[0-9]+\\s*";

		String regLabelImm = "(("+regLabel + ")|("+regImmediate+"))\\s*";
		String regRRR = regRegister +",\\s*" + regRegister + ",\\s*"+ regRegister ;
		String regRLR = regRegister +",\\s*" + regLabelImm  + "\\(\\s*"+ regRegister+"\\)\\s*" ;
		String regRRL = regRegister +",\\s*" + regRegister + ",\\s*"+ regLabelImm  ;
		String regRR = regRegister +",\\s*" + regRegister;
		String regL = regLabelImm  ;

		String regParams = "((" + regRRR +")|("  + regRLR+ ")|(" +regRRL + ")|("+ regRR+ ")|("+regL+"))\\s*";

		String regGeneralFormat = "("+regLabel+sep+")?\\s*" + regCommand + "\\s+" + regParams;

System.out.println("sad:".matches(regLabel));
System.out.println("sad : ".matches(regLabel));
System.out.println("Az09._Pie : ".matches(regLabel));
System.out.println("_ : ".matches(regLabel));

System.out.println();

System.out.println("ADD.S ".matches(regCommand));
System.out.println("ADD.S".matches(regCommand));
System.out.println("DAdDU".matches(regCommand));
System.out.println("pie".matches(regCommand));

System.out.println();

System.out.println("R12".matches(regRegister));
System.out.println("F13".matches(regRegister));
System.out.println("f2 ".matches(regRegister));
System.out.println("r12".matches(regRegister));

System.out.println();

System.out.println("0x00001".matches(regImmediate));
System.out.println("0x999".matches(regImmediate));
System.out.println("99123".matches(regImmediate));
System.out.println("13242".matches(regImmediate));


System.out.println();
System.out.println("DADDU R10, R1, R2".matches(regGeneralFormat));
System.out.println("DMULT R1 , R2".matches(regGeneralFormat));
System.out.println("L1:OR R11, R1,R2".matches(regGeneralFormat));
System.out.println("SLT R13, R1, R2".matches(regGeneralFormat));
System.out.println("".matches(regGeneralFormat));
System.out.println(";BEQ R13, R0, L2".matches(regGeneralFormat));
System.out.println("LW R14, 0000(R1)".matches(regGeneralFormat));
System.out.println("LWU R15, 0008(R2)".matches(regGeneralFormat));
System.out.println("SW R1, 0010(R3)".matches(regGeneralFormat));
System.out.println("DSLL R16, R1, 2".matches(regGeneralFormat));
System.out.println("ANDI R17, R1, 12".matches(regGeneralFormat));
System.out.println("DADDIU R18, R1, 4".matches(regGeneralFormat));
System.out.println(";BEQ R13, R0, L1".matches(regGeneralFormat));
System.out.println("".matches(regGeneralFormat));
System.out.println("J L2".matches(regGeneralFormat));
System.out.println(" ".matches(regGeneralFormat));
System.out.println("L.S F10, 0018(R4)".matches(regGeneralFormat));
System.out.println("S.S F1, 0020 (R5)".matches(regGeneralFormat));
System.out.println("L2:ADD.S F11,F1,F2".matches(regGeneralFormat));
System.out.println("MUL.S F12, F1,F2".matches(regGeneralFormat));

System.out.println();

System.out.println("  ".matches("(((//)|;).*)|(\\s*)"));
System.out.println(";s  ".matches("(((//)|;).*)|(\\s*)"));
System.out.println("//d  ".matches("(((//)|;).*)|(\\s*)"));
System.out.println("; s  ".matches("(((//)|;).*)|(\\s*)"));
System.out.println("// d  ".matches("(((//)|;).*)|(\\s*)"));
System.out.println("".matches("(((//)|;).*)|(\\s*)"));
	}
}

//
// ([A-z0-9.]+\\s*)(,\\s*[A-z0-9.]\\s*(,[A-z0-9.]+\\s*\\(\\[RrFf][0-9]{1,2}))?)?