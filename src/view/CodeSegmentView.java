package view;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.MIPSInstruction;

public class CodeSegmentView extends JPanel {
	
	public DefaultTableModel codeSegmentModel;
	public JTable codeSegmentTable;
	TitledBorder title = BorderFactory.createTitledBorder("Code Segment");
	
	public CodeSegmentView(){
		setBorder(title);
	}
	
	public void refresh(ArrayList<MIPSInstruction> inst){
		
		String[] columnNames = {"Address","Code","Binary","Hex"};
		codeSegmentModel = new DefaultTableModel(null, columnNames);
		codeSegmentTable = new JTable(codeSegmentModel);
			
		for(MIPSInstruction i: inst){
			addInst(i.toString(), i.getOpcodeInBinary(), i.getOpcodeInHex());
		}

		JScrollPane js=new JScrollPane(codeSegmentTable);
		js.setVisible(true);
		add(js);
	}
	
	public void addInst(String instruction, String binary, String hex){
		String[] a = new String[]{null,instruction, binary, hex};
		codeSegmentModel.addRow(a);
	}

}
