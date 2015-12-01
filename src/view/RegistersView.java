	package view;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utilities.MiniMipsUtilities;

import controller.Registers;

public class RegistersView extends JPanel{
	
	public Registers registers = new Registers();
	public DefaultTableModel registerModel;
	public JTable registersTable;
	
	TitledBorder title = BorderFactory.createTitledBorder("Registers");
	
	public RegistersView(){
		setBorder(title);
		registers.Initialize();
		String[] columnNames = {"","Integer", "","Integer"};
		registerModel = new DefaultTableModel(null, columnNames);
		registersTable = new JTable(registerModel);
		
		for(int i = 0; i < 32; i++){
			addR(i, registers.getR(BigInteger.valueOf(i)), registers.getF(BigInteger.valueOf(i)));
		}
		
		String[] hi = new String[]{"HI", MiniMipsUtilities.getPaddedHex128(registers.getHI())};
		String[] low = new String[]{"LOW", MiniMipsUtilities.getPaddedHex128(registers.getLO())};
		registerModel.addRow(new String[]{});
		registerModel.addRow(hi);
		registerModel.addRow(low);
		
		registersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		registersTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(1).setPreferredWidth(145);
		registersTable.getColumnModel().getColumn(2).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(3).setPreferredWidth(145);
		
		JScrollPane js=new JScrollPane(registersTable);
		js.setVisible(true);
		add(js);
		
		add(js);
	}
	
	private void addR(int i, BigInteger b, BigInteger bigInteger){
		String[] a = new String[]{"R"+Integer.toString(i), MiniMipsUtilities.getPaddedHex128(b), "F"+Integer.toString(i), MiniMipsUtilities.getPaddedHex128(bigInteger)};
		registerModel.addRow(a);
	}
	
	public void Refresh(Registers r){
		
		String[] columnNames = {"","Integer", "","Floating"};
		registerModel = new DefaultTableModel(null, columnNames);
		registersTable = new JTable(registerModel);
		
		for(int i = 0; i < 32; i++){
			addR(i, r.getR(BigInteger.valueOf(i)), r.getF(BigInteger.valueOf(i)));
		}
		
		String[] hi = new String[]{"HI", MiniMipsUtilities.getPaddedHex128(registers.getHI())};
		String[] low = new String[]{"LOW", MiniMipsUtilities.getPaddedHex128(registers.getLO())};
		registerModel.addRow(new String[]{});
		registerModel.addRow(hi);
		registerModel.addRow(low);
		
		registersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		registersTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(1).setPreferredWidth(145);
		registersTable.getColumnModel().getColumn(2).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(3).setPreferredWidth(145);
		
		
		JScrollPane js=new JScrollPane(registersTable);
		js.setVisible(true);
		add(js);
		
		add(js);
			
	}
	
	public void RefreshNonEditable(Registers r){
		
		String[] columnNames = {"","Integer", "","Floating"};
		registerModel = new DefaultTableModel(null, columnNames){
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		registersTable = new JTable(registerModel);
		
		for(int i = 0; i < 32; i++){
			addR(i, r.getR(BigInteger.valueOf(i)), r.getF(BigInteger.valueOf(i)));
		}
		
		String[] hi = new String[]{"HI", MiniMipsUtilities.getPaddedHex128(registers.getHI())};
		String[] low = new String[]{"LOW", MiniMipsUtilities.getPaddedHex128(registers.getLO())};
		registerModel.addRow(new String[]{});
		registerModel.addRow(hi);
		registerModel.addRow(low);
		
		registersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		registersTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(1).setPreferredWidth(145);
		registersTable.getColumnModel().getColumn(2).setPreferredWidth(35);
		registersTable.getColumnModel().getColumn(3).setPreferredWidth(145);
		
		
		JScrollPane js=new JScrollPane(registersTable);
		js.setVisible(true);
		add(js);
		
		add(js);
			
	}
	
	
	public Registers getRegisterValuesFromUI(){
		
		Registers newValues = new Registers();
		newValues.Initialize();
		
		for(int row = 0; row < registersTable.getRowCount(); row++) {
			
			if(row == 32){
				
			}
			else if(row > 32){
				switch(row){
				case 33: newValues.setHI( new BigInteger((String) registersTable.getValueAt(row, 1))); break;
				case 34: newValues.setLOW( new BigInteger((String) registersTable.getValueAt(row, 1))); break;
				}
			}
			else{
			    for(int column = 0; column < registersTable.getColumnCount(); column++) {
				       switch(column) {
				            case 1:				              
				                  newValues.setR(BigInteger.valueOf(row), new BigInteger((String) registersTable.getValueAt(row, column)));
				                  break;
				             case 3: 
				            	  newValues.setF(BigInteger.valueOf(row), new BigInteger((String) registersTable.getValueAt(row, column)));
				                  break;
				       }
				    }
			}
		}
		return newValues;
	}
	

}
