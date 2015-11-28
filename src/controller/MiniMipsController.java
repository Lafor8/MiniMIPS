package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import model.MIPSInstruction;

public class MiniMipsController {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("Data/sampleCode.txt"));

		InstructionIdentifier identifier = new InstructionIdentifier();
		// ArrayList<MIPSInstruction> instructions = new ArrayList<>();

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<MIPSInstruction> mipsInst;

		while (in.hasNextLine()) {
			String line = in.nextLine();

			if (line.length() > 0)
				lines.add(line);
		}

		mipsInst = identifier.parseInstructions(lines);

		for (MIPSInstruction inst : mipsInst) {
			if (inst != null) {
				String paddedInst = String.format("%1$-" + 30 + "s", inst.instruction);
				System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
			}
		}

		SequentialDatapath sequentialDatapath = new SequentialDatapath();

		sequentialDatapath.loadInstructions(mipsInst);

		sequentialDatapath.registers.setR(BigInteger.valueOf(1), BigInteger.valueOf(12));
		sequentialDatapath.registers.setR(BigInteger.valueOf(2), BigInteger.valueOf(10));

		// for (int i = 0; i < Math.min(1, mipsInst.size()); ++i)
		// sequentialDatapath.runOneCycle();

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		

		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		sequentialDatapath.IF();
		sequentialDatapath.ID();
		sequentialDatapath.EX();
		sequentialDatapath.MEM();
		sequentialDatapath.WB();
		
		System.out.println();

		System.out.println(PipelineMapManager.getInstance().toString());

		System.out.println();

		System.out.println(sequentialDatapath.registers.toString());

		
		System.out.println();

		System.out.println(sequentialDatapath.dataMemory.toString());
	}
}
