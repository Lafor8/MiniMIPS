package model;

import java.math.BigInteger;

import utilities.MiniMipsUtilities;

public class MIPSInstruction {

	public BigInteger address;
	public String instruction;
	public BigInteger opcode;

	public boolean isValid;
	public String errorMsg;

	public static final String BRANCH = "Branch";
	public static final String JUMP = "Jump";
	public static final String LOAD = "Load";
	public static final String STORE = "Store";
	public static final String REGISTER_REGISTER = "Register_Register";
	public static final String REGISTER_IMMEDIATE = "Register_Immediate";

	public boolean isFloatInst;
	public double salt;

	public MIPSInstruction() {
		salt = Math.random();
		isFloatInst = false;
	}

	public void setError(boolean isValid, String errorMsg) {
		this.isValid = isValid;
		this.errorMsg = errorMsg;
	}

	public String getOpcodeInHex() {
		String paddedOpcode;

		paddedOpcode = MiniMipsUtilities.getPaddedHex(opcode);
		paddedOpcode = paddedOpcode.substring(0, 4) + " " + paddedOpcode.substring(4);

		return paddedOpcode;
	}

	public String getOpcodeInBinary() {
		String paddedOpcode = opcode.toString(2);
		for (int i = 0, size = paddedOpcode.length(); i < 32 - size; i++)
			paddedOpcode = "0" + paddedOpcode;

		return paddedOpcode;
	}

	public BigInteger getBinarySegment(int i, int n) {
		String segment = getOpcodeInBinary().substring(i, n + 1);
		return BigInteger.valueOf(Long.parseLong(segment, 2));
	}

	public BigInteger getA() {
		return BigInteger.ZERO;
	}

	public BigInteger getB() {
		return BigInteger.ZERO;
	}

	public BigInteger getIMM() {
		return BigInteger.ZERO;
	}

	public String getInstructionType() {
		String retVal;
		String IR = getOpcodeInBinary();
		String op = Long.toString(Long.parseLong(IR.substring(0, 6), 2));

		switch (op) {
		case "2":
			retVal = JUMP;
			break;
		case "4":
			retVal = BRANCH;
			break;
		case "35":
		case "39":
		case "49":
			// case "53":
			retVal = LOAD;
			break;
		case "43":
		case "57":
			retVal = STORE;
			break;
		case "12":
		case "25":
			retVal = REGISTER_IMMEDIATE;
			break;
		default:
			retVal = REGISTER_REGISTER;
			break;
		}

		if (this.getBinarySegment(26, 31).toString(10).equals("56"))
			retVal = REGISTER_IMMEDIATE;

		return retVal;
	}

	public Boolean getCondForMultiplexer() {

		switch (getInstructionType()) {
		case LOAD:
		case STORE:
		case REGISTER_IMMEDIATE:
			return false;
		case REGISTER_REGISTER:
			return true;

		}
		return null;

	}

	public String toString() {
		return instruction;
	}
}
