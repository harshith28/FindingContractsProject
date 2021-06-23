package code;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Labour {
	public JFrame frame;
	public String lid;
	public JMenuBar menuBar;
	public JMenu profileMenu, hiringrecords, contracts;
	public JMenuItem viewProfile, editProfile, viewcontractsapplied, deletecontractsapplied, viewcontracts, applycontracts;
	public JLabel welcome;
	public JButton back;
	
	public Labour(final JFrame frame, String username) {
		
		this.lid = username;
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select Lname from labourers where Lid = '" + username + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		frame.setTitle("Labour: " + name);
		frame.getContentPane().setBackground(new Color(172, 82, 217));
	
		welcome = new JLabel("Welcome " + name + "!");
		welcome.setBounds(60, 120, 400, 40);
		Font promptFont = welcome.getFont();
		int stringWidth = welcome.getFontMetrics(promptFont).stringWidth("Welcome namelong!");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		welcome.setFont(new Font("Welcome", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(welcome);
		
		menuBar = new JMenuBar();
		frame.add(menuBar);
		
		profileMenu = new JMenu("Profile");
		profileMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(profileMenu);
		
		hiringrecords = new JMenu("Contracts applied by me");
		hiringrecords.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(hiringrecords);
		
		contracts = new JMenu("Contracts available");
		contracts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(contracts);
		
		viewProfile = new JMenuItem("View profile");
		viewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
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
		
		viewcontractsapplied = new JMenuItem("View Contracts applied");
		viewcontractsapplied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
				contractsappliedView(frame);
			}
		});
		deletecontractsapplied = new JMenuItem("Delete contracts applied");
		deletecontractsapplied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
				contractsappliedDelete(frame);
			}
		});
		
		hiringrecords.add(viewcontractsapplied);
		hiringrecords.add(deletecontractsapplied);
		
		viewcontracts = new JMenuItem("View available contracts");
		viewcontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
				contractsView(frame);
			}
		});
		
		applycontracts = new JMenuItem("Apply for new contracts");
		applycontracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				//frame.add(back);
				contractsapply(frame);
			}
		});
		contracts.add(viewcontracts);
		contracts.add(applycontracts);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);	
		this.frame = frame;
	}
	
	public void profileView(JFrame frame) {
		JLabel unameF, unameV, fnameF, fnameV = null, skillsF, skillsV = null, phoneF = null, phoneV = null; 
		unameF = new JLabel("Username:");
		unameV = new JLabel(lid);
		fnameF = new JLabel("First name:");
		phoneF = new JLabel("Phone:");
		skillsF = new JLabel("Skills:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from labourers where lid = '" + lid + "'");
			rs.next();
			fnameV = new JLabel(rs.getString(2));
			skillsV = new JLabel(rs.getString(4));
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
		
		
		skillsF.setBounds(50, 120, 150, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 120, 200, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		frame.add(unameF);
		frame.add(unameV);
		
		frame.add(fnameF);
		frame.add(fnameV);
		
		frame.add(phoneF);
		frame.add(phoneV);
		
		
		frame.add(skillsF);
		frame.add(skillsV);
		
	}
	
	public void profileEdit(JFrame frame) {		
		JButton update = new JButton("Update");
		update.setBounds(200, 320, 154, 23);
		update.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		update.setBackground(Color.WHITE);
		
		JLabel unameF, unameV, fnameF, skillsF, phoneF;
		final JTextField fnameV = new JTextField("");
		
		
		final JTextField phoneV = new JTextField("");
				final JTextField skillsV = new JTextField("");
		
		unameF = new JLabel("Username:");
		unameV = new JLabel(lid);
		fnameF = new JLabel("First name:");
	
		phoneF = new JLabel("Phone number:");
		skillsF = new JLabel("Skills:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from labourers where lid = '" + lid + "'");
			rs.next();
			fnameV.setText(rs.getString(2));
		phoneV.setText(rs.getString(3));
		skillsV.setText(rs.getString(4));
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
		
		
		
		skillsF.setBounds(50, 120, 150, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 120, 200, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateProfile(fnameV.getText(), phoneV.getText(), skillsV.getText());
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
		
		frame.add(skillsF);
		frame.add(skillsV);
		frame.add(update);		
		
	}
	
	public void contractsappliedView(JFrame frame) {
		String contractid, salary, skillsrequired;		
		String header[] = new String[] { "Contract_id", "Dailywage", "Skills Required"};
		
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
			s2 = con.createStatement();
			r1 = s1.executeQuery("select * from hiringrecords where lid = '" + lid + "'");
			while (r1.next()) {
				contractid = r1.getString(2);

				r2 = s2.executeQuery("select dailywage, skills_required from contracts where contract_id = " + contractid + "" );
				r2.next();
				salary = r2.getString(1);
				skillsrequired = r2.getString(2);
				//position = r2.getString(3);
				dtm.addRow(new Object[] {contractid, salary, skillsrequired});
			}
			s1.close();
			s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		
		table.setFont(new Font("", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 380, 150);
		frame.add(scroll);
	}
	
	public void contractsView(JFrame frame) {
		String contractid, salary, skillsrequired, cid;
		String header[] = new String[] { "Contract_id",  "Dailywage", "SkillsRequired","Contactor_id"};
		
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
			r1 = s1.executeQuery("select Cid, contract_id, dailywage, skills_required from contracts where contract_id not in (select contract_id from hiringrecords where lid = '" + lid + "')  order by contract_id");
			while(r1.next()) {
				
			
				
			
			cid=r1.getString(1);
			contractid=r1.getString(2);
			salary=r1.getString(3);
			skillsrequired=r1.getString(4);
			dtm.addRow(new Object[] {contractid,salary,skillsrequired,cid});
			}
			s1.close();
			//s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
	
	public void validateProfile(String fname, String phone, String skills) {
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
		
		
		
		if (skills.length() == 0) {
			message = message + "Skills cannot be empty!\n";
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
				s.executeQuery("update labourers set lname = '" + fname + "',lphonenumber= " + phone + ",skills= '" + skills + "' where lid = '" + lid + "'");
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
	
	public void contractsappliedDelete(JFrame frame) {
		final JTextField contractidT = new JTextField();
		contractidT.setBounds(150, 150, 150, 40);
		contractidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the contract_id of the contract which you want to remove the application </p>");
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
						int iid = Integer.parseInt(contractidT.getText());
						Connection con;
						Statement s;
						ResultSet r1, r2;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r1 = s.executeQuery("select * from internships where contract_id = " + iid);
							if (! r1.next()) {
								message = message + "contract_id does not exist!\n";
							}
							else {
								r2 = s.executeQuery("select * from internshipsapplied where contract_id = " + iid + " and lid = '" + lid + "'");
								if (!r2.next() ) {
									message = message + "You have not applied to this internship yet!\n";
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
						s.executeQuery("delete from hiringrecords where contract_id = '" + contractid + "' and lid = '" + lid + "' ");
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
		frame.add(prompt);
		frame.add(delete);
		frame.add(contractidT);
	}
	
	public void contractsapply(JFrame frame) {
		final JTextField contractidT = new JTextField();
		contractidT.setBounds(150, 150, 150, 40);
		contractidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the contract_id of the contract which you want to apply for </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton apply = new JButton("Apply");
		apply.setFont(new Font("Apply", Font.PLAIN, 15));
		apply.setBounds(170, 220, 100, 30);
		apply.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		apply.setBackground(Color.WHITE);
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "",cid="";
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
							r1 = s.executeQuery("select * from contracts where contract_id = " +  contractid);
							if (! r1.next()) {
								message = message + "contract_id does not exist!\n";
							}
							else {
								cid=r1.getString(4);
								r2 = s.executeQuery("select * from hiringrecords where contract_id = " +  contractid + " and lid = '" + lid + "'");
								if (r2.next() ) {
									message = message + "You have already applied to this contract!\n";
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
						s.executeQuery("insert into hiringrecords values ('" + cid + "', '" + contractid + "', '" + lid + "')");
						s.executeQuery("commit");
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
					contractidT.setText("");
					JOptionPane.showMessageDialog(new JFrame(), "Applied successfully!");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		frame.add(contractidT);
		frame.add(prompt);
		frame.add(apply);
		
		
	}
}


