/**
 * Name of the Application: Hotel Reservation System:
	Team Members: Sridhar Ganapathy and Ankita Kashyap
	Name of the Course: Modern Programming in Java CIS 551
	Description: Final Project
 */

package GuiDemo.src;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;

/**
 * This class is to display the confirmation of the 
 * room booked by the user. Has the option to sned mail and also to 
 * convert into pdf.
 * @author Sridhar
 *
 */
@SuppressWarnings("serial")
public class ConfirmOrder extends JFrame 
{
	private JPanel contentPane;
	public static JLabel label_9;
	public static ConfirmOrder frame;
	public static JLabel lblNewLabel_1;
	public static JLabel label_10;
	public static long confirmation_No = 03223222;
	public static JLabel label_11;
	public static JLabel label_12;
	public static JLabel label_13;
	public static JLabel label_14;
	public static JLabel label_15;
	public static JLabel label_16;
	public static JLabel label_17;
	public static JLabel label_18;
	public static JLabel label_3;
	public static JLabel label_4;
	public static JLabel label_6;
	public static  String cnstrtdate;
	public static  String cnenddate;
	public static String getCnstrtdate()
	{
		return cnstrtdate;
	}
	public static void setCnstrtdate(String cnstrtdate1) {
		cnstrtdate = cnstrtdate1;
	}
	
	public  String getCnenddate() {
		return cnenddate;
	}
	public static  void setCnenddate(String cnenddate1) {
		cnenddate = cnenddate1;
	}
	public ConfirmOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order Successful..!!!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(274, 0, 145, 23);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Room Type");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(140, 380, 101, 16);
		contentPane.add(label);
		
		JLabel lblCost = new JLabel("Rent");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCost.setBounds(140, 343, 61, 16);
		contentPane.add(lblCost);
		
		JLabel label_2 = new JLabel("Room No.");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(140, 316, 75, 16);
		contentPane.add(label_2);
		
		JLabel lblZipcode = new JLabel("ZipCode");
		lblZipcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZipcode.setBounds(140, 192, 61, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(164, 164, 37, 16);
		contentPane.add(lblState);
		
		JLabel lblCountry = new JLabel("City");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCountry.setBounds(164, 137, 37, 16);
		contentPane.add(lblCountry);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(140, 84, 101, 16);
		contentPane.add(lblAddress);
		
		JLabel label_7 = new JLabel("Last Name");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(140, 57, 75, 16);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("First Name");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(138, 30, 89, 16);
		contentPane.add(label_8);
		/**
		 * This button helps to send the email to customer
		 */
		JButton btnNewButton = new JButton("Send Confirmation to Mail");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String[] recipients = {label_15.getText()};
				String Title ="Congratulations!! You have booked the room successfully"+"\n"+"\n";				
				String Title1 = "Your Booking Confirmation Number is: #"+ ++confirmation_No+"\n"+"\n";
				String fn = "First Name: " +lblNewLabel_1.getText()+"\n" ;
				String ln = "Last  Name: " +label_10.getText()+"\n";
				String adrs = "Address: "+label_11.getText()+"\n";
				String city = "City: "+label_12.getText()+"\n";
				String St = "State: "+label_13.getText()+"\n";
				String zip = "ZIP Code: "+label_14.getText()+"\n";
				String ctry = "Country: "+label_3.getText()+"\n";
				String emid = "Email id: "+label_15.getText()+"\n";
				String phn = "Phone No: "+label_4.getText()+"\n";
				String rmno = "Room No: "+label_16.getText()+"\n";
				String rmrnt = "Room Rent: "+label_17.getText()+" for one day"+"\n";
				String rmtyp = "Room Type: "+label_18.getText()+"\n";
				String chkin = "Check-in date: "+getCnstrtdate()+"\n";
				String chkout = "Check-out date:"+getCnenddate()+"\n";
				String BigString = Title+Title1+fn+ln+adrs+city+St+zip+ctry+emid+phn+rmno+rmrnt+rmtyp+chkin+chkout ;
				
				  if(EmailSender.SendMail("HotelReservationSystemSyracuse@gmail.com", "hotelreserve007",BigString, recipients))
				  {			  
					  
					  JOptionPane.showMessageDialog(null, "Please check your mail for confirmation!");					  
					  System.out.println("email sent from the Confirm Order");
				  }
				  else
				  {
					  System.out.println("failed");
					  JOptionPane.showMessageDialog(null, "Mail sending Failed");
				  }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(150, 412, 193, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(263, 34, 193, 12);
		contentPane.add(lblNewLabel_1);
		
		label_10 = new JLabel("");
		label_10.setBounds(263, 57, 193, 20);
		contentPane.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setBounds(263, 84, 193, 16);
		contentPane.add(label_11);
		
		label_12 = new JLabel("");
		label_12.setBounds(263, 137, 193, 20);
		contentPane.add(label_12);
		
		label_13 = new JLabel("");
		label_13.setBounds(263, 164, 193, 20);
		contentPane.add(label_13);
		
		label_14 = new JLabel("");
		label_14.setBounds(263, 192, 193, 20);
		contentPane.add(label_14);
		
		label_15 = new JLabel("");
		label_15.setBounds(263, 251, 193, 23);
		contentPane.add(label_15);
		
		label_16 = new JLabel("");
		label_16.setBounds(263, 316, 193, 20);
		contentPane.add(label_16);
		
		label_17 = new JLabel("");
		label_17.setBounds(263, 343, 193, 20);
		contentPane.add(label_17);
		
		JLabel lblZipcode_1 = new JLabel("Country");
		lblZipcode_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZipcode_1.setBounds(140, 219, 61, 16);
		contentPane.add(lblZipcode_1);
		
		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmailId.setBounds(140, 251, 61, 16);
		contentPane.add(lblEmailId);
		
		JLabel lblPhoneNo = new JLabel("Phone No");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNo.setBounds(140, 285, 75, 16);
		contentPane.add(lblPhoneNo);
		
		label_3 = new JLabel("");
		label_3.setBounds(263, 223, 193, 20);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setBounds(263, 285, 193, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(249, 292, 193, 20);
		contentPane.add(label_5);
		
		label_18 = new JLabel("");
		label_18.setBounds(249, 380, 193, 20);
		contentPane.add(label_18);
		
		JLabel lblAptno = new JLabel("Apt_No");
		lblAptno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAptno.setBounds(140, 110, 75, 16);
		contentPane.add(lblAptno);
		
		label_6 = new JLabel("");
		label_6.setBounds(263, 110, 193, 16);
		contentPane.add(label_6);
		/**
		 * Generates the pdf when the button is hit
		 */
		JButton btnGeneratePdf = new JButton("Generate PDF");
		btnGeneratePdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String Title ="Congratulations!! You have booked the room successfully"+"\n"+"\n";				
				String Title1 = "Your Booking Confirmation Number is: #"+ ++confirmation_No+"\n"+"\n";
				String fn = "First Name: " +lblNewLabel_1.getText()+"\n" ;
				String ln = "Last  Name: " +label_10.getText()+"\n";
				String adrs = "Address: "+label_11.getText()+"\n";
				String city = "City: "+label_12.getText()+"\n";
				String St = "State: "+label_13.getText()+"\n";
				String zip = "ZIP Code: "+label_14.getText()+"\n";
				String ctry = "Country: "+label_3.getText()+"\n";
				String emid = "Email id: "+label_15.getText()+"\n";
				String phn = "Phone No: "+label_4.getText()+"\n";
				String rmno = "Room No: "+label_16.getText()+"\n";
				String rmrnt = "Room Rent: "+label_17.getText()+" for one day"+"\n";
				String rmtyp = "Room Type: "+label_18.getText()+"\n";
				String chkin = "Check-in date: "+getCnstrtdate()+"\n";
				String chkout = "Check-out date:"+getCnenddate()+"\n";
				String BigString = Title+Title1+fn+ln+adrs+city+St+zip+ctry+emid+phn+rmno+rmrnt+rmtyp+chkin+chkout ;
				
				Document document=new Document(); 
				 try 
				 {
					String flnm = lblNewLabel_1.getText();
					String mknm = flnm+label_16.getText()+".pdf";
					 PdfWriter.getInstance(document,new FileOutputStream(mknm));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 document.open(); 
				 try {
					document.add(new Paragraph(BigString));
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				 document.close(); 
				 JOptionPane.showMessageDialog(null, "PDF format of the Confirmation generated at C:\\Users\\Sridhar\\workspace_for_mars\\Final_Project_HotelReservationSystem");
			}
		});
		btnGeneratePdf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGeneratePdf.setBounds(366, 412, 137, 23);
		contentPane.add(btnGeneratePdf);
	}
}
