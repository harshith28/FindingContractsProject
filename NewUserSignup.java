package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class NewUserSignup {
	public JFrame frame;
	public JLabel prompt, userNameLabel, firstNameLabel, phoneLabel, skillLabel;
	public JTextField userName, firstName, phone, skills;
	public JButton back, submit;
	public String userType;
	
	public NewUserSignup(final JFrame frame, String userType) {
		this.frame = frame;
		this.userType = userType;
		if (userType.equals("labour")) {
			frame.setTitle("New Labour User");
		}
		else {
			frame.setTitle("New Contractor  User");
		}
		frame.getContentPane().setBackground(new Color(172, 82, 217));
		
		JLabel prompt = new JLabel("Please Enter Your Details");
		prompt.setBounds(50, 26, 400, 40);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Please Enter Your Details");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Please Enter Your Details", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(prompt);
		
		userNameLabel = new JLabel("User name *");
		userNameLabel.setFont(new Font("User name", Font.PLAIN, 20));
		userNameLabel.setBounds(50, 67, 114, 29);
		frame.add(userNameLabel);
		
		userName = new JTextField();
		userName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userName.setBounds(240, 67, 173, 28);
		frame.add(userName);
		
		firstNameLabel = new JLabel("Name *");
		firstNameLabel.setFont(new Font("Name", Font.PLAIN, 20));
		firstNameLabel.setBounds(50, 117, 114, 29);
		frame.add(firstNameLabel);

		firstName = new JTextField("");
		firstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		firstName.setBounds(240, 117, 173, 28);
		frame.add(firstName);
		
		phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Second name", Font.PLAIN, 20));
		phoneLabel.setBounds(50, 177, 139, 29);
		frame.add(phoneLabel);
		
		skillLabel = new JLabel("Skills");
		skillLabel.setFont(new Font("Second name", Font.PLAIN, 20));
		skillLabel.setBounds(50, 247, 139, 29);
		
		
		
		
		phone = new JTextField();
		phone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		phone.setBounds(240, 177, 173, 28);
		frame.add(phone);
		
		skills = new JTextField();
		skills.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		skills.setBounds(240, 247, 173, 28);
		if(userType.equals("labour")){
			frame.add(skillLabel);

		frame.add(skills);
		}
		
		submit = new JButton("Submit");
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		submit.setBackground(Color.WHITE);
		submit.setFont(new Font("Submit", Font.ITALIC, 15));
		submit.setBounds(190, 340, 89, 23);
		frame.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate(userName.getText(), firstName.getText(), phone.getText(), skills.getText());
			}
		});
		
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void validate(String uname, String fname, String phone, String skills ) {
		String message = "";
		if (uname.length() == 0) {
			message = "Username cannot be empty!\n";
		}
		else {
			Connection con;
			Statement s;
			ResultSet rs;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				rs = s.executeQuery("select Lid from Labourers");
				while (rs.next()) {
					if (rs.getString(1).equals(uname)) {
						message += "The username already exists. Please choose a different username!\n";
						break;
					}
				}
				rs = s.executeQuery("select Cid from contractors");
				while (rs.next()) {
					if (rs.getString(1).equals(uname)) {
						message += "The username already exists. Please choose a different username!\n";
						break;
					}
				}
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		if (fname.length() == 0) {
			message = message + "First name cannot be empty!\n";
		}
		else {
			for(char c : fname.toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "First name cannot have digits or special characters!\n";
					break;
				}
			}
		}
		if(userType.equals("labour")) {
			
		if (skills.length() == 0) {
			message = message + "Skills cannot be empty!\n";
		}
		
		else {
			for(char c : skills.toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Skills cannot have digits or special characters!\n";
					break;
				}
			}
		}
		}

		if (phone.length() == 0) {
			message = message + "Phone number cannot be empty!\n";
		}
		else {
			for(char c : phone.toCharArray()) {
				if (!Character.isDigit(c)) {
					message = message + "Phone number cannot have alphabets or special characters!\n";
					break;
				}
			}
			if (phone.length() != 10) {
					message += "Phone number must have exactly 10 digits!\n";
			}
		}
		
		
		if (message.length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			Connection con;
			Statement s;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				frame.getContentPane().removeAll();
				if (userType.equals("labour")){
					s.executeQuery("Insert into Labourers values('" + uname + "', '" + fname + "', " +phone + ", '" + skills + "')" );
					new Labour(frame, userName.getText());
					//System.out.println("ulabpurer craeted");
				}
				else {
					s.executeQuery("Insert into contractors values('" + uname + "', '" + fname + "', " +phone + ")" );
				new Contractor(frame, userName.getText());
					//System.out.println("cobtractor craeted");
				}
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
