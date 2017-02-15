import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog {

	static Login dialog;
	private JTextField textUserField;
	private JPasswordField passwordField;
	private Person person = new Person();
	private PersonEnManager pen = new PersonEnManager();

	/**
	 * Launch the application.
	 */
	public static void runLogin() {
		try {
			dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"D:\\Downloads\\Download with browsers\\Untitled-1.png"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);

		JLabel lblStatus = new JLabel("");
		lblStatus.setText(Connect.status);
		lblStatus.setBounds(10, 200, 375, 15);
		getContentPane().add(lblStatus);

		textUserField = new JTextField();
		textUserField.setBounds(150, 50, 100, 25);
		getContentPane().add(textUserField);
		textUserField.setColumns(10);

		JLabel lblUser = new JLabel("Username:");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setLabelFor(textUserField);
		lblUser.setBounds(50, 50, 90, 25);
		getContentPane().add(lblUser);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(150, 100, 100, 25);
		getContentPane().add(passwordField);

		JLabel lblPass = new JLabel("Password:");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setBounds(50, 100, 90, 25);
		getContentPane().add(lblPass);

		JButton btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					loginKeyAction();
				}
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginKeyAction();
			}
		});
		btnLogin.setBounds(150, 150, 100, 25);
		getContentPane().add(btnLogin);

		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				singUpKeyAction();
			}
		});
		btnSignIn.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					singUpKeyAction();
				}
			}
		});
		btnSignIn.setBounds(315, 197, 75, 20);
		getContentPane().add(btnSignIn);
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				Connect.disconnect();
			}
		});
	}

	
	private void loginKeyAction() {
		person.setUser(textUserField.getText());
		person.setPass(passwordField.getText());
		pen.setPerson(person);
		person = pen.getPerson();
		if (pen.isResult()) {
			Paint.runPaint(person);
			dialog.dispose();
			person = new Person(0);
		} else {
			JOptionPane.showMessageDialog(null, pen.getMassage());
		}
	}
	
	private void singUpKeyAction(){
		String code;
		code = JOptionPane.showInputDialog(
				"Please enter invited code.", null);
		if (code.equals("1024")) {
			SignUp.runSignUp();
		} else {
			JOptionPane.showMessageDialog(null,
					"Your invited code is wrong!!!");
		}
	}
}
