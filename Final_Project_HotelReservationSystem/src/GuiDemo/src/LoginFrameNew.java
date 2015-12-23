/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */

package GuiDemo.src;
import java.awt.Image;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 * This class takes care of the login functionality of this 
 * application. The user basically logs in with credentials here.
 */
public class LoginFrameNew extends JFrame 
{
	private JPanel contentPane;
	public static JTextField textField_1;
	private JLabel lblPassword;
	public static Connection conn;
	public static LoginFrameNew frame;
	public static JButton btnLogin;
	public static JPasswordField passwordField;
	/**
	 * The initial part of the execution of the application commences here
	 * @throws Exception throws when the connection is proper.
	 */
	public static void main(String[] args) throws Exception
	{
		conn = getConnection();
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try {
					 frame = new LoginFrameNew();
					frame.setVisible(true);
					frame.getRootPane().setDefaultButton(btnLogin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * This method is for the db connection
	 * @return the connection object
	 * @throws Exception when the connection fails.
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
	 * This method is used to validate all the
	 * fields in the Frame. In a given frame there are many fields that the user has to type. This checks validates all
	 * the fields. For instance, Date validation, Empty field check, Login checker, checks if the login user name and password
	 * are coorect.
	 * @return true if the check made for the validation is true.
	 */
	
	@SuppressWarnings("static-access")
	public static boolean loginCheck()
	{
		// fields validation for the user name and password
		try 
		{
			char[] passwd = passwordField.getPassword();
			String nwpasswd = new String(passwd);
			System.out.println("The password is"+nwpasswd);
	        if (textField_1.getText() != null && nwpasswd != null) {
	            String sql = "Select * from UserTable Where USER_NAME='" + textField_1.getText() + "' and PASSWORD='" + nwpasswd + "'";
	            ResultSet rs;
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) 
	            {
	            	try 
	            	{
						BookingPage window = new BookingPage();
						window.frame.setVisible(true);
						return true;
					} catch (Exception e) 
	            	{
						e.printStackTrace();
					}            	
	            	//in this case enter when at least one result comes it means user is valid
	            } 
	            else 
	            {
	            	JOptionPane.showMessageDialog(textField_1, "Incorrect Credentials");
	            	return false;
	                //in this case enter when  result size is zero  it means user is invalid
	            }
	        }

	    } catch (Exception err) {
	        //JOptionPane.showMessageDialog(this, err.getMessage());
	    	System.out.println("Exception Caught"+err);
	    }
		return false;
	} 
	

	/**
	 * Create the frame. Here is where the frames are getting created.
	 * Has three buttons of action viz. Login, New User and Forgot Password.
	 * Login - validates the user name and password, does empty field validation.
	 * New User - on clicking this it takes you to a page where the user can register
	 * Forgot Password - When you click on this takes you to the page where you can recover the passowrd.
	 */
	public LoginFrameNew() {
		//frame.getRootPane().setDefaultButton(btnLogin);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField_1 = new JTextField();
		textField_1.setBounds(199, 230, 138, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setToolTipText("Type your username");
		lblNewLabel.setBounds(86, 231, 86, 14);
		contentPane.add(lblNewLabel);
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setToolTipText("Type your password");
		lblPassword.setBounds(86, 264, 86, 14);
		contentPane.add(lblPassword);
		//textField.sete
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(loginCheck()==true)
				{
					frame.setVisible(false);
				}				
			}
		});
		btnLogin.setBounds(73, 323, 86, 20);
		contentPane.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnForgotPassword.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) 
			{
				
				try {
					SecurityQuestion sqframe = new SecurityQuestion();
					frame.setVisible(false);
					sqframe.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
		});
		btnForgotPassword.setBounds(235, 322, 154, 23);
		contentPane.add(btnForgotPassword);
		JButton btnNewUser = new JButton("New User");
		btnNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewUser.addActionListener(new ActionListener() 
		{
			/**
			 *  This button allows the user to create a new account.
			 *  
			 */
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) 
			{
				
				try 
				{
					NewUserRegistrationPage nwframe = new NewUserRegistrationPage();
					frame.setVisible(false); // here once the new user button is clicked the current window is set
					// set to false
					nwframe.frame.setVisible(true);
				} catch (Exception ex) 
				{
					ex.printStackTrace();
				}		
			}
		});
		btnNewUser.setBounds(139, 380, 121, 20);
		contentPane.add(btnNewUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 263, 138, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/HRS_resized.jpg")).getImage();
		
		lblNewLabel_1.setIcon(new ImageIcon(img));
		
		lblNewLabel_1.setBounds(0, 0, 423, 208);
		contentPane.add(lblNewLabel_1);
		
	}
}
