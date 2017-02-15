import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JDialog {

	static SignUp dialog;
	private JTextField userField;
	private JTextField passField;
	private JTextField conPassField;
	private boolean insert1 = false;
	private boolean insert2 = false;
	private boolean insert3 = false;
	private PersonEnManager pen = new PersonEnManager();

	/**
	 * Launch the application.
	 */
	public static void runSignUp() {
		try {
			dialog = new SignUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignUp() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		userField = new JTextField();
		userField.setBounds(175, 50, 100, 25);
		getContentPane().add(userField);
		userField.setColumns(10);

		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(175, 100, 100, 25);
		getContentPane().add(passField);

		conPassField = new JPasswordField();
		conPassField.setColumns(10);
		conPassField.setBounds(175, 150, 100, 25);
		getContentPane().add(conPassField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(70, 50, 100, 25);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(70, 100, 100, 25);
		getContentPane().add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setBounds(20, 150, 150, 25);
		getContentPane().add(lblConfirmPassword);

		JLabel lblCheckUser = new JLabel("");
		userField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!userField.getText().isEmpty()) {
					boolean b = true;
					for (int i = 0; i < userField.getText().length(); i++) {
						char ch = userField.getText().charAt(i);
						b = Character.isLetterOrDigit(ch);
						if (!b) {
							b = (ch == '-' || ch == '.' || ch == ',' || ch == '_');
							char ch2 = userField.getText().charAt(i - 1);
							if (!b) {
								break;
							} else if (ch2 == '-' || ch2 == '.' || ch2 == ','
									|| ch2 == '_') {
								b = false;
								break;
							}
						}
					}
					if (b) {
						b = !pen.checkUsername(userField.getText());
						if (b) {
							lblCheckUser.setForeground(Color.GREEN);
							lblCheckUser.setText("OK");
							insert1 = true;
						} else {
							lblCheckUser.setForeground(Color.RED);
							lblCheckUser.setText("Wrong!");
							insert1 = false;
						}
					} else {
						lblCheckUser.setForeground(Color.RED);
						lblCheckUser.setText("Wrong!");
						insert1 = false;
					}
				} else {
					lblCheckUser.setText("");
					insert1 = false;
				}
			}
		});
		lblCheckUser.setBounds(285, 50, 100, 25);
		getContentPane().add(lblCheckUser);

		JLabel lblCheckPass = new JLabel("");
		lblCheckPass.setBounds(285, 100, 100, 25);
		getContentPane().add(lblCheckPass);

		JLabel lblCheckConPass = new JLabel("");
		lblCheckConPass.setBounds(285, 150, 100, 25);
		getContentPane().add(lblCheckConPass);

		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!passField.getText().isEmpty()) {
					boolean b = true;
					for (int i = 0; i < passField.getText().length(); i++) {
						char ch = passField.getText().charAt(i);
						b = Character.isLetterOrDigit(ch);
						if (!b) {
							break;
						}
					}
					if (b) {
						if (passField.getText().length() < 7) {
							lblCheckPass.setForeground(Color.RED);
							lblCheckPass.setText("Very short!");
							insert2 = false;
						} else {
							lblCheckPass.setForeground(Color.GREEN);
							lblCheckPass.setText("OK");
							insert2 = true;
						}
					} else {
						lblCheckPass.setForeground(Color.RED);
						lblCheckPass.setText("Wrong!");
						insert2 = false;
					}
				} else {
					lblCheckPass.setText("");
					insert2 = false;
				}

				if (!conPassField.getText().isEmpty()) {
					boolean b = (conPassField.getText().equals(passField
							.getText()));
					if (b) {
						lblCheckConPass.setForeground(Color.GREEN);
						lblCheckConPass.setText("OK");
						insert3 = true;
					} else {
						lblCheckConPass.setForeground(Color.RED);
						lblCheckConPass.setText("Wrong!");
						insert3 = false;
					}
				} else {
					lblCheckConPass.setText("");
					insert3 = false;
				}
			}
		});

		conPassField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!conPassField.getText().isEmpty()) {
					boolean b = (conPassField.getText().equals(passField
							.getText()));
					if (b) {
						lblCheckConPass.setForeground(Color.GREEN);
						lblCheckConPass.setText("OK");
						insert3 = true;
					} else {
						lblCheckConPass.setForeground(Color.RED);
						lblCheckConPass.setText("Wrong!");
						insert3 = false;
					}
				} else {
					lblCheckConPass.setText("");
					insert3 = false;
				}
			}
		});

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					signUpAction();
				}
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUpAction();
			}
		});
		btnSignUp.setBounds(175, 200, 100, 25);
		getContentPane().add(btnSignUp);

	}

	private void signUpAction() {
		boolean insert = insert1 && insert2 && insert3;
		if (insert) {
			pen.addUserDatabase(userField.getText(), passField.getText());
			JOptionPane.showMessageDialog(null, pen.getMassage());
			if (pen.getMassage().equals("Sing Up successfully.")) {
				dialog.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Username or password is incorent!");
		}
	}
}
