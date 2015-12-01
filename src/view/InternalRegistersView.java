package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utilities.MiniMipsUtilities;

import controller.InternalRegisters;
import controller.MiniMipsController;
import controller.PipelinedDatapath;

public class InternalRegistersView extends JPanel {

	InternalRegisters ir = new InternalRegisters();
	JTable irTable;
	TitledBorder title = BorderFactory.createTitledBorder("Internal Registers");

	public InternalRegistersView() {

		setBorder(title);

		String[] ColumnNames = { "", "" };

		Object[][] IRData = { { "IF/ID.IR", "" }, { "IF/ID.NPC", "" }, { "PC", "" }, { "", "" }, { "ID/EX.A", "" }, { "ID/EX.B", "" }, { "ID/EX.IMM", "" }, { "ID/EX.IR", "" },
				{ "ID/EX.NPC", "" }, { "", "" }, { "EX/MEM.ALUOputput", "" }, { "EX/MEM.Cond", "" }, { "EX/MEM.IR", "" }, { "EX/MEM.B", "" }, { "", "" }, { "MEM/WB.LMD", "" },
				{ "MEM/WB.IR", "" }, { "MEM/WB.ALUOutput", "" }, { "", "" }, { "Rn", "" } };

		irTable = new JTable(IRData, ColumnNames);

		// add(irTable);
		irTable.getTableHeader().setReorderingAllowed(false);

		JScrollPane js = new JScrollPane(irTable);
		js.setVisible(true);
		add(js);
	}

	public void refreshAll() {

		DefaultTableModel model = new DefaultTableModel(){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		List<String> list = new ArrayList<String>();

		list.add("Register");
		list.add("Value");
		model.setColumnIdentifiers(list.toArray());

		String row[] = new String[2];

		PipelinedDatapath dataPath = MiniMipsController.getInstance().sequentialDatapath;

		row[0] = "IF/ID.IR";if(dataPath.if_id.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.if_id.IR.address);
		model.addRow(row);
		row[0] = "IF/ID.NPC";if(dataPath.if_id.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.if_id.NPC);
		model.addRow(row);
		row[0] = "PC";if(dataPath.if_id.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.if_id.NPC);
		model.addRow(row);
		row[0] = "";
		row[1] = "";
		model.addRow(row);
		row[0] = "ID/EX.IR";if(dataPath.id_ex.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.id_ex.IR.address);
		model.addRow(row);
		row[0] = "ID/EX.A";if(dataPath.id_ex.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.id_ex.A);
		model.addRow(row);
		row[0] = "ID/EX.B";if(dataPath.id_ex.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.id_ex.B);
		model.addRow(row);
		row[0] = "ID/EX.IMM";if(dataPath.id_ex.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.id_ex.IMM);
		model.addRow(row);
		row[0] = "";
		row[1] = "";
		model.addRow(row);
		row[0] = "EX/MEM.IR";if(dataPath.ex_mem.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.ex_mem.IR.address);
		model.addRow(row);
		row[0] = "EX/MEM.ALUOutput";if(dataPath.ex_mem.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.ex_mem.ALUOutput);
		model.addRow(row);
		row[0] = "EX/MEM.B";if(dataPath.ex_mem.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.ex_mem.B);
		model.addRow(row);
		row[0] = "EX/MEM.Cond";if(dataPath.ex_mem.IR != null)
		row[1] = (dataPath.ex_mem.Cond ? 1 : 0) + "";
		model.addRow(row);
		row[0] = "";
		row[1] = "";
		model.addRow(row);
		row[0] = "MEM/WB.IR";if(dataPath.mem_wb.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.mem_wb.IR.address);
		model.addRow(row);
		row[0] = "MEM/WB.ALUoutput";if(dataPath.mem_wb.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.mem_wb.ALUOutput);
		model.addRow(row);
		row[0] = "MEM/WB.LMD";if(dataPath.mem_wb.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.mem_wb.LMD);
		model.addRow(row);
		row[0] = "MEM[ALUOutput]";if(dataPath.mem_wb.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.ex_mem.B);
		model.addRow(row);
		row[0] = "";
		row[1] = "";
		model.addRow(row);
		row[0] = "Registers Affected ";if(dataPath.mem_wb.IR != null)
		row[1] = MiniMipsUtilities.getPaddedHex(dataPath.mem_wb.NPC);
		model.addRow(row);

		irTable.setModel(model);
		irTable.getTableHeader().setReorderingAllowed(false);

	}

}
