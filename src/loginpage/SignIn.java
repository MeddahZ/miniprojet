package loginpage;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class SignIn extends JFrame {

	JFrame frame;
	private JTextField EmailField;
	private JPasswordField PassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(68, 68, 69, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(68, 135, 75, 25);
		frame.getContentPane().add(lblPassword);
		
		EmailField = new JTextField();
		EmailField.setBounds(163, 72, 128, 21);
		frame.getContentPane().add(EmailField);
		EmailField.setColumns(10);
		
		JLabel DefaultEmail = new JLabel("@esi-sba.dz");
		DefaultEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		DefaultEmail.setBounds(293, 73, 69, 18);
		frame.getContentPane().add(DefaultEmail);
		
		JButton BtnSignIn = new JButton("Sign in");
		BtnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false","root", "");
					String email = EmailField.getText();
					String password = new String(PassField.getPassword());
					email += "@esi-sba.dz";
					
					Statement stm = con.createStatement();
					
					String sql = "select * from login where Email= '"+email+"' and Password='"+password+"' ";
					ResultSet rs = stm.executeQuery(sql);
					
					if(rs.next()) {
						frame.dispose();
						Home hpage = new Home();
						hpage.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null,"email or password incorrect!");
						EmailField.setText("");
						PassField.setText("");
						
					}
					
					con.close(); 
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		BtnSignIn.setBounds(169, 197, 99, 34);
		frame.getContentPane().add(BtnSignIn);
		
		PassField = new JPasswordField();
		PassField.setBounds(163, 135, 128, 20);
		frame.getContentPane().add(PassField);
	}
}
