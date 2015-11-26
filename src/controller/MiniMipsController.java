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

		while (in.hasNextLine()) {
			String line = in.nextLine();

			MIPSInstruction inst;

			if (line.trim().length() > 0) {
				inst = identifier.identifyInstruction(line);

				if (inst != null) {

					String paddedInst = String.format("%1$-" + 30 + "s", inst.getInstruction());
					System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
				}
			} else
				System.out.println();
		}

		// for (MIPSInstruction inst : instructions) {
		// if (inst != null)
		// System.out.println(inst.getInstruction() + space + " - " + inst.getOpcodeInHex());
		// }
	}

}
