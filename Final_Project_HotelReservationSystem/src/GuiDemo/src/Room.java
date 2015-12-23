/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */


package GuiDemo.src;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
/**
 * Frame where the room booking for the customer takes 
 * place  here.
 * @author Sridhar
 *
 */
public class Room 
{
	public JFrame frame;
	public static String table_click;
	public static String table_click1;
	public static String table_click2;
	
	public CustomerInfo window;
	String s1;
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "sridhar007";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	//private final String dbName = "test";
	private final String dbName = "hotel_reservation_system";
	
	/** The name of the table we are testing with */
	Connection conn;
	public  JTable table;
	private String date1;
	private String date2;
	private String occupants;
	private String roomType;
	public String getRoomType() 
	{
		return roomType;
	}

	public void setRoomType(String roomType) 
	{
		this.roomType = roomType;
	}

	public String getOccupants()
	{
		return occupants;
	}

	public void setOccupants(String occupants) 
	{
		this.occupants = occupants;
	}

	public String getDate1() 
	{
		return date1;
	}

	public String getDate2() 
	{
		return date2;
	}

	public void setDate2(String date2) 
	{
		this.date2 = date2;
	}

	public void setDate1(String date1) 
	{
		this.date1 = date1;
	}

	/**
	 * Get a new database connection
	 * 
	 * @return connection object
	 * @throws SQLException when the connection fails
	 */
	
	public Connection getConnection() throws SQLException {
		 conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	public Room() 
	{
		initialize();
	}
	
	/**
	 * Initialize all the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		frame.setBounds(100, 100, 937, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnFromLastView = new JButton("Check Availability");
		btnFromLastView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFromLastView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				/**
				 * Lists all the available rooms in the hotel for the specified room type. 
				 */
				try{
					String query= "SELECT room_number,status,type,cost,additional_features FROM room_table WHERE (start_date<'"+date1+"') and (end_date<'"+ date2 + "') and(type='"+getRoomType()+"');";
					System.out.println("The query for the date is"+query);
					PreparedStatement ps=conn.prepareStatement(query);
					ResultSet r=ps.executeQuery();
					if(r.next())
					{
						table.setModel(DbUtils.resultSetToTableModel(r));
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "There are no rooms available..!!!");
					}			
				}
				catch(Exception ex)
				{
				System.out.println("error in getting query"+ex);
				}
			}
		});
		btnFromLastView.setBounds(115, 117, 185, 29);
		frame.getContentPane().add(btnFromLastView);

		JButton btnBook = new JButton("Book!");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBook.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
		
				EventQueue.invokeLater(new Runnable() 
				{
					@SuppressWarnings("static-access")
					public void run() 
					{
						try 
						{
							window = new CustomerInfo();
							frame.setVisible(false);
							window.setCidate1(date1);
							window.setCidate2(date2);
							window.setOccupants(occupants);
							window.setRoomtype(roomType);
							window.getFrame().setVisible(true);
							System.out.println("The string of table_click"+table_click);
							window.label.setText(table_click);
							window.label_1.setText(table_click2);
							window.label_2.setText(table_click1);
						
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnBook.setBounds(557, 117, 179, 29);
		frame.getContentPane().add(btnBook);
		table = new JTable();
		table.setBackground(new Color(222, 184, 135));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		//button.setBackground(new Color(222, 184, 135));
		//scrollPane.setViewportView.setBackground(Color.white);
		scrollPane.getViewport().setBackground(new Color(222, 184, 135));
		/**
		 * On clicking on this table the particular record is selected.
		 * The values of this record is passed to the next frame.
		 */
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				//adding old code
						
						try{
						
							int row=table.getSelectedRow();
							
							//int row=table
						    table_click=(table.getModel().getValueAt(row, 0).toString());
							 table_click1=(table.getModel().getValueAt(row, 2).toString());
							 table_click2=(table.getModel().getValueAt(row, 3).toString());
							System.out.println("From addmouse listener"+table_click);					
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, ex);
							
						}
					}
	
			});
		scrollPane.setBounds(10, 183, 911, 386);
		frame.getContentPane().add(scrollPane);
		
		Label label = new Label("             CHECK AVAILABILITY PAGE!");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(283, 52, 300, 22);
		frame.getContentPane().add(label);
		
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
		
		JButton button_1 = new JButton("Logout");
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		button_1.setBackground(new Color(222, 184, 135));
		button_1.setBounds(755, 0, 166, 29);
		frame.getContentPane().add(button_1);
		
		
	}
}
