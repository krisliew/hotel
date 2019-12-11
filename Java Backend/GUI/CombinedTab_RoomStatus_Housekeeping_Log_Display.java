package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_RoomStatus_Housekeeping_Log_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_RoomStatus_Housekeeping_Log_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		RoomStatus_Display RoomStatusTab = new RoomStatus_Display();
		tabbedPane.addTab("Room Status", null, RoomStatusTab, null);
	
		
		Housekeeping_Log_Display HousekeepingLogTab = new Housekeeping_Log_Display();
		tabbedPane.addTab("Housekeeping Log", null, HousekeepingLogTab, null);
		
		
		Housekeeping_Display HousekeepingTab = new Housekeeping_Display();
		tabbedPane.addTab("Housekeeping", null, HousekeepingTab, null);
		
		


	}

}
