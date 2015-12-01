package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.PipelineMapManager;
import net.miginfocom.swing.MigLayout;

public class PipelineMapView extends JPanel {

	public JTable pipelineMapTable;
	TitledBorder title = BorderFactory.createTitledBorder("Pipeline Map");
	private JTable table;
	private JScrollPane scrollPane;

	public PipelineMapView() {
		setBorder(title);
		setLayout(new MigLayout("", "[grow]", "[grow]"));

		DefaultTableModel dtm = new DefaultTableModel();
		String cols[] = { "Instruction", "1" };
		dtm.setColumnIdentifiers(cols);
		// cols[0] = "test1"; cols[1] = "test2";
		// dtm.addRow(cols);

		table = new JTable(dtm);

		scrollPane = new FixedColumnPane(table, 1);

		scrollPane.setViewportView(table);
		add(scrollPane);
	}

	public void refresh() {

		DefaultTableModel dtmPipelineMap = new DefaultTableModel();
		DefaultTableModel dtmInst = new DefaultTableModel();

		String[][] pipelineMap = PipelineMapManager.getInstance().getPipelineMap();
		String[][] instList = PipelineMapManager.getInstance().getInstList();
		if (pipelineMap.length != 0) {
			String[] colHeaders = new String[pipelineMap[0].length];

			for (int i = 0; i < colHeaders.length; ++i)
				colHeaders[i] = (i+1) + "";

			String[] fixedColHeaders = { "Instruction" };

			dtmInst.setColumnIdentifiers(fixedColHeaders);

			dtmPipelineMap.setColumnIdentifiers(colHeaders);

			for (String[] row : pipelineMap) {
				dtmPipelineMap.addRow(row);
			}
			for (String[] row : instList) {
				dtmInst.addRow(row);
			}

			// table.setModel(dtmPipelineMap);

			FixedColumnPane fcp = (FixedColumnPane) scrollPane;

			fcp.fixed.setModel(dtmInst);
			fcp.main.setModel(dtmPipelineMap);
			fcp.resizeTables();
		}
		//
		// JScrollPane js = new JScrollPane(pipelineMapTable,
		// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// js.setVisible(true);
		// add(js);
		// }
	}
}
