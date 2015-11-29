package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class PipelineMapView extends JPanel {
	
	
	public JTable pipelineMapTable;
	TitledBorder title = BorderFactory.createTitledBorder("Pipeline Map");
	
	public PipelineMapView(){
		setBorder(title);
	}
	
	public void refresh(){
		
		
		
		
		JScrollPane js = new JScrollPane(pipelineMapTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVisible(true);
		add(js);
		}
}
