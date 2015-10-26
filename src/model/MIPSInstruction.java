package model;

public class MIPSInstruction {

	protected String instruction;
	protected long opcode;

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
