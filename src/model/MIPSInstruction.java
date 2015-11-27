package model;

import java.math.BigInteger;

public class MIPSInstruction {

	protected String instruction;
	protected long opcode;
	
	public boolean isValid;
	public String errorMsg;
	public BigInteger address;
	
	public static final String BRANCH = "Branch";
	public static final String JUMP = "Jump";
	public static final String LOAD = "Load";
	public static final String STORE = "Store";
	public static final String REGISTER_REGISTER = "Register_Register";
	public static final String REGISTER_IMMEDIATE = "Register_Immediate";
	
	public void setError(boolean isValid, String errorMsg){
		this.isValid = isValid;
		this.errorMsg = errorMsg;
	}
	
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

	public int getA(){
		return 0;
	}
	
	public int getB(){
		return 0;
	}
	
	public int getIMM(){
		return 0;
	}
	
	public static String getInstructionType(String IR) {
		
		String op = Long.toString(Long.parseLong(IR.substring(0, 6), 2));
		switch(op){
			case "2": return JUMP; 
			case "4": return BRANCH;			
			case "35":
			case "39":
			case "49":
			case "53": return LOAD;
			case "43": return STORE;
			case "12": 
			case "25": return REGISTER_IMMEDIATE;
			default:   return REGISTER_REGISTER;	
		}
	}
	
	public static Boolean getCondForMultiplexer(String IR){
		
		switch(MIPSInstruction.getInstructionType(IR)){
		
			case "LOAD":
			case "STORE":
			case "REGISTER_IMMEDIATE": return false;
			case "REGISTER_REGISTER":return true;
			
		}
		return null;
		
	}
}
