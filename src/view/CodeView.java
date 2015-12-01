package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import model.MIPSInstruction;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.InstructionIdentifier;
import controller.MiniMipsController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CodeView extends JPanel {

	TitledBorder title = BorderFactory.createTitledBorder("Code");

	private JTextPane codeInputTextPane;

	public CodeView() {
		setBorder(title);

		JButton btnCompile = new JButton("Check");
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkInput();
			}
		});
		setLayout(new MigLayout("", "[421px]", "[218px][28px]"));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "flowy,cell 0 0,grow");

		codeInputTextPane = new JTextPane();
		codeInputTextPane
				.setText("DADDU R10, R1, R2\r\nDMULT R1 , R2\r\nL1:OR R11, R1,R2\r\nSLT R13, R1, R2\r\n\r\n//BEQ R13, R0, L2\r\nLW R14, 0000(R1)\r\nLWU R15, 0008(R2)\r\nSW R1, 0010(R3)\r\nDSLL R16, R1, 2\r\nANDI R17, R1, 12\r\nDADDIU R18, R1, 4\r\n//BEQ R13, R0, L1\r\n\r\nJ L2\r\n\r\nL.S F10, 0018(R4)\r\nS.S F1, 0020 (R5)\r\nL2:ADD.S F11,F1,F2\r\nMUL.S F12, F1,F2");
		scrollPane.setViewportView(codeInputTextPane);
		add(btnCompile, "cell 0 1,alignx right,aligny bottom");
	}

	public void checkInput() {
		Scanner in = new Scanner(codeInputTextPane.getText());

		InstructionIdentifier identifier = new InstructionIdentifier();

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<MIPSInstruction> mipsInst;

		while (in.hasNextLine()) {
			String line = in.nextLine();

			lines.add(line);

		}

		mipsInst = identifier.parseInstructions(lines);

		boolean hasError = false;
		ArrayList<String> errors = new ArrayList<>();

		for (MIPSInstruction inst : mipsInst) {
			if (inst != null) {
				hasError |= !inst.isValid;
				if (!inst.isValid)
					errors.add(inst.errorMsg);
				else {
					String paddedInst = String.format("%1$-" + 30 + "s", inst.instruction);
					System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
				}
			}
		}

		System.out.println();
		System.err.println(hasError);
		System.err.println(errors.size());
		System.err.println(MiniMipsController.getInstance());
		System.err.println(MiniMipsController.getInstance().mainFrame);
		
		
		if (hasError)
			MiniMipsController.getInstance().mainFrame.refreshErrorView(errors);
		else{
			MiniMipsController.getInstance().mainFrame.refreshErrorView(errors);
			MiniMipsController.getInstance().mainFrame.refreshCodeSegment(mipsInst);
			MiniMipsController.getInstance().sequentialDatapath.loadInstructions(mipsInst);
		}
		
		MiniMipsController.getInstance().mainFrame.getRegisterValuesFromUI();
		MiniMipsController.getInstance().mainFrame.getDataSegementValuesFromUI();
	}
}
