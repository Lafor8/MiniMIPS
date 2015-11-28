package utilities;

import java.math.BigInteger;

public class MiniMipsUtilities {
	
	public static String getPaddedHex(BigInteger val) {
		String paddedHex = val.toString(16);
		for (int i = 0, size = paddedHex.length(); i < 8 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}
	
	public static String getPaddedHex128(BigInteger val) {
		String paddedHex = val.toString(16);
		for (int i = 0, size = paddedHex.length(); i < 16 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}
}
