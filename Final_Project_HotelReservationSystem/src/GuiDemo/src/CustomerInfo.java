/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */

package GuiDemo.src;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

/**
 * This class is used to customer information page where the customer information is stored
 * @author Sridhar
 *
 */

public class CustomerInfo 
{

	private JFrame frame;
	public static JTextField textField;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_7;
	public static JTextField textField_8;
	public static JTextField textField_1;
	public static JLabel label;
	public static JLabel label_1;
	public static JLabel label_2;
	private static String occupants;
	public static String cidate1;
	public static String cidate2;
	private static String roomtype;
	public static Connection conn;
	public static Room window ;
	public static String getRoomtype() 
	{
		return roomtype;
	}
	public static void setRoomtype(String roomtype) 
	{
		CustomerInfo.roomtype = roomtype;
	}
	public String getCidate2() 
	{
		return cidate2;
	}
	@SuppressWarnings("static-access")
	public void setCidate2(String cidate2) 
	{
		this.cidate2 = cidate2;
	}
	public String getCidate1() 
	{
		return cidate1;
	}
	@SuppressWarnings("static-access")
	public void setCidate1(String cidate1) 
	{
		this.cidate1 = cidate1;
	}
	public String getOccupants() 
	{
		return occupants;
	}
	@SuppressWarnings("static-access")
	public void setOccupants(String occupants) 
	{
		this.occupants = occupants;
	}
	public CustomerInfo obj1;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private JTextField textField_9;
	public CustomerInfo() {
		initialize();
	}
	/**
	 * This helps to connect to the mysql database.
	 * @return
	 * @throws Exception
	 */
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
	 * This method performs the insert operation into the 
	 * reservation table. Called from the confirm button. When the confirm button is clicked, the insert happens
	 * into the reservationtable
	 * @param conn object is passed as a parameter
	 * @throws SQLException when the 
	 */
	public static void insertTable(Connection conn)throws SQLException
	{
		try
		{
			String query = "insert into reservationtable(FIRST_NAME , LAST_NAME, CHECK_IN, CHECK_OUT, ROOM_TYPE, NO_OF_OCCUPANTS)"
			        + " values (?, ?, ?, ?, ?, ?)";
			System.out.println("The query is"+query);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("The first name is"+textField.getText());
		      preparedStmt.setString(1, textField.getText());
		      preparedStmt.setString(2, textField_1.getText());
		      preparedStmt.setString(3, cidate1);
		      preparedStmt.setString(4, cidate2);
		      preparedStmt.setString(5, label_2.getText());
		      preparedStmt.setString(6, occupants);
		      preparedStmt.execute();
		      System.out.println("Inserted successfully into the Reservation Table");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Inserts the table into the customer_information table
	 * @param conn object is passed as the parameter.
	 * @throws SQLException gets thrown when the exception occurs.
	 */
	
	public static void insertTable1(Connection conn)throws SQLException
	{
		try
		{
			String query = "insert into customer_information(FIRST_NAME,LAST_NAME,Address,Phone_Number,Room_Number,Room_Type,Booked_date,Email_id,City,State,Country,ZipCode)"
			        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("The query is"+query);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("The first name is"+textField.getText());
		      preparedStmt.setString(1, textField.getText());
		      preparedStmt.setString(2, textField_1.getText());
		      preparedStmt.setString(3, textField_2.getText());
		      preparedStmt.setString(4, textField_3.getText());
		      preparedStmt.setString(5, label.getText());
		      preparedStmt.setString(6, label_2.getText());
		      Date date = new Date();
		      String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		      preparedStmt.setString(7, modifiedDate);
		      preparedStmt.setString(8, textField_4.getText());
		      preparedStmt.setString(9, textField_7.getText());
		      preparedStmt.setString(10, textField_8.getText());
		      preparedStmt.setString(11, textField_6.getText());
		      preparedStmt.setString(12, textField_5.getText());
		      preparedStmt.execute();
		      System.out.println("Inserted successfully into the Customer Inforamtion table");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Updates the start sate and end date into the room_table. Once the user 
	 * books the room for the customer, the values of the start date and end date are updated.
	 * @throws SQLException when the update does not happen properly
	 */
	
	public void UpdateQueries() throws SQLException
	{
			String query = "update room_table set start_date = ?,end_date = ? where room_number = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1, cidate1);
		      preparedStmt.setString(2, cidate1);
		      preparedStmt.setString(3, label.getText());
		      preparedStmt.executeUpdate();
		      System.out.println("Update Query from the room executed successfully..!!");
	}
		
	/**
	 * Checks for the empty field validation.
	 * A check is made in every field.
	 * @return
	 */
	
	public static boolean IsValidationCheck()
	{
		if(textField.getText().equals("")||textField_4.getText().equals(""))
		{
			
			JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
			return false;
		}
		return true;
	}
	/**
	 * Initializes all the contents of the frame.
	 * This frame has the confirm order button, which when typed sends the values 
	 * sorted in the object to the next frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 671, 544);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name              *");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(25, 55, 136, 16);
		getFrame().getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(25, 82, 75, 16);
		getFrame().getContentPane().add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(25, 118, 101, 16);
		getFrame().getContentPane().add(lblAddress);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContactNo.setBounds(25, 343, 75, 16);
		getFrame().getContentPane().add(lblContactNo);
		
		JLabel lblRoomNo = new JLabel("Room No.");
		lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoomNo.setBounds(25, 370, 75, 16);
		getFrame().getContentPane().add(lblRoomNo);
		
		JLabel lblCostbill = new JLabel("Cost/Bill");
		lblCostbill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCostbill.setBounds(26, 397, 61, 16);
		getFrame().getContentPane().add(lblCostbill);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoomType.setBounds(25, 428, 101, 16);
		getFrame().getContentPane().add(lblRoomType);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventQueue.invokeLater(new Runnable() 
				{
					public void run() 
					{
						try 
						{
							if(IsValidationCheck()==true)
							{
								
							// here the values from the current frame are transferred 
								//to another Confirm Order frame	
							ConfirmOrder cframe = new ConfirmOrder();
							//frame.
							ConfirmOrder.lblNewLabel_1.setText(textField.getText());
							ConfirmOrder.label_10.setText(textField_1.getText());
							ConfirmOrder.label_11.setText(textField_2.getText());
							ConfirmOrder.label_12.setText(textField_7.getText());
							ConfirmOrder.label_13.setText(textField_8.getText());
							ConfirmOrder.label_14.setText(textField_5.getText());
							ConfirmOrder.label_15.setText(textField_4.getText());
							ConfirmOrder.label_16.setText(label.getText());
							ConfirmOrder.label_17.setText(label_1.getText());
							ConfirmOrder.label_18.setText(label_2.getText());
							ConfirmOrder.label_3.setText(textField_6.getText());
							ConfirmOrder.label_4.setText(textField_3.getText());
						    ConfirmOrder.label_6.setText(textField_9.getText());
							ConfirmOrder.setCnstrtdate(cidate1);
							ConfirmOrder.setCnenddate(cidate2);
							conn = getConnection();
							//insert into reservation table
							insertTable(conn);
							
							//insert into customer_information table
							insertTable1(conn);		
						
							//Update the start date and end date of the room_table
						
							UpdateQueries();
							
							getFrame().setVisible(false);
							cframe.setVisible(true);
						}
					} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				});		
			
			}
		});
		btnConfirmOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmOrder.setBounds(148, 465, 143, 29);
		getFrame().getContentPane().add(btnConfirmOrder);
		
		textField = new JTextField();
		textField.setBounds(171, 55, 213, 21);
		getFrame().getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(171, 118, 213, 20);
		getFrame().getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(171, 345, 213, 16);
		getFrame().getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setForeground(new Color(0, 0, 0));
		btnGoBack.setBackground(new Color(222, 184, 135));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							// here the values from the current frame are transferred 
							//to another frame Room
							window = new Room();
							window.setDate1(cidate1);
							window.setDate2(cidate2);
							window.setRoomType(roomtype);
							window.setOccupants(occupants);
							frame.setVisible(false);
							window.frame.setVisible(true);
						} catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					});
				
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(0, 0, 143, 29);
		frame.getContentPane().add(btnGoBack);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(171, 194, 213, 16);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(171, 221, 213, 16);
		frame.getContentPane().add(textField_8);
		
		JLabel lblAddressLine_1 = new JLabel("City");
		lblAddressLine_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressLine_1.setBounds(25, 192, 75, 16);
		frame.getContentPane().add(lblAddressLine_1);
		
		JLabel lblAddressLine = new JLabel("State");
		lblAddressLine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressLine.setBounds(25, 219, 101, 16);
		frame.getContentPane().add(lblAddressLine);
		
		JLabel lblNewLabel = new JLabel("Customer Information Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(218, 11, 238, 33);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(171, 87, 213, 21);
		frame.getContentPane().add(textField_1);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// On hitting logout LoginFrame is set to visible
				LoginFrameNew Lframe = new LoginFrameNew();
				frame.setVisible(false);
				Lframe.setVisible(true);
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.setBackground(new Color(222, 184, 135));
		button.setBounds(489, 0, 166, 29);
		frame.getContentPane().add(button);
		
		label = new JLabel("");
		label.setBounds(171, 366, 193, 20);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(173, 393, 193, 20);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("");
		label_2.setBounds(173, 424, 193, 20);
		frame.getContentPane().add(label_2);
		
		JLabel lblEmailId = new JLabel("Email Id                   *");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailId.setBounds(25, 316, 136, 16);
		frame.getContentPane().add(lblEmailId);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(171, 318, 213, 16);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(171, 248, 213, 16);
		frame.getContentPane().add(textField_5);
		
		JLabel lblZipcode = new JLabel("ZipCode");
		lblZipcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZipcode.setBounds(25, 246, 75, 16);
		frame.getContentPane().add(lblZipcode);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountry.setBounds(25, 286, 75, 16);
		frame.getContentPane().add(lblCountry);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(171, 288, 213, 16);
		frame.getContentPane().add(textField_6);
		
		JLabel lblAptno = new JLabel("Apt_No");
		lblAptno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAptno.setBounds(25, 152, 101, 16);
		frame.getContentPane().add(lblAptno);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(171, 152, 61, 20);
		frame.getContentPane().add(textField_9);
		
		JLabel lblConfirmationWillBe = new JLabel("Pls enter a valid email id.");
		lblConfirmationWillBe.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblConfirmationWillBe.setBounds(394, 316, 113, 16);
		frame.getContentPane().add(lblConfirmationWillBe);
		
		JLabel lblConfirmationWillBe_1 = new JLabel("Confirmation will be sent to this email id");
		lblConfirmationWillBe_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblConfirmationWillBe_1.setBounds(394, 330, 187, 16);
		frame.getContentPane().add(lblConfirmationWillBe_1);
	}

	public JFrame getFrame() 
	{
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.getContentPane().setBackground(new Color(222, 184, 135));
	}
}
