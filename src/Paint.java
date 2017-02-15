import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class Paint extends JPanel {

	static Paint window;
	private JFrame frame;
	private final ButtonGroup buttonColorGroup = new ButtonGroup();
	private boolean inDrag = false;
	private Shape shape = new Shape();
	private Person person = new Person();
	private ShapeEnManager sem = new ShapeEnManager();
	private boolean start = true;

	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics2D g2d = (Graphics2D) g;
	// g2d.setColor(Color.BLUE);
	// g2d.fillRect(100, 100, 100, 100);
	// }

	/**
	 * Launch the application.
	 */
	public static void runPaint(Person person) {
		System.out.println(person.getUser());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Paint(person);
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
	public Paint(Person person) {
		this.person = person;
		shape.setUserID(person.getUserID());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"D:\\Downloads\\Download with browsers\\Wallpaper-427.jpg"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 665, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// frame.getContentPane().setLayout(null);

		JButton btnLineOption = new JButton("\u062E\u0637");
		btnLineOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setTypeShape("line");
			}
		});
		frame.getContentPane().setLayout(null);
		btnLineOption.setBounds(525, 50, 100, 25);
		frame.getContentPane().add(btnLineOption);

		JButton btnCircleOption = new JButton("\u062F\u0627\u06CC\u0631\u0647");
		btnCircleOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setTypeShape("circle");
			}
		});
		btnCircleOption.setBounds(525, 100, 100, 25);
		frame.getContentPane().add(btnCircleOption);

		JButton btnRectangleOption = new JButton(
				"\u0645\u0633\u062A\u0637\u06CC\u0644");
		btnRectangleOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setTypeShape("rectangle");
			}
		});
		btnRectangleOption.setBounds(525, 150, 100, 25);
		frame.getContentPane().add(btnRectangleOption);

		JButton btnExit = new JButton("\u062E\u0631\u0648\u062C");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				Login.runLogin();
				window.frame.dispose();
			}
		});
		btnExit.setBounds(525, 450, 100, 25);
		frame.getContentPane().add(btnExit);

		JLabel labelColor = new JLabel(
				"\u0627\u0646\u062A\u062E\u0627\u0628 \u0631\u0646\u06AF:");
		labelColor.setHorizontalAlignment(SwingConstants.RIGHT);
		labelColor.setBounds(525, 200, 100, 25);
		frame.getContentPane().add(labelColor);

		JRadioButton rdbtnBlackColor = new JRadioButton(
				"\u0645\u0634\u06A9\u06CC");
		rdbtnBlackColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setColor("black");
			}
		});
		buttonColorGroup.add(rdbtnBlackColor);
		rdbtnBlackColor.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnBlackColor.setBounds(550, 230, 75, 25);
		frame.getContentPane().add(rdbtnBlackColor);

		JRadioButton rdbtnRedColor = new JRadioButton(
				"\u0642\u0631\u0645\u0632");
		rdbtnRedColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setColor("red");
			}
		});
		buttonColorGroup.add(rdbtnRedColor);
		rdbtnRedColor.setForeground(new Color(255, 0, 0));
		rdbtnRedColor.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnRedColor.setBounds(550, 260, 75, 25);
		frame.getContentPane().add(rdbtnRedColor);

		JRadioButton rdbtnGreenColor = new JRadioButton("\u0633\u0628\u0632");
		rdbtnGreenColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setColor("green");
			}
		});
		buttonColorGroup.add(rdbtnGreenColor);
		rdbtnGreenColor.setForeground(new Color(0, 255, 0));
		rdbtnGreenColor.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnGreenColor.setBounds(550, 290, 75, 25);
		frame.getContentPane().add(rdbtnGreenColor);

		JRadioButton rdbtnBlueColor = new JRadioButton("\u0622\u0628\u06CC");
		rdbtnBlueColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape.setColor("blue");
			}
		});
		buttonColorGroup.add(rdbtnBlueColor);
		rdbtnBlueColor.setForeground(new Color(0, 0, 255));
		rdbtnBlueColor.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnBlueColor.setBounds(550, 320, 75, 25);
		frame.getContentPane().add(rdbtnBlueColor);


		Canvas canvas = new Canvas();
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				shape.setStartX(p.x);
				shape.setStartY(p.y);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (inDrag) {
					inDrag = false;
					Graphics g = canvas.getGraphics();
					switch (shape.getColor()) {
					case "black":
						g.setColor(Color.BLACK);
						break;
					case "red":
						g.setColor(Color.RED);
						break;
					case "green":
						g.setColor(Color.GREEN);
						break;
					case "blue":
						g.setColor(Color.BLUE);
						break;
					case " ":
						return;
					}

					switch (shape.getTypeShape()) {
					case "line":
						paintLine(g);
						break;
					case "circle":
						paintCircle(g);
						break;
					case "rectangle":
						paintRectangle(g);
						break;
					case " ":
						break;
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (start) {
					ShapeEnManager sem = new ShapeEnManager();
					Shape[] oldShape = sem.addDataShape(person);
					Graphics g = canvas.getGraphics();

					for (int i = 0; i < oldShape.length; i++) {
						switch (oldShape[i].getColor()) {
						case "black":
							g.setColor(Color.BLACK);
							break;
						case "red":
							g.setColor(Color.RED);
							break;
						case "green":
							g.setColor(Color.GREEN);
							break;
						case "blue":
							g.setColor(Color.BLUE);
							break;
						case " ":
							return;
						}

						oldShape[i].setWidth(oldShape[i].getCurX()
								- oldShape[i].getStartX());
						oldShape[i].setHeight(oldShape[i].getCurY()
								- oldShape[i].getStartY());

						switch (oldShape[i].getTypeShape()) {
						case "line":
							g.drawLine(oldShape[i].getStartX(),
									oldShape[i].getStartY(),
									oldShape[i].getCurX(),
									oldShape[i].getCurY());
							break;
						case "circle":
							g.drawOval(oldShape[i].getStartX(),
									oldShape[i].getStartY(),
									oldShape[i].getWidth(),
									oldShape[i].getHeight());
							break;
						case "rectangle":
							g.drawRect(oldShape[i].getStartX(),
									oldShape[i].getStartY(),
									oldShape[i].getWidth(),
									oldShape[i].getHeight());
							break;
						case " ":
							break;
						}
						start = false;
					}
					
				}
			}
		});

		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = e.getPoint();
				shape.setCurX(p.x);
				shape.setCurY(p.y);
				inDrag = true;
			}
		});

		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 0, 500, 500);
		frame.getContentPane().add(canvas);

		JLabel lblUser = new JLabel("");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setText("user: " + person.getUser());
		lblUser.setBounds(525, 475, 100, 25);
		frame.getContentPane().add(lblUser);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Connect.disconnect();
			}
		});
	}

	public void paintRectangle(Graphics g) {
		setWidthHeight();

		if (shape.getStartX() < 0 || shape.getStartY() < 0
				|| shape.getCurX() > 500 || shape.getCurY() > 500)
			return;

		g.drawRect(shape.getStartX(), shape.getStartY(), shape.getWidth(),
				shape.getHeight());
		sem.addShapeDatebase(shape);
	}

	public void paintCircle(Graphics g) {
		setWidthHeight();

		if (shape.getStartX() < 0 || shape.getStartY() < 0
				|| shape.getCurX() > 500 || shape.getCurY() > 500)
			return;

		g.drawOval(shape.getStartX(), shape.getStartY(), shape.getWidth(),
				shape.getHeight());
		sem.addShapeDatebase(shape);
	}

	public void paintLine(Graphics g) {
		if (shape.getStartX() < 0 || shape.getStartY() < 0
				|| shape.getCurX() > 500 || shape.getCurY() > 500)
			return;

		g.drawLine(shape.getStartX(), shape.getStartY(), shape.getCurX(),
				shape.getCurY());
		sem.addShapeDatebase(shape);
	}

	public void setWidthHeight() {
		if (shape.getCurX() < shape.getStartX()) {
			int tempX = shape.getStartX();
			shape.setStartX(shape.getCurX());
			shape.setCurX(tempX);
		}

		if (shape.getCurY() < shape.getStartY()) {
			int tempY = shape.getStartY();
			shape.setStartY(shape.getCurY());
			shape.setCurY(tempY);
		}

		shape.setWidth(shape.getCurX() - shape.getStartX());
		shape.setHeight(shape.getCurY() - shape.getStartY());
	}

}
