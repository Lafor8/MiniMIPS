package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import view.MainFrame;
import view.RegistersView;

import model.MIPSInstruction;

public class MiniMipsController {

	MainFrame mainFrame;
	PipelinedDatapath sequentialDatapath;

	public MiniMipsController() {
		mainFrame = new MainFrame();
		sequentialDatapath = new PipelinedDatapath();
	}

	public void run() {

		mainFrame.refreshRegisters(sequentialDatapath.registers);
		// frame.refreshCodeSegment(mipsInst);
		mainFrame.refreshDataSegment(sequentialDatapath.dataMemory);
		mainFrame.setVisible(true);

	}
}
