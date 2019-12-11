package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_Amenity_AmenitiesUsageLog_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_Amenity_AmenitiesUsageLog_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		Amenities_Usage_Log_Display AmenitiesUsageLogTab = new Amenities_Usage_Log_Display();
		tabbedPane.addTab("Amenities Usage Log", null, AmenitiesUsageLogTab, null);
	
		Amenities_Display AmenitiesTab = new Amenities_Display();
		tabbedPane.addTab("Amenities", null, AmenitiesTab, null);
		
		


	}

}
