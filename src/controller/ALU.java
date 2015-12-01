package controller;

import java.math.BigInteger;

import model.ITypeInstruction;
import model.MIPSInstruction;
import model.RTypeInstruction;
import utilities.MiniMipsUtilities;

public class ALU {

	public BigInteger apply(BigInteger param1, BigInteger param2, MIPSInstruction Instruction) {
		BigInteger answer = BigInteger.valueOf(0);
		int op;
		String IR = Instruction.getOpcodeInBinary();
		if (IR.substring(0, 6).equals("000000"))
			op = Integer.parseInt(IR.substring(26, 32), 2);
		else
			op = Integer.parseInt(IR.substring(0, 6), 2);

		System.err.println("ANSWER 1: " + Instruction + " " + op + " " + param1 + " " + param2);

		switch (op) {
		case RTypeInstruction.DADDU:
		case ITypeInstruction.DADDIU:
			answer = param1.add(param2);
			break;
		case RTypeInstruction.DMULT:
			answer = param1.multiply(param2);
			break;
		// value of the answer should be placed in HI n LOW
		case RTypeInstruction.OR:
			answer = param1.or(param2);
			break;
		case RTypeInstruction.SLT:
			if (param1.compareTo(param2) == -1)
				answer = BigInteger.valueOf(1);
			else
				answer = BigInteger.valueOf(0);
			break;
		case ITypeInstruction.ANDI:
			answer = param1.and(param2);
			break;

		case RTypeInstruction.OP:
			int func = Integer.parseInt(IR.substring(26, 32), 2);
			if (func == RTypeInstruction.ADDS) {
				float v1 = Float.intBitsToFloat(Integer.parseUnsignedInt(MiniMipsUtilities.getPaddedHex64(param1).substring(8), 16));
				float v2 = Float.intBitsToFloat(Integer.parseUnsignedInt(MiniMipsUtilities.getPaddedHex64(param2).substring(8), 16));

				answer = new BigInteger(Integer.toHexString(Float.floatToIntBits(v1 + v2)), 16);
				// System.err.println("ANSWER: " + v1 + " " + v2 + " " +
				// MiniMipsUtilities.getPaddedHex(answer) + " " +
				// Integer.toHexString(Float.floatToIntBits(v1 + v2)));
				// answer = BigInteger.ZERO;
			} else if (func == RTypeInstruction.MULS) {
				float v1 = Float.intBitsToFloat(Integer.parseUnsignedInt(MiniMipsUtilities.getPaddedHex64(param1).substring(8), 16));
				float v2 = Float.intBitsToFloat(Integer.parseUnsignedInt(MiniMipsUtilities.getPaddedHex64(param2).substring(8), 16));

				answer = new BigInteger(Integer.toHexString(Float.floatToIntBits(v1 * v2)), 16);
				 System.err.println("ANSWER: " + MiniMipsUtilities.getPaddedHex(answer));
				// answer = BigInteger.ZERO;
			}

			break;
		case RTypeInstruction.DSLL:
			String str = MiniMipsUtilities.getPaddedHex(param2).substring(13, 15);
			long temp = Long.parseLong(str, 16) & 0x7CL;
			param2 = BigInteger.valueOf(temp / 4);

			answer = param1.multiply(BigInteger.valueOf(2).pow(Integer.parseInt(param2.toString())));
			break;

		// TODO: Double check these all
		case ITypeInstruction.LW:
			answer = param1.add(param2);
			break;

		case ITypeInstruction.SW:
			answer = param1.add(param2);
			break;

		case ITypeInstruction.LWU:
			answer = param1.add(param2);
			break;

		case ITypeInstruction.SS:
			answer = param1.add(param2);
			break;

		case ITypeInstruction.LS:
			answer = param1.add(param2);
			break;

		// TODO: LW, LWU, SW, L.S, S.S, ADD.S, MUL.S

		// Double check whether all the alu operations work
		}
		return answer;
	}
}
