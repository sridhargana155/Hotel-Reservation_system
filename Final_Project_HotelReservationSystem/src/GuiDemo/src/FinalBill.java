/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */
package GuiDemo.src;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JTextField;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FinalBill extends JFrame 
{
	public static JTextField textField_2;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JTextField textField_6;
	public static JTextField textField_7;
	public static JTextField textField_8;
	public static JTextField textField_9;
	public static String email;
	public static Label label_9;
	public static Label label_10;
	public static Label label_11 ;
	public static Connection conn;
	public static JFrame frame;
	
	public static String getEmail() 
	{
		return email;
	}

	public static void setEmail(String email) {
		FinalBill.email = email;
	}
	/**
	 * For the database connection
	 * @return the object for connection
	 * @throws Exception thrown when the connection fails
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
	 * Query to retrieve the room_cost.
	 */
	public void retrieveRoom_cost()
	{
		try
		{
				String sql = "SELECT Email_id FROM customer_information where  First_Name = '" + label_9.getText()+ "'" +";";
	            ResultSet rs;
	            System.out.println("The query from AddAddtional charges is \t"+sql);
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) 
	            {
	            	//JOptionPane.showMessageDialog(null, "Your password is "+"\""+rs.getString("PASSWORD")+"\"");     
	            	setEmail(rs.getString("Email_id"));
	            
	            }
			
		}
		catch (Exception err) 
		{
	        //JOptionPane.showMessageDialog(this, err.getMessage());
	    	System.out.println("Exception Caught"+err);
	    }
	}
	/**
	 * Checks for the validation of empty fields.
	 * 
	 * @return false if the validation is false
	 */
	public static boolean IsValidationCheck()
	{
		
		
		//JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
		if(textField_2.getText().equals("")||textField_4.getText().equals("")||textField_5.getText().equals("")||textField_6.getText().equals("")||textField_7.getText().equals("")||textField_8.getText().equals(""))
		{
			
			JOptionPane.showMessageDialog(null, "Please dont leave the textbox empty. Typer zero if not applicable!!");
			return false;
		}
		return true;
	}
/**
 * Query to insert into the billing table
 * @param conn
 * @throws SQLException
 */
	public static void insertTable(Connection conn)throws SQLException
	{
		try
		{
			String query = "insert into billing_table(FIRST_NAME,Room_Number,Room_Bill,Room_Services,Food_and_Beverages,Gym_Service,Recreation_Service,Swimming_Pool,Other_Services,Total_Bill)"
			        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("The query is"+query);
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1, label_9.getText());
		      preparedStmt.setString(2, label_11.getText());
		      preparedStmt.setString(3, label_10.getText());
		      preparedStmt.setString(4, textField_2.getText());
		      preparedStmt.setString(5, textField_4.getText());
		      //preparedStmt.setString(6, textField_5.getText());
		      preparedStmt.setString(6, textField_5.getText());
		      preparedStmt.setString(7, textField_6.getText());
		      preparedStmt.setString(8, textField_7.getText());
		      preparedStmt.setString(9, textField_8.getText());
		      preparedStmt.setString(10, textField_9.getText());
		      preparedStmt.execute();
		      System.out.println("Inserted successfully in the Billing Table from final bill");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public FinalBill() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 640, 483);
		
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total Bill Amount with Miscellaneous Charges");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(125, 41, 449, 37);
		frame.getContentPane().add(lblNewLabel);
		
		Label label = new Label("First Name");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(77, 107, 81, 20);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Room Amount          $");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(55, 187, 140, 20);
		frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("Room Service             $");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(40, 226, 155, 20);
		frame.getContentPane().add(label_2);
		
		Label label_3 = new Label("Room Number");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_3.setBounds(54, 146, 104, 20);
		frame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(209, 226, 111, 20);
		frame.getContentPane().add(textField_2);
		
		Label label_4 = new Label("Food and Beverages    $");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_4.setBounds(39, 277, 164, 20);
		frame.getContentPane().add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(209, 277, 111, 20);
		frame.getContentPane().add(textField_4);
		
		Label label_5 = new Label("Gym Services          $");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_5.setBounds(55, 326, 140, 20);
		frame.getContentPane().add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(209, 326, 111, 20);
		frame.getContentPane().add(textField_5);
		
		Label label_6 = new Label("Recreation Service      $");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_6.setBounds(39, 368, 156, 20);
		frame.getContentPane().add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(209, 368, 111, 20);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(503, 107, 89, 20);
		frame.getContentPane().add(textField_7);
		
		Label label_7 = new Label("Swimming Pool Charges  $");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_7.setBounds(322, 107, 175, 20);
		frame.getContentPane().add(label_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(503, 146, 89, 20);
		frame.getContentPane().add(textField_8);
		
		Label label_8 = new Label("Other Services    $");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_8.setBounds(372, 146, 125, 20);
		frame.getContentPane().add(label_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(503, 238, 111, 20);
		frame.getContentPane().add(textField_9);
		
		JButton btnTotalBill = new JButton("Total Bill");
		btnTotalBill.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{			   	
				if(IsValidationCheck()==true)
				{
					
					//To add all the values in the fields with room rent.	
					int totalbill = 0, rm_amt=0,rm_serv=0,fd_bv=0,gym_srv=0,recreatn_ser=0,swm_pl=0,othr_rm=0;
					StringBuilder sb = new StringBuilder(label_10.getText());
					System.out.println("The label 10 is"+label_10.getText());
					String temp = sb.toString();
					System.out.println("The value of Room rent is"+temp);
					rm_amt = Integer.parseInt(temp);
					rm_serv = Integer.parseInt(textField_2.getText());
					fd_bv = Integer.parseInt(textField_4.getText());
					gym_srv = Integer.parseInt(textField_5.getText());
					recreatn_ser = Integer.parseInt(textField_6.getText());
					swm_pl = Integer.parseInt(textField_7.getText());
					othr_rm = Integer.parseInt(textField_8.getText());
					totalbill = rm_amt+rm_serv+fd_bv+gym_srv+recreatn_ser+swm_pl+othr_rm;// adding all the values in the fields
					System.out.println("The total bill is"+totalbill);
					textField_9.setText(Integer.toString(totalbill));	
				}
				
			}
		});
		btnTotalBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTotalBill.setBounds(385, 235, 89, 23);
		frame.getContentPane().add(btnTotalBill);
		
		JButton btnSendPdfTo = new JButton("Send Bill to Mail");
		btnSendPdfTo.addActionListener(new ActionListener() {
			/**
			 * This method performs the email triggering to the customer's email id
			 * Once the button is clicked on the mail is then triggered to the customer's email id.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				retrieveRoom_cost();
				System.out.println("The email to be sent is"+getEmail());
				String[] recipients = {getEmail()};
				// call query to retrieve email
				String Title = "Your total bill amount for the accomadation with additional charges is as follows: "+"\n"+"\n";
				String fn = "First Name: "+label_9.getText()+"\n" ;
				String ln = "Room Rent: "+label_10.getText()+"\n" ;
				String rn = "Room No: "+label_11.getText()+"\n" ;
				String rs = "Room Service: $"+textField_2.getText()+"\n" ;
				String fb = "Food and Beverages: $"+textField_4.getText()+"\n" ;
				String gs = "Gym Service: $"+textField_5.getText()+"\n" ;
				String rcsr = "Recreational Services: $"+textField_6.getText()+"\n" ;
				String swm = "Swimming Pool: $"+textField_7.getText()+"\n" ;
				String othrsrv = "Other Services: $"+textField_8.getText()+"\n" ;
				String totlservc = "Total Bill: $"+textField_9.getText()+"\n" ;
				String BigString = Title+fn+ln+rn+rs+fb+gs+rcsr+swm+othrsrv+totlservc;
				if(EmailSender.SendMail("hotelreservationsystemsyracuse@gmail.com", "hotelreserve007",BigString, recipients))
				  {			  
					  JOptionPane.showMessageDialog(null, "Please check your mail for Billing information!");					  
					  System.out.println("email sent from the Final Bill");
				  }
				  else
				  {
					  System.out.println("failed");
					  JOptionPane.showMessageDialog(null, "Mail sending Failed");
				  }
			}
		});
		btnSendPdfTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSendPdfTo.setBounds(372, 395, 149, 23);
		frame.getContentPane().add(btnSendPdfTo);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() 
		{
			/**
			 * Once the logout button is clicked, the login page 
			 * appears from anywhere in the application
			 */
			public void actionPerformed(ActionEvent e) 
			{
				LoginFrameNew Lframe = new LoginFrameNew();
				frame.setVisible(false);
				Lframe.setVisible(true);
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.setBackground(new Color(222, 184, 135));
		button.setBounds(458, 0, 166, 29);
		frame.getContentPane().add(button);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) 
			{
				AddAdditionalCharges adframe = new AddAdditionalCharges();
				frame.setVisible(false);
				adframe.frame.setVisible(true);		
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnBack.setBackground(new Color(222, 184, 135));
		btnBack.setBounds(0, 0, 166, 29);
		frame.getContentPane().add(btnBack);
		
		label_9 = new Label("");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_9.setBounds(209, 107, 81, 20);
		frame.getContentPane().add(label_9);
		
		label_10 = new Label("");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_10.setBounds(209, 187, 81, 20);
		frame.getContentPane().add(label_10);
		
		label_11 = new Label("");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_11.setBounds(209, 146, 91, 20);
		frame.getContentPane().add(label_11);
		
		JButton btnStoreBillInfo = new JButton("Store Bill Info");
		btnStoreBillInfo.addActionListener(new ActionListener() 
		{
		
			/**
			 * Stores the Billing information into the database table - biiling_table
			 */
			public void actionPerformed(ActionEvent e) 
			{

				try 
				{
					conn = getConnection();
					insertTable(conn);
				} catch (Exception et) {
					// TODO Auto-generated catch block
					et.printStackTrace();
				}
			}
		});
		btnStoreBillInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStoreBillInfo.setBounds(372, 294, 149, 23);
		frame.getContentPane().add(btnStoreBillInfo);
		
		JButton btnGeneratePdf = new JButton("Generate Pdf");
		btnGeneratePdf.addActionListener(new ActionListener() 
		{
			/**
			 * This helps to generate the Total bill. From all the fields in the Frame. we take the values.
			 * 
			 */
			public void actionPerformed(ActionEvent e) 
			{
				String Title = "Your total bill amount for the accomadation with additional charges is as follows: "+"\n"+"\n";
				String fn = "First Name: "+label_9.getText()+"\n" ;
				String ln = "Room Rent: "+label_10.getText()+"\n" ;
				String rn = "Room No: "+label_11.getText()+"\n" ;
				String rs = "Room Service: $"+textField_2.getText()+"\n" ;
				String fb = "Food and Beverages: $"+textField_4.getText()+"\n" ;
				String gs = "Gym Service: $"+textField_5.getText()+"\n" ;
				String rcsr = "Recreational Services: $"+textField_6.getText()+"\n" ;
				String swm = "Swimming Pool: $"+textField_7.getText()+"\n" ;
				String othrsrv = "Other Services: $"+textField_8.getText()+"\n" ;
				String totlservc = "Total Bill: $"+textField_9.getText()+"\n" ;
				String BigString = Title+fn+ln+rn+rs+fb+gs+rcsr+swm+othrsrv+totlservc;
				String mknm = label_9.getText()+label_11.getText()+"Bill.pdf";
				Document document=new Document(); 
				 try {
					PdfWriter.getInstance(document,new FileOutputStream(mknm));
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				 document.open(); 
				 try {
					document.add(new Paragraph(BigString));
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				 document.close(); 
				 JOptionPane.showMessageDialog(null, "PDF format of the bill generated at C:\\Users\\Sridhar\\workspace_for_mars\\Final_Project_HotelReservationSystem");
			
			}
		});
		btnGeneratePdf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGeneratePdf.setBounds(372, 348, 136, 23);
		frame.getContentPane().add(btnGeneratePdf);
	}
}
