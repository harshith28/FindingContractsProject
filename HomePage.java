package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
	public JFrame frame;
	public JLabel title, prompt, marketing;
	public JButton login, signup;
	
	public HomePage(final JFrame frame) {
		this.frame = frame;
		frame.setTitle("InternHub - Home Page");
		frame.getContentPane().setBackground(new Color(172, 82, 217));
		
		title = new JLabel("Finding Contracts!");
		title.setBounds(60, 50, 350, 60);
		Font titleFont = title.getFont();
		int stringWidth = title.getFontMetrics(titleFont).stringWidth("Welcome to InternHub!");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(titleFont.getSize() * widthRatio);
		title.setFont(new Font("Finding Contracts!", Font.ITALIC, Math.min(newFontSize, 60)));
		frame.add(title);
		
		prompt = new JLabel("(Log-in for existing users | Sign-up for new users)");
		prompt.setBounds(90, 100, 350, 40);
		frame.add(prompt);
		
		login = new JButton("Log-in");
		login.setBounds(80,200,150, 40);
		login.setFont(new Font("Log-in", Font.PLAIN, 20));
		login.setBackground(Color.white);
		login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(login);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new UserLoginSignup(frame, "login");
			}
		});
		
		signup = new JButton("Sign-up");
		signup.setBounds(250,200,150, 40);
		signup.setFont(new Font("Sign-up", Font.PLAIN, 20));
		signup.setBackground(Color.white);
		signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(signup);
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new UserLoginSignup(frame, "signup" );
			}
		});
		
		
			
		frame.setSize(500, 440);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}