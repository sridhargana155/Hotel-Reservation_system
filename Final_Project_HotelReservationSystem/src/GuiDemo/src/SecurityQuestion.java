/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */

package GuiDemo.src;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.TextField;
import javax.swing.JComboBox;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
@SuppressWarnings("serial")
/**
 * This page is the retrieve the password when the user forgets the 
 * password.
 * @author Sridhar
 *
 */
public class SecurityQuestion extends JFrame 
{
	//private JPanel contentPane;
    @SuppressWarnings("rawtypes")
	public static JComboBox  comboBox;
    public static TextField textField;
    public static Connection conn;
    public static TextField textField_1;
    public static JFrame frame;
   /**
    * The connection is made again
    * @return connection object
    * @throws Exception thrown when the connection fails.
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
	 * The field validation whether the field is empty is checked here.
	 */
	public static void loginCheck()
	{
		try 
		{
	        if (comboBox.getSelectedItem() != null && textField.getText() != null) {
	            String sql = "Select PASSWORD from usertable where SECURITY_QN='" + comboBox.getSelectedItem() + "' and SECURITY_ANS='" + textField.getText() + "' and USER_NAME='"+textField_1.getText()+"'"+";";
	            ResultSet rs;
	            System.out.println("The query is \t"+sql);
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) 
	            {
	            	JOptionPane.showMessageDialog(null, "Your password is "+"\""+rs.getString("PASSWORD")+"\"");         	
	            	//in this case enter when at least one result comes it means user is valid
	            } 
	            else 
	            {
	            	//Component frame;
	            	JOptionPane.showMessageDialog(textField, "Your security answer is not correct!!");
	                //in this case enter when  result size is zero  it means user is invalid
	            }
	            
	        }

	    } catch (Exception err) {
	    	System.out.println("Exception Caught"+err);
	    }
		
	} 	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SecurityQuestion() 
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
		
		String[] Questions={"-----------------Select--------------------------------","What is name of the first car you bought?","What is the maiden name of your mother?","Who is your first employer?","Who is your favorite teacher?","Which is your favorite place?","What is your native place?","Who is your best friend?"};
		
		comboBox = new JComboBox(Questions);
		comboBox.setBounds(145, 109, 258, 20);
		frame.getContentPane().add(comboBox);
		
		Label label = new Label("Security Question");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 109, 119, 22);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Security Answer");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(21, 156, 108, 22);
		frame.getContentPane().add(label_1);
		
		textField = new TextField();
		textField.setBounds(145, 156, 258, 22);
		frame.getContentPane().add(textField);
		
		Button button = new Button("Retrieve Password");
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try{
					conn = getConnection();
				}
				catch(Exception err){
					System.out.println(err.getMessage());
				}
				loginCheck();
			}
		});
		button.setBounds(95, 215, 143, 22);
		frame.getContentPane().add(button);
		
		Label label_2 = new Label("User Name");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(35, 65, 83, 22);
		frame.getContentPane().add(label_2);
		
		textField_1 = new TextField();
		textField_1.setBounds(145, 65, 168, 22);
		frame.getContentPane().add(textField_1);
		
		Label label_3 = new Label("Security Check..!!");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_3.setBounds(147, 21, 152, 22);
		frame.getContentPane().add(label_3);
		
		Button button_1 = new Button("Go Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{			
				
				// gets the user to the previous page.
				try
				{
					LoginFrameNew Lframe = new LoginFrameNew();
					//Sqframe.setVisible(false);
					frame.setVisible(false);
					Lframe.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}			
			}
		});
		button_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_1.setBounds(268, 215, 83, 22);
		frame.getContentPane().add(button_1);
	}
}
