package controller;

import java.util.regex.Pattern;

import model.*;

public class InstructionIdentifier {

	public static void main(String args[]){
		InstructionIdentifier identifier = new InstructionIdentifier();
		MIPSInstruction thing = identifier.identify("DADDU R3, R1, R1");
		System.out.println(Long.toBinaryString(thing.getOpcode()));
		System.out.println(thing.getOpcodeInBinary());		
		
	
	}
	
	public InstructionIdentifier(){
		
	}
	
	public MIPSInstruction identify(String instruction) {
		MIPSInstruction MIPSInst = null;
		
		String inst[] = instruction.trim().split(" ");
		String param[] = instruction.substring(inst[0].length()).trim().split(",");
		
		int regs[] = new int[3];
		int regsFound = 0;
		String label;
		
		String temp;
		for (int i = 0, j = 0; i < param.length; ++i) {
			//System.out.println("param: "+param[i]);
			
			temp = param[i].trim();
			// TODO: check if any excess will be considered
			if (Pattern.matches("[RrFf][1-3]?[0-9]", temp)) {
				regs[j++] = Integer.parseInt(temp.substring(1));
				regsFound++;
			} else if (Pattern.matches("[A-z0-9]{1,}([RrFf][1-3]?[0-9])", temp)) {
				label = temp.split("(")[0];
				regs[j++] = Integer.parseInt(temp.substring(0, temp.length() - 1).split(")")[0].substring(1));
				regsFound++;
			} else if (i == param.length - 1 && Pattern.matches("[A-z0-9]{1,}", temp)) {
				label = temp;
			}
		}

		switch (inst[0]) {
		case "DADDU": 	MIPSInst = new RTypeInstruction(0,regs[1],regs[2],regs[0],0,RTypeInstruction.DADDU); break;
		case "DMULT": 	MIPSInst = new RTypeInstruction(0,regs[1],regs[2],0,0,RTypeInstruction.DMULT); break;
		case "OR":		MIPSInst = new RTypeInstruction(0,regs[1],regs[2],regs[0],0,RTypeInstruction.OR); break;
		case "SLT":		MIPSInst = new RTypeInstruction(0,regs[1],regs[2],regs[0],0,RTypeInstruction.SLT); break;
		case "ADD.S":	MIPSInst = new RTypeInstruction(RTypeInstruction.OP,RTypeInstruction.EXTOP,regs[2],regs[1],regs[0],RTypeInstruction.ADDS); break;
		case "MUL.S":	MIPSInst = new RTypeInstruction(RTypeInstruction.OP,RTypeInstruction.EXTOP,regs[2],regs[1],regs[0],RTypeInstruction.MULS); break;
		
		//Change the values that are being passed
		case "DSLL":	MIPSInst = new RTypeInstruction(0,regs[1],regs[2],regs[0],0,RTypeInstruction.DSLL); break;
		
		// I -type // fix for offset
		case "BEQ":		MIPSInst = new ITypeInstruction(ITypeInstruction.BEQ, regs[0], regs[1], regs[2]); break;
		case "LW":		MIPSInst = new ITypeInstruction(ITypeInstruction.LW, regs[0], regs[1], regs[2]); break;
		case "LWU":		MIPSInst = new ITypeInstruction(ITypeInstruction.LWU, regs[0], regs[1], regs[2]); break;
		case "SW":		MIPSInst = new ITypeInstruction(ITypeInstruction.SW, regs[0], regs[1], regs[2]); break;
		
		
		case "ANDI":	MIPSInst = new ITypeInstruction(ITypeInstruction.ANDI, regs[0], regs[1], regs[2]); break;
		case "DADDIU":	MIPSInst = new ITypeInstruction(ITypeInstruction.DADDIU, regs[0], regs[1], regs[2]); break;
		
		//Change the values that are being passed
		case "L.S":		MIPSInst = new ITypeInstruction(ITypeInstruction.LS, regs[0], regs[1], regs[2]); break;
		case "S.S": 	MIPSInst = new ITypeInstruction(ITypeInstruction.SS, regs[0], regs[1], regs[2]); break;
		
		// j - type
		case "J": 		MIPSInst = new JTypeInstruction(JTypeInstruction.J, regs[0]); break;
		}

		return MIPSInst;
	}
}
