package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main_Display extends JFrame {

	private JPanel contentPane;	
	
	private JButton btnReservation;
	private JButton btnRoom;
	private JButton btnHotelDetail;
	private JButton btnServices;
	private JButton btnAmentities;
	private JButton btnPayment;
	private JButton btnGuest;
	private JButton btnRoomStatus;
	private JButton btnCheckIn;
	private JButton btnFoodOrder;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Display frame = new Main_Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	void hotelDetails(JSplitPane splitPane) {// Change to hotelDetails pane	
		HotelDetail_Display hotelDetails = new HotelDetail_Display();
		splitPane.setRightComponent(hotelDetails);
	}
	
	
	void rooms(JSplitPane splitPane) {// Change to rooms pane	
		CombinedTab_Room_RoomType_Display rooms = new CombinedTab_Room_RoomType_Display();
		splitPane.setRightComponent(rooms);
	}
	
	void guests(JSplitPane splitPane) {// Change to guests pane	
		Guest_Display guests = new Guest_Display();
		splitPane.setRightComponent(guests);
	}
	
	void amenities(JSplitPane splitPane) {// Change to amenities pane	
		CombinedTab_Amenity_AmenitiesUsageLog_Display amenities = new CombinedTab_Amenity_AmenitiesUsageLog_Display();
		splitPane.setRightComponent(amenities);
	}
	
	void services(JSplitPane splitPane) {// Change to services pane	
		CombinedTab_Services_ServicesUsageLog_Display services = new CombinedTab_Services_ServicesUsageLog_Display();
		splitPane.setRightComponent(services);
	}
	
	void reservations(JSplitPane splitPane) {// Change to reservations pane	
		Reservation_Display reservations = new Reservation_Display();
		splitPane.setRightComponent(reservations);
	}
	
	
	void food_order(JSplitPane splitPane) {// Change to food_order pane	
		CombinedTab_Menu_FoodOrder_Display food_orders = new CombinedTab_Menu_FoodOrder_Display();
		splitPane.setRightComponent(food_orders);
	}
	
	
	void check_in(JSplitPane splitPane) {// Change to check_in pane	
		CombinedTab_CheckIn_CheckInForm_Display checkIn = new CombinedTab_CheckIn_CheckInForm_Display();
		splitPane.setRightComponent(checkIn);
	}
	
	void room_status(JSplitPane splitPane) {// Change to room_status pane	
		CombinedTab_RoomStatus_Housekeeping_Log_Display roomStatus = new CombinedTab_RoomStatus_Housekeeping_Log_Display();
		splitPane.setRightComponent(roomStatus);
	}
	
	void payment(JSplitPane splitPane) {// Change to payment pane	
		CombinedTab_Payment_Display payment = new CombinedTab_Payment_Display();
		splitPane.setRightComponent(payment);
	}

	/**
	 * Create the frame.
	 */
	public Main_Display() {
		
		
		setTitle("Giant Forest Inn Hotel");
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window and logout?", "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	System.exit(0);
		        }else {
		        	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        }
		    }
		});
		
		setBounds(100, 100, 1598, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(5, 5, 1584, 709);
		contentPane.add(splitPane);
		
		JPanel panelLeftNav = new JPanel();
		splitPane.setLeftComponent(panelLeftNav);
		panelLeftNav.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		/*
		 * 
		 * Website: Stackoverflow
		 * Title: Adjusting size of JPanels in JSplitPane
		 * Author: Lee Meador
		 * Released Date: 02/08/13
		 * Referred Date: 10/12/19
		 * URL: hhttps://stackoverflow.com/questions/18021631/adjusting-size-of-jpanels-in-jsplitpane
		 * 
		 * */
		splitPane.setDividerLocation(0.2);
		
		//employee(splitPane);		


		JLabel lblLogo = new JLabel("");
		lblLogo.setOpaque(true);
		
		/*
		 * 
		 * Website: Stackoverflow
		 * Title: How to resize JLabel ImageIcon?
		 * Author: trolologuy
		 * Released Date: 20/08/13
		 * Referred Date: 10/12/19
		 * URL: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
		 * 
		 * */
		ImageIcon imageIcon = new ImageIcon(Main_Display.class.getResource("/img/Giant forest Inn Hotel Logo.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(280, 95,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg); 
		
		
		lblLogo.setIcon(imageIcon);
		panelLeftNav.add(lblLogo);
		
		
		btnReservation = new JButton("Reservation");
		btnReservation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bReservation = btnReservation.getModel();
				
                if (bReservation.isPressed()) {
                	btnReservation.setBackground(Color.lightGray);
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                	
                }
               
			}
		});
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reservations(splitPane);
			}
		});
		panelLeftNav.add(btnReservation);
		
		btnGuest = new JButton("Guest");
		btnGuest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bGuest = btnGuest.getModel();
				
                if (bGuest.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(Color.lightGray);
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guests(splitPane);
				
			}
		});
		panelLeftNav.add(btnGuest);
		
		btnCheckIn = new JButton("Check In");
		btnCheckIn.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bCheckIn = btnCheckIn.getModel();
				
                if (bCheckIn.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(Color.lightGray);
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_in(splitPane);
			}
		});
		panelLeftNav.add(btnCheckIn);
		
		
		
		
		
		btnRoom = new JButton("Room");
		btnRoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bRoom = btnRoom.getModel();
				
                if (bRoom.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(Color.lightGray);
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rooms(splitPane);
			}
		});
		panelLeftNav.add(btnRoom);//if want to control the page just hide this button
		
		btnRoomStatus = new JButton("Room Status / Housekeeping");
		btnRoomStatus.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bRoomStatus= btnRoomStatus.getModel();
				
                if (bRoomStatus.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(Color.lightGray);
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnRoomStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				room_status(splitPane);	
			
			}
		});
		panelLeftNav.add(btnRoomStatus);
		

		
		
		
		btnAmentities = new JButton("Amenities");
		btnAmentities.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bAmenities = btnAmentities.getModel();
				
                if (bAmenities.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(Color.lightGray);
                	btnServices.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnAmentities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amenities(splitPane);
			}
		}); 
		panelLeftNav.add(btnAmentities);

		
		
		btnServices = new JButton("Services");
		btnServices.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bServices= btnServices.getModel();
				
                if (bServices.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(Color.lightGray);
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
               
			}
		});
		btnServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				services(splitPane);	
			
			}
		});
		panelLeftNav.add(btnServices);
		
		btnFoodOrder = new JButton("Food Orderings");
		btnFoodOrder.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bFoodOrder = btnFoodOrder.getModel();
				if (bFoodOrder.isPressed()){
					btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(Color.lightGray);
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
			}
		});
		btnFoodOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food_order(splitPane);	
			}
		});
		panelLeftNav.add(btnFoodOrder);
		
		btnPayment = new JButton("Payment");
		btnPayment.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bPayment = btnPayment.getModel();
				if (bPayment.isPressed()){
					btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(Color.lightGray);
                	btnHotelDetail.setBackground(new JButton().getBackground());
                }
			}
		});
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payment(splitPane);	
			}
		});
		panelLeftNav.add(btnPayment);
		
		btnHotelDetail = new JButton("Hotel Information");
		btnHotelDetail.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ButtonModel bHotelDetail = btnHotelDetail.getModel();
				
                if (bHotelDetail.isPressed()) {
                	btnReservation.setBackground(new JButton().getBackground());
                	btnGuest.setBackground(new JButton().getBackground());
                	btnCheckIn.setBackground(new JButton().getBackground());
                	btnRoom.setBackground(new JButton().getBackground());
                	btnRoomStatus.setBackground(new JButton().getBackground());
                	btnAmentities.setBackground(new JButton().getBackground());
                	btnServices.setBackground(new JButton().getBackground());
                	btnFoodOrder.setBackground(new JButton().getBackground());
                	btnPayment.setBackground(new JButton().getBackground());
                	btnHotelDetail.setBackground(Color.lightGray);
                }
               
			}
		});
		btnHotelDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotelDetails(splitPane);
				
			}
		});
		panelLeftNav.add(btnHotelDetail);
		

	}
}
