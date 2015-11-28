package controller;

import java.math.BigInteger;

public class AdderALU extends ALU {

//	public static void main(String args[]) {
//		AdderALU adder = new AdderALU();
//
//		System.out.println(adder.add(BigInteger.ONE, 4).toString());
//	}

	public BigInteger add(BigInteger pc, int i) {

		BigInteger newPC = pc.add(BigInteger.valueOf(i));
		return newPC;
	}
}
