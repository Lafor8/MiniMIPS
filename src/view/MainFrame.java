package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.MIPSInstruction;

import controller.DataMemory;
import controller.Registers;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private CodeView code;
	private ErrorView error;
	private DataSegmentView data;
	private RegistersView register1;
	
	private CodeSegmentView codeSegment;
	private PipelineMapView pipelineMap;
	private DataSegmentView dataSegment;
	private InternalRegistersView internalRegisters;
	RegistersView register2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
		);
		
		JPanel CodePanel = new JPanel();
		tabbedPane.addTab("Code Tab", null, CodePanel, null);
		
		code = new CodeView();	
		error = new ErrorView();
		data = new DataSegmentView();
		register1 = new RegistersView();

		GroupLayout gl_CodePanel = new GroupLayout(CodePanel);
		gl_CodePanel.setHorizontalGroup(
			gl_CodePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CodePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CodePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(error, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(code, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(data, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(register1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_CodePanel.setVerticalGroup(
			gl_CodePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CodePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CodePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CodePanel.createSequentialGroup()
							.addComponent(register1, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(data, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_CodePanel.createSequentialGroup()
							.addComponent(code, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(error, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(21, Short.MAX_VALUE))))
		);
		code.setLayout(new GridLayout(1, 0, 0, 0));
		error.setLayout(new GridLayout(1, 0, 0, 0));
		register1.setLayout(new GridLayout(1, 0, 0, 0));
		data.setLayout(new GridLayout(1, 0, 0, 0));
		CodePanel.setLayout(gl_CodePanel);
		
		JPanel ExecutionPanel = new JPanel();
		tabbedPane.addTab("Execution Tab", null, ExecutionPanel, null);
		
		codeSegment = new CodeSegmentView();
		pipelineMap = new PipelineMapView();
		dataSegment = new DataSegmentView();
		internalRegisters = new InternalRegistersView();
		register2 = new RegistersView();
		
		GroupLayout gl_ExecutionPanel = new GroupLayout(ExecutionPanel);
		gl_ExecutionPanel.setHorizontalGroup(
			gl_ExecutionPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ExecutionPanel.createSequentialGroup()
					.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addComponent(dataSegment, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(internalRegisters, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
						.addComponent(pipelineMap, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
						.addComponent(codeSegment, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(register2, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		gl_ExecutionPanel.setVerticalGroup(
			gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExecutionPanel.createSequentialGroup()
					.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addComponent(codeSegment, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pipelineMap, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(internalRegisters, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
								.addComponent(dataSegment, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)))
						.addComponent(register2, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pipelineMap.setLayout(new GridLayout(1, 0, 0, 0));
		codeSegment.setLayout(new GridLayout(1, 0, 0, 0));
		dataSegment.setLayout(new GridLayout(1, 0, 0, 0));
		register2.setLayout(new GridLayout(1, 0, 0, 0));
		internalRegisters.setLayout(new GridLayout(1, 0, 0, 0));
		ExecutionPanel.setLayout(gl_ExecutionPanel);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void refreshRegisters(Registers r){
		register1.removeAll();
		register1.Refresh(r);
		register2.removeAll();
		register2.Refresh(r);
	}
	
	public void refreshInternalRegisters(){
		
	}

	public void refreshCodeSegment(ArrayList<MIPSInstruction> mipsInst){
		codeSegment.removeAll();
		codeSegment.refresh(mipsInst);
	}
	
	public void refreshDataSegment(DataMemory dataMemory){
		data.removeAll();
		data.refresh(dataMemory);
		dataSegment.removeAll();
		dataSegment.refresh(dataMemory);
	}
	
}
