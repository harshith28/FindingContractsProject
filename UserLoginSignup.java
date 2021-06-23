package code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLoginSignup {
	public JFrame frame;
	public JLabel prompt;
	public JButton labour, contractor, back;
	
	public UserLoginSignup(final JFrame frame, final String userType) {
		this.frame = frame;
		if (userType.equals("login")) {
			frame.setTitle("User Log-in");
		}
		else {
			frame.setTitle("User Sign-up");
		}
		frame.getContentPane().setBackground(new Color(172, 82, 217));
		
		prompt = new JLabel("Select the type of user:");
		prompt.setBounds(80, 50, 350, 60);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Select the type of user:");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Select the type of user:", Font.ITALIC, Math.min(newFontSize, 60)));
		frame.add(prompt);
		
		labour = new JButton("labour");
		labour.setBounds(70,150,150, 40);
		labour.setFont(new Font("labour", Font.PLAIN, 20));
		labour.setBackground(Color.white);
		labour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(labour);
		labour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				if (userType.equals("login")) {
					new LoginWithUsername(frame, "labour");
				}
				else {
					new NewUserSignup(frame, "labour");
				}
				
			}
		});
		
		contractor = new JButton("Contractor");
		contractor.setBounds(250,150,160, 40);
		contractor.setFont(new Font("Contractor", Font.PLAIN, 18));
		contractor.setBackground(Color.white);
		contractor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		contractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				if (userType.equals("login")) {
					new LoginWithUsername(frame, "contractor");
				}
				else {
					new NewUserSignup(frame, "contractor");
				}
			}
		});
		frame.add(contractor);
		
	
		
		frame.setSize(500, 440);
		frame.setLayout(null);
		frame.setVisible(true);
	}

}
