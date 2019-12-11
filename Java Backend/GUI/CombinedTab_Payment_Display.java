package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CombinedTab_Payment_Display extends JPanel {

	/**
	 * Create the panel.
	 */
	public CombinedTab_Payment_Display() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 1235, 593);
		add(tabbedPane);
		
		Payment_Display PaymentTab = new Payment_Display();
		tabbedPane.addTab("Payment", null, PaymentTab, null);
	
		Payment_Method_Display PaymentMethodTab = new Payment_Method_Display();
		tabbedPane.addTab("Payment Method", null, PaymentMethodTab, null);
		
		Cash_Display CashTab = new Cash_Display();
		tabbedPane.addTab("Cash", null, CashTab, null);
		
		Cheque_Display ChequeTab = new Cheque_Display();
		tabbedPane.addTab("Cheque", null, ChequeTab, null);
		
		Credit_Card_Display CreditCardTab = new Credit_Card_Display();
		tabbedPane.addTab("Credit Card", null, CreditCardTab, null);
		
		


	}

}
