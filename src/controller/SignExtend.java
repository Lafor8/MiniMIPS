package controller;

import java.math.BigInteger;

public class SignExtend {

	public BigInteger getImmAndExtend(BigInteger IMM) {
		BigInteger extended;
		
		if(IMM.compareTo(BigInteger.valueOf(65536)) >= 0)
			extended = BigInteger.valueOf(Long.parseLong(IMM.toString()) | 0xFFFFFFFFFFFF0000L);
		else
			extended = IMM;
			
		return extended;
	}

}
