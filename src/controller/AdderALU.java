package controller;

import java.math.BigInteger;

public class AdderALU extends ALU {

	public BigInteger add(BigInteger pc, int i) {

		BigInteger newPC = pc.add(BigInteger.valueOf(i));
		return newPC;
	}
}
