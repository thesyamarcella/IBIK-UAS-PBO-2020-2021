package com.ibik.library.app.login;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.ibik.library.app.connection.ConnectionDB;
import com.ibik.library.app.dashboard.Profile;
import com.ibik.library.app.model.Users;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginController {

	private JFrame frameMain;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private static Users user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginController window = new LoginController();
					window.frameMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginController() {
		//user = new Users();
		//call the ConnectionDB class using error handling : try_catch
		ConnectionDB conn = new ConnectionDB(); //init object class ConnectionDB
		//error handling for if connection is connected will show initialize() function
		try { 
			conn.connect();
			initialize();
		
		//error handling for if connection is connected will show Window Dialog, the connection is failed
		} catch (SQLException e) {			
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
		//end call
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMain = new JFrame();
		frameMain.setResizable(false);
		frameMain.getContentPane().setForeground(SystemColor.textHighlight);
		frameMain.setTitle("Login - Library IBIK");		
		frameMain.setSize(300,450);//set size frame by width and height
		
		//scripting for having position in a center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frameMain.setLocation( dim.width/2 - frameMain.getSize().width/2, 
							   dim.height/2 - frameMain.getSize().height/2 );
		//end statement
		
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.getContentPane().setLayout(null);

		JLabel labelIcon = new JLabel("");
		labelIcon.setIcon(new ImageIcon(
				"C:\\Users\\LENOVO\\Documents\\UAS THESYA\\Pemrograman-Object-Oriented\\src\\main\\java\\UAS\\soal\\tujuh\\sampai\\sepuluh\\app-library\\src\\main\\java\\com\\ibik\\library\\images\\logo-ibik-web.png"));
		labelIcon.setBounds(85, 0, 109, 150);
		frameMain.getContentPane().add(labelIcon);

		JLabel labelTitle = new JLabel("Sistem Informasi Perpustakaan");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setForeground(Color.GRAY);
		labelTitle.setFont(new Font("Arial Black", Font.BOLD, 15));
		labelTitle.setBounds(0, 137, 284, 28);
		frameMain.getContentPane().add(labelTitle);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Login to your account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 200, 264, 159);
		frameMain.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel labelUsername = new JLabel("Username");
		labelUsername.setBounds(10, 35, 70, 14);
		panel.add(labelUsername);
		
		
		textUsername = new JTextField();
		textUsername.setText("NIK");
		textUsername.setToolTipText("Enter your NIK");
		textUsername.setBounds(90, 32, 164, 20);
		textUsername.setColumns(10);
		textUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textUsername.getText().equals("NIK")) {
					textUsername.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textUsername.getText().isEmpty()) {
					textUsername.setText("NIK");
				}
			}
		});		
		panel.add(textUsername);
		

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(10, 74, 70, 14);
		panel.add(labelPassword);

		textPassword = new JPasswordField();
		textPassword.setBounds(90, 71, 164, 20);
		panel.add(textPassword);
		
		//action for btnLogin if having click event
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(139, 114, 115, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = String.valueOf(textPassword.getPassword());
				//sending variabel username and password to function ValidationForm() and save it in to variabel result
				CallbackResultSet callback = new LoginController().ValidationForm(username, password);				
				if(callback.getResponce()) {
					frameMain.dispose();
					new Profile(user).setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, callback.getLabel(),"Error",JOptionPane.ERROR_MESSAGE);
				}				
			}
		});		
		panel.add(btnLogin);
		//end action for btnLogin if having click event
		
		//action for btnReset if having click event
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
			}
		});
		btnReset.setBounds(10, 114, 115, 23);
		panel.add(btnReset);
		//action for btnReset if having click event

		JLabel labelCopyright = new JLabel("(c) 2021 - PBO");
		labelCopyright.setBounds(10, 386, 264, 14);
		frameMain.getContentPane().add(labelCopyright);
	}
	public class CallbackResultSet extends LoginController {
		private boolean Responce;
		private String Label;
		public boolean getResponce() {
			return Responce;
		}
		public void setResponce(boolean responce) {
			Responce = responce;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
			
			
		}
	}
	
	public CallbackResultSet ValidationForm(String username, String password) { //sending parameter username & password
		CallbackResultSet c = new CallbackResultSet();
		//condition for if username or password is empty
		if (username.isEmpty() || password.isEmpty()) {
			c.setResponce(false);
			c.setLabel("Fill both the username and password fields");			
		} 
		
		//condition for if username has length more than 10
		else if(username.length() > 10) {
			c.setResponce(false);
			c.setLabel("Keyword must not exceed 10 characters");		
		}
		
		//condition for if password has length more than 6
		else if(password.length() > 6) {
			c.setResponce(false);
			c.setLabel("Keyword must not exceed 6 characters");		
		}	
		
		//condition for if username or password has alphabet value => only number can be pass it	
		else if( username.matches("[a-z]*") || password.matches("[a-z]*") ) {
			c.setResponce(false);
			c.setLabel("This entry can only contain numbers");			 
		}
		
		//condition if username and password is not empty
		else {
		//sending username and password to DAO
			//saving to class Login
			Users users = new Users();
			Integer NIK = Integer.parseInt(username);
			users.setNIK(NIK);
			users.setPassword(password);
			//end saving to class Login
			//sending class Login to DAO
			UsersDao usersDao = new UsersDao();			
			try {
				user = usersDao.checkLogin(users);
				System.out.println("NIK "+user.getNIK());
				System.out.println("Fullname "+user.getFullname());
				c.setResponce(true);
				c.setLabel("Wellcome to this application");
			} catch (Exception e) {
				e.printStackTrace();
				c.setResponce(false);
				c.setLabel("You have entered an invalid username or password.");
			}
		}
		
		return c;
	}
}

