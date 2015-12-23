/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */


package GuiDemo.src;
import javax.swing.JFrame;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddAdditionalCharges
 {

	//private JPanel contentPane;
	public static JTextField textField;
	public static JTextField textField_1;
	public static Connection conn;
	public static JFrame frame;
	public static FinalBill Finlframe;
	
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
			System.out.println("Connected successfully");
			return conn;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return null;
	}
	
	public static boolean IsValidationCheck()
	{
		
		
		//JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
		if(textField.getText().equals("")||textField_1.getText().equals(""))
		{
			
			JOptionPane.showMessageDialog(null, "Please fill out the Madantory Fields");
			return false;
		}
		return true;
	}
	
	
	@SuppressWarnings("static-access")
	public void retrieveRoom_cost()
	{
		try
		{
			long days = 0;
			if(textField.getText()!=null && textField_1.getText()!=null)
			{
				// get the no of days to multiply it with the cost per day
				String sql1 = "SELECT CHECK_IN,CHECK_OUT FROM reservationtable where FIRST_NAME = '" + textField_1.getText()+ "'" +";";
	            ResultSet rs1;
	            System.out.println("The query from AddAddtional charges is \t"+sql1);
	            Statement stmt1 = conn.createStatement();
	            rs1 = stmt1.executeQuery(sql1);
	            if (rs1.next()) 
	            {
	            System.out.println("The check in date is"+rs1.getString("CHECK_IN"));
	            System.out.println("The check out date is"+rs1.getString("CHECK_OUT"));
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		Date date1 = sdf.parse(rs1.getString("CHECK_IN"));
	            Date date2 = sdf.parse(rs1.getString("CHECK_OUT"));
	            long difftime = date2.getTime() - date1.getTime();
	            days = difftime/(1000 * 60 * 60 * 24);
	            System.out.println("The number of days are"+days);	            
	            }							
				String sql = "SELECT cost FROM room_table where room_number = '" + textField.getText()+ "'" +";";
	            ResultSet rs;
	            System.out.println("The query from AddAddtional charges is \t"+sql);
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) 
	            {
	            	//JOptionPane.showMessageDialog(null, "Your password is "+"\""+rs.getString("PASSWORD")+"\"");     
	            	Finlframe = new FinalBill();
	            	String tmp = rs.getString("cost");
	            	StringBuilder sb = new StringBuilder(tmp);
	            	int count = tmp.length()-1;
	            	sb.deleteCharAt(count);
	            	String finl = sb.toString();
	            	long cost = Long.parseLong(finl);
	            	cost *= days;
	            	System.out.println("Th total cost is"+cost);
	            	
	            	String strLong = Long.toString(cost);
	            	Finlframe.label_10.setText(strLong);
	            	Finlframe.label_9.setText(textField_1.getText());
	            	Finlframe.label_11.setText(textField.getText());
	            	
	            	//in this case enter when at least one result comes it means user is valid
	            } 
			else
				{
					JOptionPane.showMessageDialog(null, "The entered first name and room number are incorrect!!");
				}
		}
			else
			{
				
				JOptionPane.showMessageDialog(null, "Please enter the first name and room number");
				
			}
		}
		catch (Exception err) 
		{
	       
	    	System.out.println("Exception Caught"+err);
	    }
	}
	/**
	 * Create the frame.
	 */
	public AddAdditionalCharges() {
		frame = new JFrame();		
		//contentPane = new JPanel();
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		frame.setBounds(100, 100, 660, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);*/
		frame.getContentPane().setLayout(null);
		
		Label label = new Label("Exisiting User Page!");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(214, 25, 190, 55);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("Room No            *");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(53, 140, 127, 34);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(190, 140, 137, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name           *");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(53, 221, 127, 34);
		frame.getContentPane().add(lblFirstName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(190, 226, 137, 29);
		frame.getContentPane().add(textField_1);
		JButton btnNewButton = new JButton("Make Final Bill");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{				
				
				if(IsValidationCheck()==true)
				{
				
				try 
				{
					conn = getConnection();
					retrieveRoom_cost();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				
				EventQueue.invokeLater(new Runnable()
				{
					@SuppressWarnings("static-access")
					public void run() {
						try {
							
							//contentPane.get
							frame.setVisible(false);
							Finlframe.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(188, 319, 117, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
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
		button.setBounds(478, 0, 166, 29);
		frame.getContentPane().add(button);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) 
			{
				BookingPage window = new BookingPage();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnBack.setBackground(new Color(222, 184, 135));
		btnBack.setBounds(0, 0, 166, 29);
		frame.getContentPane().add(btnBack);
	}
}
