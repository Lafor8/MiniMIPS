package controller;

import java.math.BigInteger;

public class SignExtend {


	
	public BigInteger getImmAndExtend(BigInteger IMM) {
		BigInteger extended;

		if (IMM.compareTo(BigInteger.valueOf(32768)) >= 0) {
			extended = BigInteger.valueOf(Long.parseLong(IMM.toString()) | 0x7FFFFFFFFFFF0000L);
			extended = extended.add(BigInteger.valueOf(4611686018427387904L).multiply(BigInteger.valueOf(2)));
		} else
			extended = IMM;

		return extended;
	}
}
