package controller;

import java.math.BigInteger;

import model.MIPSInstruction;
import utilities.MiniMipsUtilities;

public class InternalRegisters {

	public MIPSInstruction IR;
	public BigInteger NPC;
	public BigInteger PC;

	public BigInteger A;
	public BigInteger B;
	public BigInteger IMM;

	public BigInteger ALUOutput;
	public Boolean Cond;

	public BigInteger LMD;

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("IR: \t\t");
		sb.append(IR);
		sb.append("\n");
		sb.append("NPC: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(NPC));
		sb.append("\n");
		sb.append("PC: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(PC));
		sb.append("\n");
		sb.append("A: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(A));
		sb.append("\n");
		sb.append("B: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(B));
		sb.append("\n");
		sb.append("IMM: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(IMM));
		sb.append("\n");
		sb.append("ALUOutput: \t");
		sb.append(MiniMipsUtilities.getPaddedHex128(ALUOutput));
		sb.append("\n");
		sb.append("Cond: \t\t");
		sb.append(Cond);
		sb.append("\n");
		sb.append("LMD: \t\t");
		sb.append(MiniMipsUtilities.getPaddedHex(LMD));
		sb.append("\n");

		return sb.toString();
	}
}
