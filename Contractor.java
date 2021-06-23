package code;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Contractor {
	public JFrame frame;
	public String cid;
	public JMenuBar menuBar;
	public JMenu profileMenu, contractsMenu, contractRequestsMenu;
	public JMenuItem viewProfile, editProfile, viewcontracts, postcontracts, editcontracts, deletecontracts, viewcontractRequests;
	public JButton back;
	public JLabel welcome;
	
	public Contractor(final JFrame frame, String username) {
		this.frame = frame;
		this.cid = username;
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select cname from contractors where cid = '" + username + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		frame.setTitle("Contractor : " + name);
		frame.getContentPane().setBackground(new Color(172, 82, 217));
		
		menuBar = new JMenuBar();
		frame.add(menuBar);
		
		profileMenu = new JMenu("Profile");
		profileMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(profileMenu);
		
		contractsMenu = new JMenu("contracts Posted by me");
		contractsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(contractsMenu);
		
		contractRequestsMenu = new JMenu("contract requests available");
		contractRequestsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(contractRequestsMenu);
		
		viewProfile = new JMenuItem("View profile");
		viewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				profileView(frame);
			}
		});
		editProfile = new JMenuItem("Edit profile");
		editProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
				profileEdit(frame);
			}
		});
		profileMenu.add(viewProfile);
		profileMenu.add(editProfile);
		
		viewcontracts = new JMenuItem("View contracts posted");
		viewcontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				
				contractsPostedView(frame);
			}
		});
		postcontracts = new JMenuItem("Post new contracts");
		postcontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
								contractsPostNew(frame);
			}
		});
		editcontracts = new JMenuItem("Edit contracts posted");
		editcontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				
				contractsPostedEdit(frame);
			}
		});
		deletecontracts = new JMenuItem("Delete contracts posted");
		deletecontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				
				contractsPostedDelete(frame);
			}
		});
		contractsMenu.add(viewcontracts);
		contractsMenu.add(editcontracts);
		contractsMenu.add(postcontracts);
		contractsMenu.add(deletecontracts);
		
		viewcontractRequests = new JMenuItem("View contracts requests");
		viewcontractRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				
				contractRequestsView(frame);
			}
		});
		contractRequestsMenu.add(viewcontractRequests );
		
		
		welcome = new JLabel("Welcome " + name + "!");
		welcome.setBounds(60, 120, 400, 40);
		Font promptFont = welcome.getFont();
		int stringWidth = welcome.getFontMetrics(promptFont).stringWidth("Welcome <name>!");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		welcome.setFont(new Font("Welcome", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(welcome);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);	
	}
	
	public void profileView(JFrame frame) {
		JLabel unameF, unameV, fnameF, fnameV = null, skillsF, skillsV = null, phoneF = null, phoneV = null; 
		unameF = new JLabel("Username:");
		unameV = new JLabel(cid);
		fnameF = new JLabel("First name:");
		phoneF = new JLabel("Phone:");
		
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from contractors where cid = '" + cid + "'");
			rs.next();
			fnameV = new JLabel(rs.getString(2));
			//skillsV = new JLabel(rs.getString(4));
			phoneV = new JLabel(rs.getString(3));
			
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		unameF.setBounds(50, 30, 100, 30);
		unameF.setFont(new Font("", Font.PLAIN, 20));
		unameV.setBounds(250, 30, 100, 30);
		unameV.setFont(new Font("", Font.PLAIN, 20));
		
		fnameF.setBounds(50, 60, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 60, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		phoneF.setBounds(50, 90, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 90, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		
		frame.add(unameF);
		frame.add(unameV);
		
		frame.add(fnameF);
		frame.add(fnameV);
		
		frame.add(phoneF);
		frame.add(phoneV);
		
		
		
		
	}
	
	public void contractsPostedView(JFrame frame) {
		String contractid, salary, skillsrequired;
		String header[] = new String[] { "Contract_id",  "Dailywage", "SkillsRequired"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		Connection con;
		Statement s1, s2;
		ResultSet r1, r2;
		try {
			con = ConnectionManager.getConnection();
			s1 = con.createStatement();
			//s2 = con.createStatement();
			r1 = s1.executeQuery("select * from contracts where cid = '" + cid + "'  order by contract_id");
			while(r1.next()) {
				
			
				
			
			contractid=r1.getString(1);
			salary=r1.getString(2);
			skillsrequired=r1.getString(3);
			dtm.addRow(new Object[] {contractid,salary,skillsrequired});
			}
			s1.close();
			//s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 380, 150);
		frame.add(scroll);
		
	}
	
	
	public void contractRequestsView(JFrame frame) {
		String contractid, position, lid, skills, skillsrequired, status;		
		String header[] = new String[] { "Contract_id", "Labourer_id", "Labourer Skills", "Skills"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		Connection con;
		Statement s1, s2, s3;
		ResultSet r1, r2, r3;
		try {
			con = ConnectionManager.getConnection();
			s1 = con.createStatement();
			s2 = con.createStatement();
			s3 = con.createStatement();
			r1 = s1.executeQuery("select * from hiringrequests where contract_id in (select contract_id from contracts where cid = '" + cid + "')");
			while (r1.next()) {
				lid = r1.getString(1);
				contractid = r1.getString(2);
				
				r2 = s2.executeQuery("select skills_required from contracts where contract_id = " + contractid);
				r2.next();
				skillsrequired = r2.getString(1);
				r3 = s3.executeQuery("select skills from labourers where lid = '" + lid + "'");
				r3.next();
				skills = r3.getString(1);
				dtm.addRow(new Object[] {contractid,lid, skills,skillsrequired});
			}
			s1.close();
			s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		//table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
	
	
	
	public void profileEdit(JFrame frame) {		
		JButton update = new JButton("Update");
		update.setBounds(200, 320, 154, 23);
		update.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		update.setBackground(Color.WHITE);
		
		JLabel unameF, unameV, fnameF, phoneF;
		final JTextField fnameV = new JTextField("");
		
		
		final JTextField phoneV = new JTextField("");
				
		
		unameF = new JLabel("Username:");
		unameV = new JLabel(cid);
		fnameF = new JLabel("First name:");
	
		phoneF = new JLabel("Phone number:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from contractors where cid = '" + cid + "'");
			rs.next();
			fnameV.setText(rs.getString(2));
		phoneV.setText(rs.getString(3));
		//skillsV.setText(rs.getString(4));
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		unameF.setBounds(50, 30, 100, 30);
		unameF.setFont(new Font("", Font.PLAIN, 20));
		unameV.setBounds(250, 30, 100, 30);
		unameV.setFont(new Font("", Font.PLAIN, 20));
		
		fnameF.setBounds(50, 60, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 60, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		phoneF.setBounds(50, 90, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 90, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateProfile(fnameV.getText(), phoneV.getText());
				}
				catch (Exception e1 ) {
					e1.printStackTrace();
				}
			}
		});
		
		
		frame.add(unameF);
		frame.add(unameV);
		
		frame.add(fnameF);
		frame.add(fnameV);
		
		frame.add(phoneF);
		frame.add(phoneV);
		
		
		frame.add(update);		
		
	}
	public void validateProfile(String fname, String phone) {
		String message = "";
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
			ResultSet r;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				s.executeQuery("update contractors set cname = '" + fname + "',cphonenumber= " + phone + " where cid = '" + cid + "'");
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(new JFrame(), "Updated successfully!");
		}
	}
	
	public void contractsPostNew(final JFrame frame) {
		final JTextField contractidT = new JTextField();
		contractidT.setBounds(150, 150, 150, 40);
		contractidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the contract_id of the contract which you want to post </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton post = new JButton("Post");
		post.setBounds(150, 220, 154, 23);
		post.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		post.setBackground(Color.WHITE);
		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (contractidT.getText()==null||contractidT.getText().length() == 0) {
					message = message + "contract_id cannot be empty!\n";
				}
				else {
					try {
						int contractid = Integer.parseInt(contractidT.getText());
						Connection con;
						Statement s;
						ResultSet r;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r = s.executeQuery("select * from contracts where contract_id = ' " + contractidT.getText()+ "'");
							if (r.next()) {
								message = message + "contract_id already exists! Please choose another contract_id\n";
							}
							s.close();
							con.close();
						} 
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch (Exception e1) {
						message = message + "Enter a valid contract_id!\n";
					}
				}
				
				if ( message.length() == 0) {
					welcome.setText("");
					frame.getContentPane().removeAll();
					frame.repaint();
					
					contractsPostNewEditPage(frame, contractidT.getText(), "post");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		
		frame.add(prompt);
		frame.add(post);
		frame.add(contractidT);
		
	}
	
	public void contractsPostNewEditPage(JFrame frame, final String contractid, final String type ) {
		JLabel contractidF, contractidV,  salaryF, skillsF;
		contractidF = new JLabel("Contract_id:");
		contractidV = new JLabel(contractid);
		
		salaryF = new JLabel("Dailywage:");
		
		skillsF = new JLabel("Skills Required:");
		
		
		final JTextField salaryV = new JTextField("");
		final JTextField skillsV = new JTextField("");
		
		if (type.equals("edit")) {
			Connection con;
			Statement s;
			ResultSet r;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				r = s.executeQuery("select * from contracts where contract_id = '" + contractid + "'");
				r.next();
				
				salaryV.setText(r.getString(2));
				
				skillsV.setText(r.getString(3));
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		JButton post = new JButton("Post");
		if (type.equals("edit")) {
			post.setText("Update");
		}
		post.setBounds(150, 260, 154, 23);
		post.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		post.setBackground(Color.WHITE);
		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contractsValidate(contractid, salaryV.getText(), skillsV.getText(), type);
			}
		});
		
		contractidF.setBounds(50, 30, 200, 30);
		contractidF.setFont(new Font("", Font.PLAIN, 20));
		contractidV.setBounds(250, 30, 100, 30);
		contractidV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		salaryF.setBounds(50, 110, 100, 30);
		salaryF.setFont(new Font("", Font.PLAIN, 20));
		salaryV.setBounds(250, 110, 150, 30);
		salaryV.setFont(new Font("", Font.PLAIN, 20));
		
		
		
		skillsF.setBounds(50, 190, 200, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 190, 150, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		frame.add(post);
		
		frame.add(skillsF);
		frame.add(skillsV);
		
		frame.add(salaryF);
		frame.add(salaryV);
		frame.add(contractidF);
		frame.add(contractidV);
	}
	
	public void contractsValidate(String contractid, String salary, String skills, String type) {
		String message = "";
		
	
		
		if (skills == null || skills.length() == 0) {
			message = message + "Skills required cannot be empty!\n";
		}
		else {
			for(char c : skills.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Skills required cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (salary == null || salary.length() == 0) {
			message = message + "Dailywage  cannot be empty!\n";
		}
		else {
			try {
				Integer.parseInt(salary);
			}
			catch (NumberFormatException e) {
				message += "Dailywage cannot have alphabets or special characters!\n";
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
				if (type.equals("post")) {
					s.executeQuery("insert into contracts values('" +  contractid + "', " + salary + ", '" + skills + "', '" + cid + "')");
				}
				else {
					s.executeQuery("update contracts set dailywage = '" + salary + "', skills_required = '" + skills + "' where contract_id = '" + contractid + "' and cid = '" + cid + "'");
				}
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			if (type.equals("post")) {
				frame.getContentPane().removeAll();
				frame.repaint();
				contractsPostNew(frame);
				JOptionPane.showMessageDialog(new JFrame(), "Posted successfully!");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Updated successfully!");
			}
			
		}
		
	}
	
	public void contractsPostedEdit(final JFrame frame) {
		final JTextField contractidT = new JTextField();
		contractidT.setBounds(150, 150, 150, 40);
		contractidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the contract_id of the contract which you want to edit </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton edit = new JButton("Update");
		edit.setBounds(150, 220, 154, 23);
		edit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		edit.setBackground(Color.WHITE);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (contractidT.getText().length() == 0) {
					message = message + "contract_id cannot be empty!\n";
				}
				else {
					try {
						int contractid = Integer.parseInt(contractidT.getText());
						Connection con;
						Statement s;
						ResultSet r;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r = s.executeQuery("select * from contracts where contract_id = '" + contractid + "'");
							if (!r.next()) {
								message = message + "contract_id does not exist!\n";
							}
							else {
								r = s.executeQuery("select * from contracts where contract_id = '" + contractid + "' and cid = '" + cid + "'");
								if (!r.next() ) {
									message = message + "You have not posted this contract, you cannot edit it!\n";
								}
							}
							s.close();
							con.close();
						} 
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch (Exception e1) {
						message = message + "Enter a valid contract_id!\n";
					}
				}
				
				if ( message.length() == 0) {
					welcome.setText("");
					frame.getContentPane().removeAll();
					frame.repaint();
					contractsPostNewEditPage(frame, contractidT.getText(), "edit");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		
		
		frame.add(prompt);
		frame.add(edit);
		frame.add(contractidT);
	}
	
	public void contractsPostedDelete(JFrame frame) {
		final JTextField contractidT = new JTextField();
		contractidT.setBounds(150, 150, 150, 40);
		contractidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the contract_id of the contract which you want to delete </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton delete = new JButton("Delete");
		delete.setFont(new Font("Delete", Font.PLAIN, 15));
		delete.setBounds(170, 220, 100, 30);
		delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delete.setBackground(Color.WHITE);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (contractidT.getText().length() == 0) {
					message = message + "contract_id cannot be empty!\n";
				}
				else {
					try {
						int contractid = Integer.parseInt(contractidT.getText());
						Connection con;
						Statement s;
						ResultSet r1, r2;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r1 = s.executeQuery("select * from contracts where contract_id = " + contractid);
							if (! r1.next()) {
								message = message + "contract_id does not exist!\n";
							}
							else {
								r2 = s.executeQuery("select * from contracts where contract_id = " + contractid + " and cid = '" + cid + "'");
								if (!r2.next() ) {
									message = message + "You have not posted this contract, you cannot delete it!\n";
								}
							}
							s.close();
							con.close();
						} 
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch (Exception e1) {
						message = message + "Enter a valid contract_id!\n";
					}
				}
				
				if ( message.length() == 0) {
					int contractid = Integer.parseInt(contractidT.getText());
					Connection con;
					Statement s;
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						s.executeQuery("delete from contracts where contract_id = " + contractid + " and cid = '" + cid + "' ");
						s.executeQuery("commit");
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
					contractidT.setText("");
					JOptionPane.showMessageDialog(new JFrame(), "Deleted successfully!");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	});
		
		frame.add(contractidT);
		frame.add(prompt);
		frame.add(delete);
	}
}