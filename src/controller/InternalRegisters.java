package controller;

import java.math.BigInteger;

import model.MIPSInstruction;

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
}
