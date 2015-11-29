package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import controller.InternalRegisters;

public class InternalRegistersView extends JPanel {
	
	InternalRegisters ir = new InternalRegisters();
	JTable irTable;
	TitledBorder title = BorderFactory.createTitledBorder("Internal Registers");
	
	public InternalRegistersView(){
		
		setBorder(title);
		
		String[] ColumnNames= {"",""};
		
		Object[][] IRData = {
				{"IF/ID.IR",""},
				{"IF/ID.NPC",""},
				{"PC",""},
				{"",""},
				{"ID/EX.A",""},
				{"ID/EX.B",""},
				{"ID/EX.IMM",""},
				{"ID/EX.IR",""},
				{"ID/EX.NPC",""},
				{"",""},
				{"EX/MEM.ALUOputput",""},
				{"EX/MEM.Cond",""},
				{"EX/MEM.IR",""},
				{"EX/MEM.B",""},
				{"",""},
				{"MEM/WB.LMD",""},
				{"MEM/WB.IR",""},
				{"MEM/WB.ALUOutput",""},
				{"",""},
				{"Rn",""}
		};
		
		irTable = new JTable(IRData,ColumnNames);
		
		//add(irTable);
		
		JScrollPane js=new JScrollPane(irTable);
		js.setVisible(true);
		add(js);
	}

}
