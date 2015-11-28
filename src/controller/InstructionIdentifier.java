package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import model.*;

public class InstructionIdentifier {

	public InstructionIdentifier() {
		labelsMap = new HashMap<>();
		InternalRegisters r = null;
		
	}

	HashMap<String, Integer> labelsMap;

	public ArrayList<MIPSInstruction> parseInstructions(ArrayList<String> instList) {
		ArrayList<String> normalizedInstList = new ArrayList<>();
		ArrayList<MIPSInstruction> mipsInstList = new ArrayList<>();

		String normalizedInst;

		// remove labels & affix address
		for (int i = 0; i < instList.size(); ++i) {
			normalizedInst = instList.get(i).trim();

			if (normalizedInst.contains(":")) {
				String tokens[] = normalizedInst.split(":");
				labelsMap.put(tokens[0].trim(), i * 4);
				normalizedInst = tokens[1].trim();
			}
			normalizedInst = (i * 4) + ":" + normalizedInst;

//			System.out.println(normalizedInst);

			normalizedInstList.add(normalizedInst);
		}

//		System.out.println();

		// display label map
		for (Entry<String, Integer> item : labelsMap.entrySet()) {
			System.out.println(item.getKey() + ": " + item.getValue());
		}

		System.out.println();

		// create mips objects
		for (String inst : normalizedInstList) {
			mipsInstList.add(this.identifyInstruction(inst));
		}

		return mipsInstList;
	}

	private MIPSInstruction identifyInstruction(String instruction) {
		MIPSInstruction MIPSInst = null;

		String segments[] = instruction.split(":");

		BigInteger address = BigInteger.valueOf(Long.parseLong(segments[0]));

		instruction = segments[1];

		String inst[] = instruction.trim().split(" ", 2);
		String param[] = inst[1].split(",");

		int regs[] = new int[3];
		int regsFound = 0;
		String label = "";

		boolean isFloatInst = false;

		String temp;
		
		
		for (int i = 0, j = 0; i < param.length; ++i) {
			// System.out.println("param: "+param[i]);

			temp = param[i].trim();
			// TODO: check if any excess will be considered
			if (Pattern.matches("[RrFf][1-3]?[0-9]", temp)) {
				regs[j++] = Integer.parseInt(temp.substring(1));
				regsFound++;

			} else if (Pattern.matches("[A-z0-9]{1,}[ ]?\\([Rr][1-3]?[0-9]\\)", temp)) {
				segments = temp.split("\\(");
				label = segments[0].trim();
				temp = segments[1].trim();
				
				regs[j++] = Integer.parseInt((temp.substring(1, temp.length() - 1)).trim());
				regsFound++;

			} else if (i == param.length - 1 && Pattern.matches("[A-z0-9]{1,}", temp)) {

				label = temp;
			} else {

				System.out.println("ERROR: " + temp);
			}
		}

		switch (inst[0]) {
		case "DADDU":
			MIPSInst = new RTypeInstruction(0, regs[1], regs[2], regs[0], 0, RTypeInstruction.DADDU);
			break;
		case "DMULT":
			MIPSInst = new RTypeInstruction(0, regs[0], regs[1], 0, 0, RTypeInstruction.DMULT);
			break;
		case "OR":
			MIPSInst = new RTypeInstruction(0, regs[1], regs[2], regs[0], 0, RTypeInstruction.OR);
			break;
		case "SLT":
			MIPSInst = new RTypeInstruction(0, regs[1], regs[2], regs[0], 0, RTypeInstruction.SLT);
			break;
		case "ADD.S":
			MIPSInst = new RTypeInstruction(RTypeInstruction.OP, RTypeInstruction.EXTOP, regs[2], regs[1], regs[0], RTypeInstruction.ADDS);
			break;
		case "MUL.S":
			MIPSInst = new RTypeInstruction(RTypeInstruction.OP, RTypeInstruction.EXTOP, regs[2], regs[1], regs[0], RTypeInstruction.MULS);
			break;

		// Change the values that are being passed
		case "DSLL":
			MIPSInst = new RTypeInstruction(0, 0, regs[1], regs[0], Integer.parseInt(label), RTypeInstruction.DSLL);

			break;

		// I -type // fix for offset
		case "BEQ":
			MIPSInst = new ITypeInstruction(ITypeInstruction.BEQ, regs[0], regs[1], (int) (this.labelsMap.get(label) - Long.valueOf(address.toString()) - 4 >> 2));
			break;
		case "LW":
			MIPSInst = new ITypeInstruction(ITypeInstruction.LW, regs[1], regs[0], Integer.parseInt(label,16));
			break;
		case "LWU":
			MIPSInst = new ITypeInstruction(ITypeInstruction.LWU, regs[1], regs[0], Integer.parseInt(label,16));
			break;
		case "SW":
			MIPSInst = new ITypeInstruction(ITypeInstruction.SW, regs[1], regs[0], Integer.parseInt(label,16));
			break;

		case "ANDI":
			MIPSInst = new ITypeInstruction(ITypeInstruction.ANDI, regs[1], regs[0], Integer.parseInt(label));
			break;
		case "DADDIU":
			MIPSInst = new ITypeInstruction(ITypeInstruction.DADDIU, regs[1], regs[0], Integer.parseInt(label));
			break;

		// Change the values that are being passed
		case "L.S":
			MIPSInst = new ITypeInstruction(ITypeInstruction.LS, regs[1], regs[0],Integer.parseInt(label,16));
			break;
		case "S.S":
			MIPSInst = new ITypeInstruction(ITypeInstruction.SS, regs[1], regs[0], Integer.parseInt(label,16));
			break;

		// j - type
		case "J":
			MIPSInst = new JTypeInstruction(JTypeInstruction.J, (int) (this.labelsMap.get(label) >> 2));
			break;
		}

		if (MIPSInst == null)
			MIPSInst = new MIPSInstruction();

		MIPSInst.instruction = instruction;
		MIPSInst.address = address;

		return MIPSInst;
	}
}
