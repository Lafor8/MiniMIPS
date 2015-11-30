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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CodeView extends JPanel {
	
	TitledBorder title = BorderFactory.createTitledBorder("Code");
	
	private JTextPane codeInputTextPane;
	

	
	public CodeView(){
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
		scrollPane.setViewportView(codeInputTextPane);
		add(btnCompile, "cell 0 1,alignx right,aligny bottom");
	}

	public void checkInput(){
		Scanner in = new Scanner(codeInputTextPane.getText());

		InstructionIdentifier identifier = new InstructionIdentifier();

		ArrayList<String> lines = new ArrayList<>();
		ArrayList<MIPSInstruction> mipsInst;

		while (in.hasNextLine()) {
			String line = in.nextLine();

			if (line.length() > 0 && !line.matches("//.*")) {
				lines.add(line);
			}
		}

		mipsInst = identifier.parseInstructions(lines);

		for (MIPSInstruction inst : mipsInst) {
			if (inst != null) {
				String paddedInst = String.format("%1$-" + 30 + "s", inst.instruction);
				System.out.println(paddedInst + " - " + inst.getOpcodeInHex());
			}
		}
		
		
		System.out.println();
	}
}
