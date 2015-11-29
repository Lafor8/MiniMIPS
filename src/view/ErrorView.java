package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ErrorView extends JPanel{
	
	TitledBorder title = BorderFactory.createTitledBorder("Error");
	
	public ErrorView(){
		setBorder(title);
	}

}
