package utilities;

import java.math.BigInteger;

public class MiniMipsUtilities {

	public static void main(String args[]) {
		// System.out.println(MiniMipsUtilities.getPaddedBinary32(BigInteger.valueOf(3)));

		BigInteger param1 = new BigInteger("3f800000", 16);
		BigInteger param2 = new BigInteger("c0000000", 16);

		String str1, str2;
		str1 = MiniMipsUtilities.getPaddedHex64(param1).substring(8);
		str2 = MiniMipsUtilities.getPaddedHex64(param2).substring(8);

		BigInteger m1 = BigInteger.valueOf((Long.parseLong(str1, 16) & 0x7FFFFFL) | 0x800000L);
		BigInteger m2 = BigInteger.valueOf((Long.parseLong(str2, 16) & 0x7FFFFFL) | 0x800000L);

		boolean sb1 = (Long.parseLong(str1, 16) >> 31) > 0;
		boolean sb2 = (Long.parseLong(str2, 16) >> 31) > 0;

		long exp1 = ((Long.parseLong(str1, 16) & 0x7F800000L) >> 23) - 127;
		long exp2 = ((Long.parseLong(str2, 16) & 0x7F800000L) >> 23) - 127;

		
		//long val = m
		
		
		System.out.println(str1 + " " + sb1 + " " + exp1 + " " + m1);
		System.out.println(str2 + " " + sb2 + " " + exp2 + " " + m2);
		System.out.println(Long.toHexString((Long.parseLong(str1, 16) & 0x7F800000L)));
		System.out.println(m1.toString(16));
		System.out.println(m2.toString(16));
		System.out.println(Float.intBitsToFloat(Integer.parseInt("3f800000",16)));

		//System.out.println(Float.floatToIntBits(1)));
	}

	public static String getPaddedHex(BigInteger val) {
		if (val == null)
			return null;
		String paddedHex = val.toString(16);
		for (int i = 0, size = paddedHex.length(); i < 8 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}
	
	public static String getPaddedHex64(BigInteger val) {
		if (val == null)
			return null;
		String paddedHex = val.toString(16);
		for (int i = 0, size = paddedHex.length(); i < 16 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}


	public static String getPaddedBinary32(BigInteger val) {
		if (val == null)
			return null;
		String paddedHex = val.toString(2);
		for (int i = 0, size = paddedHex.length(); i < 32 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}

	public static String getPaddedHex128(BigInteger val) {
		if (val == null)
			return null;
		String paddedHex = val.toString(16);
		for (int i = 0, size = paddedHex.length(); i < 16 - size; i++)
			paddedHex = "0" + paddedHex;

		return paddedHex;
	}
}
