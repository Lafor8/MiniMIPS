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
import controller.MiniMipsController;
import controller.Registers;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	// tonm
	private JPanel contentPane;
	private ErrorView error;
	private InteractiveForm data;
	private RegistersView register1;

	private CodeSegmentView codeSegment;
	private PipelineMapView pipelineMap;
	private DataSegmentView dataSegment;
	private InternalRegistersView internalRegisters;
	RegistersView register2;
	private JButton btnExecuteAll;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	// } catch (Throwable e) {
	// e.printStackTrace();
	// }
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainFrame frame = new MainFrame();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE));

		JPanel codePanel = new JPanel();
		tabbedPane.addTab("Code Tab", null, codePanel, null);
		error = new ErrorView();
		data = new InteractiveForm();
		register1 = new RegistersView();

		CodeView codeView = new CodeView();

		GroupLayout gl_codePanel = new GroupLayout(codePanel);
		gl_codePanel.setHorizontalGroup(gl_codePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_codePanel.createSequentialGroup()
						.addGroup(gl_codePanel.createParallelGroup(Alignment.LEADING).addGroup(gl_codePanel.createSequentialGroup().addGap(15).addComponent(codeView, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_codePanel.createSequentialGroup().addContainerGap().addComponent(error, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(data, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(register1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE).addGap(279)));
		gl_codePanel.setVerticalGroup(gl_codePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_codePanel.createSequentialGroup()
						.addGroup(gl_codePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_codePanel.createSequentialGroup().addContainerGap()
										.addGroup(gl_codePanel.createParallelGroup(Alignment.BASELINE).addComponent(data, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE).addComponent(register1, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_codePanel.createSequentialGroup().addGap(14).addComponent(codeView, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE).addGap(28).addComponent(error, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))).addGap(74)));
		error.setLayout(new GridLayout(1, 0, 0, 0));
		register1.setLayout(new GridLayout(1, 0, 0, 0));
		data.setLayout(new GridLayout(1, 0, 0, 0));
		codePanel.setLayout(gl_codePanel);

		JPanel ExecutionPanel = new JPanel();
		tabbedPane.addTab("Execution Tab", null, ExecutionPanel, null);

		codeSegment = new CodeSegmentView();
		pipelineMap = new PipelineMapView();
		dataSegment = new DataSegmentView();
		internalRegisters = new InternalRegistersView();
		register2 = new RegistersView();

		JButton btnNewButton = new JButton("Execute One Cycle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeOneCycle();
			}
		});

		btnExecuteAll = new JButton("Execute All");
		btnExecuteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeAllCycles();
			}
		});

		GroupLayout gl_ExecutionPanel = new GroupLayout(ExecutionPanel);
		gl_ExecutionPanel.setHorizontalGroup(
			gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExecutionPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_ExecutionPanel.createSequentialGroup()
							.addComponent(dataSegment, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(internalRegisters, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnExecuteAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(pipelineMap, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(codeSegment, GroupLayout.PREFERRED_SIZE, 959, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(register2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addGap(247))
		);
		gl_ExecutionPanel.setVerticalGroup(
			gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ExecutionPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(codeSegment, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addGap(59)
							.addComponent(btnNewButton)
							.addGap(15)
							.addComponent(btnExecuteAll))
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(pipelineMap, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ExecutionPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addComponent(internalRegisters, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
							.addGap(77))
						.addGroup(gl_ExecutionPanel.createSequentialGroup()
							.addComponent(dataSegment, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_ExecutionPanel.createSequentialGroup()
					.addComponent(register2, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		pipelineMap.setLayout(new GridLayout(1, 0, 0, 0));
		codeSegment.setLayout(new GridLayout(1, 0, 0, 0));
		dataSegment.setLayout(new GridLayout(1, 0, 0, 0));
		register2.setLayout(new GridLayout(1, 0, 0, 0));
		internalRegisters.setLayout(new GridLayout(1, 0, 0, 0));
		ExecutionPanel.setLayout(gl_ExecutionPanel);
		contentPane.setLayout(gl_contentPane);
	}

	public void refreshRegisters(Registers r) {
		register1.removeAll();
		register1.Refresh(r);
		register2.removeAll();
		register2.RefreshNonEditable(r);
	}

	public void refreshInternalRegisters() {
		this.internalRegisters.refreshAll();
	}

	public void refreshCodeSegment(ArrayList<MIPSInstruction> mipsInst) {
		codeSegment.removeAll();
		codeSegment.refresh(mipsInst);
	}

	public void refreshDataSegment(DataMemory dataMemory) {
		//data.removeAll();
		data.refresh(dataMemory);
		dataSegment.removeAll();
		dataSegment.refresh(dataMemory);
	}

	public void refreshErrorView(ArrayList<String> errors) {
		error.refresh(errors);

		if (errors.size() > 0)
			codeSegment.removeAll();
	}

	public void getRegisterValuesFromUI() {
		Registers newValues = register1.getRegisterValuesFromUI();
		refreshRegisters(newValues);
	}

	public void refreshPipeLineView() {
		this.pipelineMap.refresh();
	}

	public void executeOneCycle() {
		MiniMipsController.getInstance().sequentialDatapath.runOneCycle();
		MiniMipsController.getInstance().refreshAll();
	}

	public void executeAllCycles() {

		MiniMipsController.getInstance().sequentialDatapath.run();
		MiniMipsController.getInstance().refreshAll();
	}

	public void getDataSegementValuesFromUI() {
		DataMemory newValues = data.getDataSegementValuesFromUI();
		refreshDataSegment(newValues);
	}

}
