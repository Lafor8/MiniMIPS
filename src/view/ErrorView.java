package view;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ErrorView extends JPanel{
	
	TitledBorder title = BorderFactory.createTitledBorder("Error");
	private JTextPane textPane;
	
	public ErrorView(){
		setBorder(title);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
	}
	
	public void setErrorText(ArrayList<String> errors){
		StringBuilder sb = new StringBuilder();
		
		for(String str : errors){
			sb.append(str);
			sb.append("\n");
		}
		
		textPane.setText(sb.toString());
	}

}
