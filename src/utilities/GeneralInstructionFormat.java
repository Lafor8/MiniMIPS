package utilities;

public class GeneralInstructionFormat {

	static String regLabel = "[A-z0-9\\._]+\\s*";
	static String sep = ":\\s*";
	static String regCommand = "[A-z\\._]+\\s*";
	static String regRegister = "[RrFf][0-9]+\\s*";
	static String regImmediate = "(0x)?[0-9]+\\s*";

	static String regLabelImm = "((" + regLabel + ")|(" + regImmediate + "))\\s*";
	static String regRRR = regRegister + ",\\s*" + regRegister + ",\\s*" + regRegister;
	static String regRLR = regRegister + ",\\s*" + regLabelImm + "\\(\\s*" + regRegister + "\\)\\s*";
	static String regRRL = regRegister + ",\\s*" + regRegister + ",\\s*" + regLabelImm;
	static String regRR = regRegister + ",\\s*" + regRegister;
	static String regL = regLabelImm;

	static String regParams = "((" + regRRR + ")|(" + regRLR + ")|(" + regRRL + ")|(" + regRR + ")|(" + regL + "))\\s*";

	static String regGeneralFormat = "(" + regLabel + sep + ")?\\s*" + regCommand + "\\s+" + regParams;

	public static boolean check(String str) {
		return str.matches(regGeneralFormat);
	}

}
