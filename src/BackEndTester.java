import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import model.MIPSInstruction;
import controller.InstructionIdentifier;
import controller.PipelineMapManager;
import controller.PipelinedDatapath;


public class BackEndTester {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("Data/sampleCode.txt"));

		InstructionIdentifier identifier = new InstructionIdentifier();
		// ArrayList<MIPSInstruction> instructions = new ArrayList<>();

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<MIPSInstruction> mipsInst;

		while (in.hasNextLine()) {
			String line = in.nextLine();
				lines.add(line);
		}

		mipsInst = identifier.parseInstructions(lines);

		for (MIPSInstruction inst : mipsInst) {
			if (inst != null) {
				String paddedInst = String.format("%1$-" + 30 + "s", inst.instruction);
				System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
			}
		}

		System.out.println();
		PipelinedDatapath sequentialDatapath = new PipelinedDatapath();

		sequentialDatapath.loadInstructions(mipsInst);

		sequentialDatapath.registers.setR(BigInteger.valueOf(1), BigInteger.valueOf(3));
		sequentialDatapath.registers.setR(BigInteger.valueOf(2), BigInteger.valueOf(2));
		sequentialDatapath.registers.setR(BigInteger.valueOf(3), BigInteger.valueOf(3));

		sequentialDatapath.registers.setF(BigInteger.valueOf(1), BigInteger.valueOf(Long.parseLong("3f800000", 16)));
		sequentialDatapath.registers.setF(BigInteger.valueOf(2), BigInteger.valueOf(Long.parseLong("c0000000", 16)));

		sequentialDatapath.dataMemory.setDataToMemory(BigInteger.valueOf(3), BigInteger.valueOf(17));
		sequentialDatapath.dataMemory.setDataToMemory(BigInteger.valueOf(10), BigInteger.valueOf(18));
		sequentialDatapath.dataMemory.setDataToMemory(BigInteger.valueOf(24), BigInteger.valueOf(19));

		// for (int i = 0; i < Math.min(1, mipsInst.size()); ++i)
		// sequentialDatapath.runOneCycle();

		sequentialDatapath.run();

		System.out.println();

		System.out.println(PipelineMapManager.getInstance().toString());

		System.out.println();

		System.out.println(sequentialDatapath.registers.toString());

		System.out.println();

		System.out.println(sequentialDatapath.dataMemory.toString());

		// MainFrame frame = new MainFrame();
		// frame.refreshRegisters(sequentialDatapath.registers);
		// frame.refreshCodeSegment(mipsInst);
		// frame.refreshDataSegment(sequentialDatapath.dataMemory);
		// frame.setVisible(true);

	}

}
