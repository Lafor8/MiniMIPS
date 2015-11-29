package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CodeView extends JPanel {
	
	TitledBorder title = BorderFactory.createTitledBorder("Code");
	
	public CodeView(){
		setBorder(title);
	}

}
