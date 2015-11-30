package utilities;

public class GeneralInstructionFormat {

	static String regLabel = "[A-z0-9\\._]+\\s*";
	static String sep = ":\\s*";
	static String regCommand = "[A-z\\._]+\\s*";
	static String regRegister = "[RrFf][0-9]+\\s*";
	static String regRegisterStrict = "[RrFf]0*(([0-9])|([12][0-9])|(3[0-1]))\\s*";
	static String regRegisterR = "[Rr][0-9]+\\s*";
	static String regRegisterF = "[Ff][0-9]+\\s*";
	static String regImmediate = "(0x)?[0-9]+\\s*";

	static String regLabelImm = "((" + regLabel + ")|(" + regImmediate + "))\\s*";
	static String regVVV = regRegister + ",\\s*" + regRegister + ",\\s*" + regRegister;
	static String regVLV = regRegister + ",\\s*" + regLabelImm + "\\(\\s*" + regRegister + "\\)\\s*";
	static String regVVL = regRegister + ",\\s*" + regRegister + ",\\s*" + regLabelImm;
	static String regVV = regRegister + ",\\s*" + regRegister;
	static String regL = regLabelImm;
	
	public static String availableCommands = "(daddi?u)|(dmult)|(or)|(slt)|(beq)|(lwu?)|(sw)|(dsll)|(andi)|(j)|(l.s)|(s.s)|(add.s)|(mul.s)";

	static String regParams = "((" + regVVV + ")|(" + regVLV + ")|(" + regVVL + ")|(" + regVV + ")|(" + regL + "))\\s*";

	static String regGeneralFormat = "(" + regLabel + sep + ")?\\s*" + regCommand + "\\s+" + regParams;
	
	static String regFFF = regRegisterF + ",\\s*" + regRegisterF + ",\\s*" + regRegisterF;
	static String regFLR = regRegisterF + ",\\s*" + regLabelImm + "\\(\\s*" + regRegister + "\\)\\s*";
	
	static String regRRR = regRegisterR + ",\\s*" + regRegisterR + ",\\s*" + regRegisterR;
	static String regRLR = regRegisterR + ",\\s*" + regLabelImm + "\\(\\s*" + regRegisterR + "\\)\\s*";
	static String regRRL = regRegisterR + ",\\s*" + regRegisterR + ",\\s*" + regLabelImm;
	static String regRR = regRegisterR + ",\\s*" + regRegisterR;
	
	static String regSSS = regRegisterStrict + ",\\s*" + regRegisterStrict + ",\\s*" + regRegisterStrict;
	static String regSLS = regRegisterStrict + ",\\s*" + regLabelImm + "\\(\\s*" + regRegisterStrict + "\\)\\s*";
	static String regSSL = regRegisterStrict + ",\\s*" + regRegisterStrict + ",\\s*" + regLabelImm;
	static String regSS = regRegisterStrict + ",\\s*" + regRegisterStrict;
	
	public static String fpParams = "((" + regFFF + ")|(" + regFLR + "))\\s*";
	public static String intParams = "((" + regRRR + ")|(" + regRLR + ")|(" + regRRL + ")|(" + regRR + ")|(" + regL + "))\\s*";
	public static String strictParams = "((" + regSSS + ")|(" + regSLS + ")|(" + regSSL + ")|(" + regSS + ")|(" + regL + "))\\s*";


	public static boolean check(String str) {
		return str.matches(regGeneralFormat);
	}

}
