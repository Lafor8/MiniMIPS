package controller;

import java.math.BigInteger;

public class ZeroCondition {

	public Boolean check(BigInteger a) {
		if (a.equals(BigInteger.ZERO))
			return true;
		else
			return false;
	}
}
