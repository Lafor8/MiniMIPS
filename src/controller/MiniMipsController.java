package controller;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.UIManager;

import view.MainFrame;
import view.RegistersView;

import model.MIPSInstruction;

public class MiniMipsController {

	// UI Components
	public MainFrame mainFrame;
	
	// Backend Components
	public PipelinedDatapath sequentialDatapath;

	// Main Controller
	public static MiniMipsController miniMipsController;

	
	public MiniMipsController() {
		mainFrame = new MainFrame();
		sequentialDatapath = new PipelinedDatapath();
		miniMipsController = this;
	}

	public static MiniMipsController getInstance() {
		return miniMipsController;
	}

	public void run() {
		refreshAll();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		mainFrame.setVisible(true);

	}
	
	public void refreshAll(){
		mainFrame.refreshRegisters(sequentialDatapath.registers);
		//mainFrame.refreshCodeSegment(mipsInst);
		//mainFrame.refreshDataSegment(sequentialDatapath.dataMemory);
		mainFrame.refreshInternalRegisters();
	}
}
