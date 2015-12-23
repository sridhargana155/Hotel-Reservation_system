/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */


package GuiDemo.src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * This class is used to create a new account for the 
 * user
 *
 */

@SuppressWarnings("serial")
public class NewUserRegistrationPage extends JFrame 
{
	
	public static TextField textField;
	public static TextField textField_1;
	@SuppressWarnings("rawtypes")
	public static JComboBox comboBox;
	public static Connection conn;
	public static JFrame frame;
	public static JPasswordField passwordField;
	
	/**
	 * An insert into the usertable happens here.
	 * @param conn object is passed as the parameter.
	 * @throws SQLException thrown when th econnection fails
	 */
	public static void insertTable(Connection conn)throws SQLException
	{
		try
		{
			String query = "insert into usertable(USER_NAME, PASSWORD, SECURITY_QN, SECURITY_ANS)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			char[] passwd = passwordField.getPassword();
			String nwpasswd = new String(passwd);
			
			//String tmp = textField;
			String value1=(String) comboBox.getSelectedItem();
			System.out.println("Combo value:\t"+value1);
			System.out.println(textField.getText());
			System.out.println(textField_1.getText());
			System.out.println(passwordField.getPassword());
		     // preparedStmt.setString(1, textField.getText());
		      preparedStmt.setString(1, textField.getText());
		      preparedStmt.setString(2, nwpasswd);
		      preparedStmt.setString(3, value1);
		      preparedStmt.setString(4, textField_1.getText());
		      // execute the preparedstatement
		      preparedStmt.execute();
		      System.out.println("Inserted successfully");
		      JOptionPane.showMessageDialog(null, "Your account has been created sucessfully!");		      
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Exception Caught"+ex);
		}
	}
	/**
	 * This method is for the database connection
	 * @return the connection object
	 * @throws Exception when the connection fails
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
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected successfully");
			return conn;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return null;
	}
	
	/**
	 * The frame for this page is created here.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NewUserRegistrationPage() 
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 686, 483);
		//contentPane = new JPanel();
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name      *");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(67, 69, 103, 20);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password        *");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(67, 127, 103, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question");
		lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecurityQuestion.setBounds(35, 171, 114, 20);
		frame.getContentPane().add(lblSecurityQuestion);
		
		JLabel lblSecurityAnswer = new JLabel("Security Answer");
		lblSecurityAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecurityAnswer.setBounds(45, 232, 104, 20);
		frame.getContentPane().add(lblSecurityAnswer);
		
		JLabel lblNewUserRegistration = new JLabel("New User Registration..!!");
		lblNewUserRegistration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewUserRegistration.setBounds(141, 21, 214, 20);
		frame.getContentPane().add(lblNewUserRegistration);
		
		JButton btnCreateNewUser = new JButton("Go back to Login");
		btnCreateNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateNewUser.addActionListener(new ActionListener() 
		{
			/**
			 * Takes user to the Login page from here.
			 */
			public void actionPerformed(ActionEvent arg0) 
			{			
				
				try
				{
					LoginFrameNew Lframe = new LoginFrameNew();
					frame.setVisible(false);
					Lframe.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnCreateNewUser.setBounds(274, 311, 179, 23);
		frame.getContentPane().add(btnCreateNewUser);
		
		String[] Questions={"-----------------Select--------------------------------------------","What is name of the first car you bought?","What is the maiden name of your mother?","Who is your first employer?","Who is your favorite teacher?","Which is your favorite place?","What is your native place?","Who is your best friend?"};
		
		comboBox = new JComboBox(Questions);
		comboBox.setBounds(193, 172, 307, 23);
		frame.getContentPane().add(comboBox);
		
		textField = new TextField();
		textField.setBounds(193, 69, 162, 22);
		frame.getContentPane().add(textField);
		
		textField_1 = new TextField();
		textField_1.setBounds(193, 230, 162, 22);
		frame.getContentPane().add(textField_1);
		
		JButton button = new JButton("Create New User");
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button.addActionListener(new ActionListener()
		{
			//A call is made to insert the record into the table.
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					conn = getConnection();
				} 
				catch (Exception e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try 
				{
					insertTable(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(67, 311, 173, 23);
		frame.getContentPane().add(button);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 129, 162, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPleaseKeep = new JLabel("-> Please keep a record of your Security Question and Answer to recover password.");
		lblPleaseKeep.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblPleaseKeep.setBounds(92, 273, 408, 20);
		frame.getContentPane().add(lblPleaseKeep);
	}
}
