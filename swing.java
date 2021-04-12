import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.sql.*;

public class swing {

	public static void main(String[] args) {
		
		login page = new login();
		page.but.addActionListener(e -> {
			int c = 0;
			if(!page.pass.getText().equals("") || !page.uName.getText().equals("")) {
				if(page.uName.getText().equals("OOP") && page.pass.getText().equals("CS201")) {
					c = 1;
				}
			}	
			if(c == 1) {
				hsp hos = new hsp();
			} else
				JOptionPane.showMessageDialog(null, "Invalid Login", "Login", JOptionPane.ERROR_MESSAGE);
		});
	}

}

class pl {
	static JPanel panel = new JPanel();
	JLabel label;
	pl() {
		label = new JLabel();
		panel.add(label);
	}
	
}

class window{
	JFrame frame = new JFrame();
	window() {
		frame.setVisible(true);
		frame.setSize(800, 800);
		frame.setResizable(false);
	}
	
}

class form {
	
	JButton but = new JButton("Submit");
	JTextField aa = new JTextField();
	JTextField ba = new JTextField();
	JTextField ca = new JTextField();
	JTextField da = new JTextField();
	
	form(String status) {
		
		JPanel ab = new JPanel();
		JLabel a = new JLabel(status);
		a.setBounds(0, 0, 90, 30);
		a.setFont(new Font("Serif", Font.BOLD, 20));
		aa.setBounds(100, 0, 200, 30);
		ab.add(a);
		ab.add(aa);
		ab.setLayout(null);
		ab.setBounds(250, 130, 300, 30);
		
		JPanel bb = new JPanel();
		JLabel b = new JLabel("Gender");
		b.setBounds(0, 0, 90, 30);
		b.setFont(new Font("Serif", Font.BOLD, 20));
		ba.setBounds(100, 0, 200, 30);
		bb.add(b);
		bb.add(ba);
		bb.setLayout(null);
		bb.setBounds(250, 90, 300, 30);
		
		JPanel cb = new JPanel();
		JLabel c = new JLabel("Age");
		c.setBounds(0, 0, 90, 30);
		c.setFont(new Font("Serif", Font.BOLD, 20));
		ca.setBounds(100, 0, 200, 30);
		cb.add(c);
		cb.add(ca);
		cb.setLayout(null);
		cb.setBounds(250, 50, 300, 30);
		
		JPanel db = new JPanel();
		JLabel d = new JLabel("Name");
		d.setBounds(0, 0, 90, 30);
		d.setFont(new Font("Serif", Font.BOLD, 20));
		da.setBounds(100, 0, 200, 30);
		db.add(d);
		db.add(da);
		db.setLayout(null);
		db.setBounds(250, 10, 300, 30);
		
		JPanel panel = new JPanel();
		panel.add(ab);
		panel.add(bb);
		panel.add(cb);
		panel.add(db);
		
		but.setBounds(250, 180, 200, 30);
		but.setFont(new Font("Serif", Font.PLAIN, 30));
		but.setFocusable(false);
		but.setBorder(null);
		
		panel.add(but);
		panel.setSize(800, 800);
		panel.setLayout(null);
		
		window win = new window();
		win.frame.add(panel);
		
	}
}

interface required {
	final String url = "jdbc:mysql://localhost:3306/abc";
	final String user = "root";
	final String password = "mayur9898SH$";
	
	public void executeShow(String query, String status);
	public void update() throws SQLException, ClassNotFoundException;
	public void executeAdd(String query, String status, String message);
}

class hsp implements ActionListener, required {
	
	JButton but1 = new JButton();
	JButton but2 = new JButton();
	JButton but3 = new JButton();
	
	hsp() {
		JFrame frame = new JFrame();
		
		but1.setBounds(50, 370, 200, 30);
		but2.setBounds(290, 370, 200, 30);
		but3.setBounds(530, 370, 200, 30);
		
		but2.setText("Add new Entry");
		but1.setText("See the old ones");
		but3.setText("Update the entries");
		
		but1.setFocusable(false);
		but2.setFocusable(false);
		but3.setFocusable(false);
		
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		
		but1.setBorder(null);
		but2.setBorder(null);
		but3.setBorder(null);
		
		frame.setVisible(true);
		frame.setSize(800, 800);
		frame.setLayout(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(but1);
		frame.add(but2);
		frame.add(but3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == but2) {
			
			String queryDoc = "insert into doctor values(?,?,?,?)";
			String queryPat = "insert into patients values(?,?,?,?)";
			subsight2 sub = new subsight2();
			sub.but1.addActionListener(a -> {
				executeAdd(queryDoc, "Appointement", "Added a new Doctor...!");
			});
			sub.but2.addActionListener(a -> {
				executeAdd(queryPat, "ID", "Added a new Patient...!");
			});
			
		} else if(e.getSource() == but1){
			
			subsight1 sub = new subsight1();
			String queryDoc = "select * from doctor";
			String queryPat = "select * from patients";
			sub.but1.addActionListener(a -> {
				executeShow(queryDoc, "ID");
			});
			sub.but2.addActionListener(a -> {
				executeShow(queryPat, "Appointment");
			});
			
		} else {
			try {
				update();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			} catch (NullPointerException msg) {
				System.out.println(msg.getMessage());
			}
		}
	}
	
	public void executeShow(String query, String status) {
		
		String[] heading = {"Name", "Age", "Gender", status};
		Object[][] value = new Object[10][4];
		int index = 0;
		
		try {
			Class.forName("java.sql.DriverManager");
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			 
			while(rs.next()) {
				value[index][0] = rs.getString(1);
				value[index][1] = rs.getInt(2);
				value[index][2] = rs.getString(3);
				value[index][3] = rs.getString(4);
				index++;
			}
			
			JTable table = new JTable(value, heading);
			table.setPreferredSize(new Dimension(600, 200));
			window win = new window();
			win.frame.add(table);
			win.frame.setLayout(new FlowLayout());
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void update() throws SQLException, ClassNotFoundException {
		String input1 = JOptionPane.showInputDialog("What field would you like to handle?");
		
		String val1 = "Doctor";
		String val2 = "Patient";
		String value1 = "patients";
		String value2 = "doctor";
		String status = "status";
		
		Class.forName("java.sql.DriverManager");
		Connection con = DriverManager.getConnection(url, user, password);
		
		if(input1.equals("Doctor")) {
			String input2 = JOptionPane.showInputDialog("What " + val1 + " would you like to delete?");
			String query1 = "delete from " + value2 + " where "  + status + " = '" + input2.toString() + "'";
			Statement st = con.prepareStatement(query1);
			st.executeUpdate(query1);
			st.close();
			con.close();
		} else if(input1.equals("Patient")) {
			String input2 = JOptionPane.showInputDialog("What " + val2 + " would you like to delete?");
			String query2 = "delete from " + value1 + " where "  + status + " = '" + input2.toString() + "'";
			Statement st = con.prepareStatement(query2);
			st.executeUpdate(query2);
			st.close();
			con.close();
		} else {
			JOptionPane.showMessageDialog(null, 
					"Invalid field",
					"Wrong",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void executeAdd(String query, String status, String message) {
		
		try {
			Class.forName("java.sql.DriverManager");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(query);
			
			form f = new form(status);
			f.but.addActionListener(el -> {
				int c = 0;
				if(el.getSource() == f.but) {
					String name = f.da.getText();
					if(!name.equals("")) {
						c++;
						try {
							st.setString(1, name);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					String age = f.ca.getText();
					if(!age.equals("")) {
						c++;
						try {
							st.setInt(2, Integer.parseInt(age));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
					String gender = f.ba.getText();
					if(!gender.equals("")) {
						c++;
						try {
							st.setString(3, gender);
						} catch (NumberFormatException | SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					String stat = f.aa.getText();
					if(!stat.equals("")) {
						c++;
						try {
							st.setString(4, stat);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					if(c == 4) {
						try {
							st.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} 
						JOptionPane.showMessageDialog(null, message);
						f.aa.setText("");
						f.ba.setText("");
						f.ca.setText("");
						f.da.setText("");
						
					} 
					else {
						JOptionPane.showMessageDialog(null, 
								"There are some missing fields", 
								"Incomplete Registration", 
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}

class subsight1 {
	JButton but1; 
	JButton but2; 
	
	subsight1() {
		but1 = new JButton("Available Doctors");
		but2 = new JButton("Available Patients");
		
		window win = new window();
		
		but1.setBounds(190, 370, 200, 30);
		but1.setFocusable(false);
		
		but2.setBounds(410, 370, 200, 30);
		but2.setFocusable(false);

		but1.setBorder(null);
		but2.setBorder(null);
		
		win.frame.setLayout(null);
		win.frame.add(but1);
		win.frame.add(but2);
	}
}

class subsight2 {
	JButton but1; 
	JButton but2; 
	
	subsight2() {
		but1 = new JButton("Add Doctors");
		but2 = new JButton("Add Patients");
		
		window win = new window();
		
		but1.setBounds(190, 370, 200, 30);
		but1.setFocusable(false);
		
		but2.setBounds(410, 370, 200, 30);
		but2.setFocusable(false);
		
		but1.setBorder(null);
		but2.setBorder(null);

		win.frame.setLayout(null);
		win.frame.add(but1);
		win.frame.add(but2);
	}
}

class login{
	JTextField uName = new JTextField();
	JTextField pass = new JTextField();
	JButton but = new JButton("Submit");
	login() {
		window win = new window();
		JLabel head = new JLabel("Admin Login");
//		head.setForeground(new Color(0x0CBABA));
		head.setFont(new Font("Sans-Serif", Font.BOLD, 48));
		head.setBounds(250, 0, 300, 300);
		
		JLabel name = new JLabel("User Name");
		name.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
//		name.setForeground(new Color(0x0CBABA));
		JPanel a1 = new JPanel();
		name.setBounds(0, 0, 90, 30);
		a1.setBounds(250, 240, 300, 30);
		a1.setLayout(null);
		uName.setBounds(100, 0, 200, 30);
		uName.setBorder(null);
		a1.add(name);
		a1.add(uName);
		
		JLabel pas = new JLabel("Password");
		pas.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		JPanel a2 = new JPanel();
		pas.setBounds(0, 0, 90, 30);
//		pas.setForeground(new Color(0x0CBABA));
		a2.setBounds(250, 280, 300, 30);
		a2.setLayout(null);
		pass.setBounds(100, 0, 200, 30);
		pass.setBorder(null);
		a2.add(pas);
		a2.add(pass);
		
		but.setBounds(250, 340, 200, 30);
		but.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		but.setFocusable(false);
		but.setBorder(null);
		win.frame.add(head);
		win.frame.add(a1);
		win.frame.add(a2);
		win.frame.add(but);
		win.frame.setLayout(null);
		win.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
