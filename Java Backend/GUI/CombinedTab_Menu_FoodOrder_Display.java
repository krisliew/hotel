package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_Menu_FoodOrder_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_Menu_FoodOrder_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		Food_Order_Display FoodOrderTab = new Food_Order_Display();
		tabbedPane.addTab("Food Orders", null, FoodOrderTab, null);
	
		Menu_Display MenuTab = new Menu_Display();
		tabbedPane.addTab("Menu", null, MenuTab, null);
		
		


	}

}
