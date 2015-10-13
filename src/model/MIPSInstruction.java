package model;

public class MIPSInstruction {
	
	private String instruction;
	private long opcode;
	
	public String getOpcodeInHex(){
		return Long.toHexString(opcode);
	}
	
	public String getOpcodeInBinary(){
		return Long.toBinaryString(opcode);
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
