package com.ibik.library.app.dashboard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.ibik.library.app.login.UsersDao;
import com.ibik.library.app.model.Users;

import javax.swing.JTextArea;
import javax.swing.JPasswordField;

public class Profile extends JFrame {

	private JPanel contentPane;
	private JTextField textNIK;
	private JTextField textFullname;
	private JTextField textPlaceBirth;
	private JTextField textBirthdate;
	private JTextField textGender;
	private JTextField textEmail;
	private JPasswordField textPassword;
	private Users user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param user 
	 * @param user 
	 */
	public Profile(Users user) {
		this.user = user;
		initialize();
	}
	
	private void initialize() {
		
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitle = new JLabel("Wellcome to this application");
		labelTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(10, 33, 364, 29);
		contentPane.add(labelTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Your profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 86, 360, 348);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel labelNIK = new JLabel("NIK");
		labelNIK.setBounds(22, 35, 81, 14);
		panel.add(labelNIK);
		
		textNIK = new JTextField();
		textNIK.setBounds(22, 60, 138, 20);
		textNIK.setColumns(10);
		textNIK.setText(user.getNIK().toString());
		panel.add(textNIK);
		
		JLabel labelFullname = new JLabel("Fullname");
		labelFullname.setBounds(170, 35, 81, 14);
		panel.add(labelFullname);
		
		textFullname = new JTextField();
		textFullname.setBounds(170, 60, 169, 20);
		textFullname.setColumns(10);
		textFullname.setText(user.getFullname());
		panel.add(textFullname);
		
		JLabel labelPlacebirth = new JLabel("Placebirth");
		labelPlacebirth.setBounds(22, 102, 81, 14);
		panel.add(labelPlacebirth);
		
		textPlaceBirth = new JTextField();
		textPlaceBirth.setBounds(22, 127, 98, 20);
		textPlaceBirth.setColumns(10);
		textPlaceBirth.setText(user.getPlaceBirth());
		panel.add(textPlaceBirth);
				
		JLabel labelBirthdate = new JLabel("Birthdate");
		labelBirthdate.setBounds(130, 102, 81, 14);
		panel.add(labelBirthdate);
		
		textBirthdate = new JTextField();
		textBirthdate.setBounds(130, 127, 121, 20);
		textBirthdate.setColumns(10);
		textBirthdate.setText(user.getBirthdate());
		panel.add(textBirthdate);
		
		JLabel labelGender = new JLabel("Gender");
		labelGender.setBounds(266, 102, 58, 14);
		panel.add(labelGender);
		
		textGender = new JTextField();
		textGender.setBounds(266, 127, 73, 20);
		textGender.setColumns(10);
		textGender.setText( (user.getGender().equals("m")) ? "Male":"Female" );
		panel.add(textGender);
		
		JLabel labelAddress = new JLabel("Address");
		labelAddress.setBounds(22, 171, 98, 14);
		panel.add(labelAddress);
		
		JTextArea textAddress = new JTextArea();
		textAddress.setBounds(22, 196, 315, 52);
		textAddress.setText(user.getAddress());
		panel.add(textAddress);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setBounds(22, 272, 81, 14);
		panel.add(labelEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(22, 297, 138, 20);
		textEmail.setColumns(10);
		textEmail.setText(user.getEmail());
		panel.add(textEmail);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(170, 272, 81, 14);
		panel.add(labelPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(170, 297, 169, 20);
		textPassword.setText(user.getPassword());
		panel.add(textPassword);
	}
}
