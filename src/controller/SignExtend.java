package controller;

import java.math.BigInteger;

public class SignExtend {

//	public static void main(String args[]) {
//		SignExtend signExtend = new SignExtend();
//
//		System.out.println(signExtend.getImmAndExtend(BigInteger.valueOf(0x0FFFL)).toString(16));
//		System.out.println(signExtend.getImmAndExtend(BigInteger.valueOf(0x8FFFL)).toString(16));
//		System.out.println(signExtend.getImmAndExtend(BigInteger.valueOf(0xFFFFL)).toString(16));
//	}

	public BigInteger getImmAndExtend(BigInteger IMM) {
		BigInteger extended;

		if (IMM.compareTo(BigInteger.valueOf(32768)) >= 0) {
			extended = BigInteger.valueOf(Long.parseLong(IMM.toString()) | 0x7FFFFFFFFFFF0000L);
			extended = extended.add(BigInteger.valueOf(4611686018427387904L).multiply(BigInteger.valueOf(2)));
		} else
			extended = IMM;

//		System.out.println("FUNC: " + IMM.toString(16));
//		System.out.println("FUNC: " + BigInteger.valueOf(32768).toString(16));
//		System.out.println("FUNC: " + IMM.compareTo(BigInteger.valueOf(32768)));
//		System.out.println("FUNC: " + extended.toString(16));
//		System.out.println("FUNC: " + extended.toString(16));
//		System.out.println("FUNC: " + BigInteger.valueOf(4611686018427387904L).multiply(BigInteger.valueOf(2)).toString(16));
//		System.out.println("FUNC: " + BigInteger.valueOf(Long.parseLong(IMM.toString()) | 0x7FFFFFFFFFFF0000L).toString(16));

		return extended;
	}

}
