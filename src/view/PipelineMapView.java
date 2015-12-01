package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class PipelineMapView extends JPanel {
	
	
	public JTable pipelineMapTable;
	TitledBorder title = BorderFactory.createTitledBorder("Pipeline Map");
	private JTable table;
	
	public PipelineMapView(){
		setBorder(title);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public void refresh(){
		
		
		
		
		JScrollPane js = new JScrollPane(pipelineMapTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVisible(true);
		add(js);
		}
}
