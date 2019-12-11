package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_Services_ServicesUsageLog_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_Services_ServicesUsageLog_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		Services_Usage_Log_Display ServicesUsageLogTab = new Services_Usage_Log_Display();
		tabbedPane.addTab("Services Usage Log", null, ServicesUsageLogTab, null);
	
		Services_Display ServicesTab = new Services_Display();
		tabbedPane.addTab("Services", null, ServicesTab, null);
		
		


	}

}
