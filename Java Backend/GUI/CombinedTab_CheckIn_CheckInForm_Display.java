package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_CheckIn_CheckInForm_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_CheckIn_CheckInForm_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		Check_In_Display CheckInTab = new Check_In_Display();
		tabbedPane.addTab("Check In", null, CheckInTab, null);
	
		Check_In_Form_Display CheckInFormTab = new Check_In_Form_Display();
		tabbedPane.addTab("Check In Form", null, CheckInFormTab, null);
		
		


	}

}
