package view;


import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ErrorView extends JPanel {

	TitledBorder title = BorderFactory.createTitledBorder("Error");
	private JTextPane errorTextPane;

	public ErrorView() {
		setBorder(title);
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");

		errorTextPane = new JTextPane();
		errorTextPane.setText("asda");
		scrollPane.setViewportView(errorTextPane);
	}

	public void refresh(ArrayList<String> errors) {
		StringBuilder sb = new StringBuilder();

		for (String str : errors) {
			sb.append(str);
			sb.append("\n");
		}
		System.err.println(sb.toString());
		System.err.println(errorTextPane.getText());
		errorTextPane.setText(sb.toString());
		errorTextPane.updateUI();
		errorTextPane.revalidate();
		errorTextPane.validate();
		errorTextPane.removeAll();
		
		System.err.println("GET TEXT: "+ errorTextPane.getText());
		System.err.println(sb.toString());
	}

}
