package controller;

import java.math.BigInteger;

import model.MIPSInstruction;

public class Multiplexer {
	
	public BigInteger select(BigInteger first, BigInteger second, Boolean cond) {
			
			if(cond)
				return first;
			else
				return second;
			
		}

}
