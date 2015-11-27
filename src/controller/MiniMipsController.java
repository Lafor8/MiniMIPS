package controller;

import java.io.File;
import java.io.FileNotFoundException;
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
				String paddedInst = String.format("%1$-" + 30 + "s", inst.getInstruction());
				System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
			}
		}
	}
}
