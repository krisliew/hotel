package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_Room_RoomType_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_Room_RoomType_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
	
		Room_Display RoomTab = new Room_Display();
		tabbedPane.addTab("Room", null, RoomTab, null);
		
		RoomType_Display RoomTypeTab = new RoomType_Display();
		tabbedPane.addTab("Room Type", null, RoomTypeTab, null);


	}

}
