package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginWithUsername {
	public JFrame frame;
	public String typeOfUser;
	public JLabel prompt;
	public JButton submit, back;
	public JTextField username;
	
	public LoginWithUsername(final JFrame frame, String typeOfUser ) {
		this.typeOfUser = typeOfUser;
		this.frame = frame;
		frame.setTitle("Login as a " + typeOfUser + "!");
		frame.getContentPane().setBackground(new Color(172, 82, 217));
		
		prompt = new JLabel("Enter your user name:");
		prompt.setBounds(50, 56, 400, 40);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Enter your user name");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Enter your user name", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(prompt);
		
		submit = new JButton("Submit");
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		submit.setBackground(Color.WHITE);
		submit.setFont(new Font("Submit", Font.ITALIC, 15));
		submit.setBounds(190, 240, 89, 23);
		frame.add(submit);
		 submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate(username.getText());
			}
		});
		
		username = new JTextField();
		username.setBounds(130, 150, 200, 30);
		
		frame.add(username);
	
		
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void validate(String username) {
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			boolean flag = false;
			if (typeOfUser.equals("labour")) {
				rs = s.executeQuery("select lid from labourers");
			}
			else {
				rs = s.executeQuery("select cid from contractors");
			}
				while(rs.next()) {
					String user = rs.getString(1);
					if (user.equals(username)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					frame.getContentPane().removeAll();
					if (typeOfUser.equals("labour")) {
						new Labour(frame, username);
						//System.out.println("Existing Labour");
					}
					else {
						new Contractor(frame, username);
						//System.out.println("Existing Contractor");
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Username doesnot exist! Please enter a valid username!", "error", JOptionPane.ERROR_MESSAGE);
				}				
			
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
