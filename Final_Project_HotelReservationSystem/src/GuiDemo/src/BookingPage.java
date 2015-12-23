package GuiDemo.src;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;


import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;

@SuppressWarnings("serial")
public class BookingPage extends JFrame
{
	public static String tableName = "ReservationTable";
	public static JFrame frame;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	@SuppressWarnings("rawtypes")
	public static JComboBox comboBox ;
	public static Connection conn;
	public static Room window;
	//public static JTable table;
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	
	public static boolean IsValidationCheck()
	{
		
		
		//JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
		if(textField_2.getText().equals("")||textField_3.getText().equals("")||comboBox.getSelectedItem().equals("")||textField_4.getText().equals(""))
		{
			
			JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
			return false;
		}
		return true;
	}

	public static Connection getConnection() throws Exception
	{
		try
		{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/hotel_reservation_system";
			String username = "root";
			String password = "sridhar007";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected successfully from Booking Page");
			return conn;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * Create the application.
	 */
	public BookingPage() 
	{
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public boolean DateValidation() throws ParseException
	{
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		if(textField_2.getText().matches("\\d{4}-\\d{2}-\\d{2}") && textField_3.getText().matches("\\d{4}-\\d{2}-\\d{2}") )
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = sdf.parse(textField_2.getText());
			Date date3 = sdf.parse(textField_3.getText());
			//the date typed can be after or equal to the current date but not beofre the current date
			if(date2.before(date) && date3.before(date))
			{
				JOptionPane.showMessageDialog(null, "The start date and end date can be present date or future date but not past date.");
					return false;
			}
			else if(date2.after(date3))
			{
				JOptionPane.showMessageDialog(null, "the start date should be less than the end date");
				return false;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please fill out the Date format Correctly as YYYY-MM-DD");
			return false;
		}
		return true;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(222, 184, 135));
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		frame.setBounds(100, 100, 657, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(new JPanelWithBackground("/HRS.jpg"));
		frame.getContentPane().setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(245, 103, 153, 26);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(245, 163, 153, 26);
		frame.getContentPane().add(textField_3);
		
		JLabel lblCheckin = new JLabel("Check-in                       *");
		lblCheckin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheckin.setForeground(Color.BLACK);
		lblCheckin.setBounds(70, 104, 153, 21);
		frame.getContentPane().add(lblCheckin);
		JLabel lblCheckout = new JLabel("Check-out                      *");
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheckout.setForeground(Color.BLACK);
		lblCheckout.setBounds(65, 166, 170, 16);
		frame.getContentPane().add(lblCheckout);
		
		JLabel lblRoomType = new JLabel("Room type                     *");
		lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoomType.setForeground(Color.BLACK);
		lblRoomType.setBounds(65, 236, 166, 26);
		frame.getContentPane().add(lblRoomType);
		
		String[] rooms={"-----------------Select--------------------------------","Single","Double","Deluxe"};
		comboBox = new JComboBox(rooms);
		
		comboBox.setBounds(245, 238, 250, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNoOfOccupants = new JLabel("No. of occupants              *");
		lblNoOfOccupants.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNoOfOccupants.setForeground(Color.BLACK);
		lblNoOfOccupants.setBounds(58, 301, 177, 16);
		frame.getContentPane().add(lblNoOfOccupants);
		
		textField_4 = new JTextField();
		textField_4.setBounds(245, 298, 153, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnBook = new JButton("Go to CheckAvailability Page");
		btnBook.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnBook.setBounds(223, 380, 272, 26);
		frame.getContentPane().add(btnBook);
		
		JLabel lblBookingPage = new JLabel("Booking Page!");
		/*Image img = new ImageIcon(this.getClass().getResource("/HRS.jpg")).getImage();
		lblBookingPage.setIcon(new ImageIcon(img));*/
		
		
		lblBookingPage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookingPage.setForeground(Color.BLACK);
		lblBookingPage.setBackground(Color.LIGHT_GRAY);
		lblBookingPage.setBounds(237, 11, 193, 29);
		frame.getContentPane().add(lblBookingPage);
		JButton btnExistingUser = new JButton("Existing Customers");
		btnExistingUser.setForeground(new Color(0, 0, 0));
		btnExistingUser.setBackground(new Color(222, 184, 135));
		btnExistingUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				EventQueue.invokeLater(new Runnable() 
				{
					@SuppressWarnings("static-access")
					public void run() {
						try {
							AddAdditionalCharges adframe = new AddAdditionalCharges();
							frame.setVisible(false);
							adframe.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnExistingUser.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnExistingUser.setBounds(421, 51, 166, 26);
		frame.getContentPane().add(btnExistingUser);
		
		JLabel lblYyyymmdd = new JLabel("(YYYY-MM-DD)");
		lblYyyymmdd.setForeground(Color.BLACK);
		lblYyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYyyymmdd.setBounds(411, 108, 95, 21);
		frame.getContentPane().add(lblYyyymmdd);
		
		JLabel lblyyyymmdd = new JLabel("(YYYY-MM-DD)");
		lblyyyymmdd.setForeground(Color.BLACK);
		lblyyyymmdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblyyyymmdd.setBounds(408, 163, 98, 21);
		frame.getContentPane().add(lblyyyymmdd);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LoginFrameNew Lframe = new LoginFrameNew();
				frame.setVisible(false);
				Lframe.setVisible(true);
				//Lframe.frame.getRootPane().setDefaultButton(Lframe.btnLogin);
			
			}
		});
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnLogout.setBackground(new Color(222, 184, 135));
		btnLogout.setBounds(475, 0, 166, 29);
		frame.getContentPane().add(btnLogout);
		btnBook.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try 
				{
					if(IsValidationCheck()==true)
					{
						if(DateValidation()==true)
						{
							 conn = getConnection();
							 EventQueue.invokeLater(new Runnable() 
							 {
									public void run() {
										try {
											window = new Room();
											window.setDate1(textField_2.getText());
											window.setDate2(textField_3.getText());
											window.setRoomType((String) comboBox.getSelectedItem());
											window.setOccupants(textField_4.getText());
											frame.setVisible(false);
											window.frame.setVisible(true);											
											/*CustomerInfo csinfo = new CustomerInfo();
											csinfo.setOccupants(textField_4.getText());*/
										} catch (Exception e) 
										{
											e.printStackTrace();
										}
									}
									});	 
							 
						}
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null,"Invalid entry");
				
				 		 	
            }
		});
		
	}
}
