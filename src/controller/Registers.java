package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import utilities.MiniMipsUtilities;

public class Registers {

	List<BigInteger> IntegerRegister = new ArrayList<>(32);
	List<BigInteger> FloatingRegister = new ArrayList<>(32);
	BigInteger HI;
	BigInteger LOW;

	public void Initialize() {
		int i = 0;

		do {
			IntegerRegister.add(i, BigInteger.ZERO);
			FloatingRegister.add(i, BigInteger.ZERO);
			i++;
		} while (i < 32);

		HI = BigInteger.ZERO;
		LOW = BigInteger.ZERO;
	}

	public BigInteger getR(BigInteger index) {
		if (index.equals(BigInteger.ZERO))
			return BigInteger.ZERO;
		return IntegerRegister.get(Integer.parseInt(index.toString()));
	}

	public BigInteger getF(BigInteger index) {
		if (index.equals(BigInteger.ZERO))
			return BigInteger.ZERO;
		return FloatingRegister.get(Integer.parseInt(index.toString()));
	}

	public void setR(BigInteger index, BigInteger value) {
		if (!(index.compareTo(BigInteger.ZERO) == 0))
			IntegerRegister.set(Integer.parseInt(index.toString()), value);
	}

	public void setF(BigInteger index, BigInteger value) {
		if (!(index.compareTo(BigInteger.ZERO) == 0))
			FloatingRegister.set(Integer.parseInt(index.toString()), value);
	}

	public BigInteger getHI() {
		return HI;
	}

	public void setHI(BigInteger hI) {
		HI = hI;
	}

	public BigInteger getLOW() {
		return LOW;
	}

	public void setLOW(BigInteger lOW) {
		LOW = lOW;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Integer Registers\n\n");

		for (int i = 0; i < 32; ++i) {
			sb.append("R");
			sb.append(String.format("%02d", i));
			sb.append(": ");
			sb.append(MiniMipsUtilities.getPaddedHex(this.IntegerRegister.get(i)));
			sb.append("\n");
		}

		sb.append("\n");
		sb.append("HI");
		sb.append(": ");
		sb.append(MiniMipsUtilities.getPaddedHex(this.getHI()));
		sb.append("\n");
		sb.append("LO");
		sb.append(": ");
		sb.append(MiniMipsUtilities.getPaddedHex(this.getLOW()));
		sb.append("\n");

//		sb.append("\n\nFloating Point Registers\n\n");
//
//		for (int i = 0; i < 32; ++i) {
//			sb.append("F");
//			sb.append(String.format("%02d", i));
//			sb.append(": ");
//			sb.append(MiniMipsUtilities.getPaddedHex(this.FloatingRegister.get(i)));
//			sb.append("\n");
//		}

		return sb.toString();
	}
}
