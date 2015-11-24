package model;

public class MIPSInstruction {

	protected String instruction;
	protected long opcode;
	
	public static final String BRANCH = "Branch";
	public static final String JUMP = "Jump";
	public static final String LOAD = "Load";
	public static final String STORE = "Store";
	public static final String REGISTER_REGISTER = "Register_Register";
	public static final String REGISTER_IMMEDIATE = "Register_Immediate";
	
	

	public String getOpcodeInHex() {
		String paddedOpcode = Long.toHexString(opcode);
		for (int i = 0, size = paddedOpcode.length(); i < 8 - size; i++)
			paddedOpcode = "0" + paddedOpcode;

		return paddedOpcode;
	}

	public String getOpcodeInBinary() {
		String paddedOpcode = Long.toBinaryString(opcode);
		for (int i = 0, size = paddedOpcode.length(); i < 32 - size; i++)
			paddedOpcode = "0" + paddedOpcode;
		return paddedOpcode;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public long getOpcode() {
		return opcode;
	}

	public void setOpcode(long opcode) {
		this.opcode = opcode;
	}

}
