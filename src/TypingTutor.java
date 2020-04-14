import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * @author Mohit Kumar
 * @date Aug 9, 2019
 */

public class TypingTutor extends JFrame implements ActionListener {

	JLabel lblTime;
	JLabel lblScore;
	JLabel lblWord;
	JTextField txtWord;
	JButton btnStart;
	JButton btnStop;

	Timer timer;
	int timeRemaining;
	int score;
	boolean isRunning;
	String[] words;

	public TypingTutor(String[] words) {

		this.words = words;

		super.pack();

		GridLayout layout = new GridLayout(3, 2);

		Font font = new Font("Helvetica", 1, 40);

		super.setLayout(layout);

		super.setTitle("Typing Tutor");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		super.setSize(dim.width / 2, dim.height / 2);
		super.setLocationRelativeTo(null);

		lblTime = new JLabel("Time");
		lblTime.setFont(font);
		lblTime.setForeground(new Color(252, 172, 3));

		super.add(lblTime);

		lblScore = new JLabel("Score");
		lblScore.setFont(font);
		lblScore.setForeground(new Color(252, 172, 3));
		super.add(lblScore);

		lblWord = new JLabel();
		lblWord.setFont(font);
		lblWord.setForeground(new Color(252, 172, 3));

		super.add(lblWord);

		txtWord = new JTextField();
		txtWord.setFont(font);
		txtWord.setBorder(null);
		txtWord.setBackground(new Color(220, 220, 220));
		txtWord.setForeground(new Color(252, 172, 3));

		super.add(txtWord);

		btnStart = new JButton("Start");
		btnStart.setFont(font);
		btnStart.setForeground(new Color(252, 172, 3));

		btnStart.addActionListener(this);
		super.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setFont(font);
		btnStop.setForeground(new Color(252, 172, 3));

		btnStop.addActionListener(this);
		super.add(btnStop);

		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setVisible(true);

		setUpGame();
	}

	private void setUpGame() {
		timer = new Timer(1000, this);
		timeRemaining = 15;
		score = 0;
		isRunning = false;
		lblTime.setText("");
		lblScore.setText("");
		lblWord.setText("");
		txtWord.setText("");
		txtWord.setEnabled(false);
		btnStart.setText("Start");
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			handleStart();
		} else if (e.getSource() == btnStop) {
			handleStop();
		} else {
			handleTimer();
		}
	}

	private void handleStart() {
		if (isRunning == false) {
			timer.start();
			txtWord.setEnabled(true);
			btnStart.setText("Pause");
			btnStop.setEnabled(true);
			isRunning = true;
		} else {
			timer.stop();
			txtWord.setEnabled(false);
			btnStart.setText("Start");
			isRunning = false;
		}
	}

	private void handleStop() {

		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Replay ?");
		if (choice == JOptionPane.YES_OPTION) {
			setUpGame();

		} else if (choice == JOptionPane.NO_OPTION) {
			super.dispose();
			System.exit(0);

		} else {
			if (timeRemaining < 0) {
				setUpGame();
			} else {
				timer.start();
			}
		}
	}

	private void handleTimer() {

		timeRemaining--;

		if (lblWord.getText().equals(txtWord.getText()) && lblWord.getText().length() > 0) {
			score++;
		}

		lblScore.setText("Score: " + score);

		if (timeRemaining < 0) {
			handleStop();
			return;
		}

		lblTime.setText("Time: " + timeRemaining);

		txtWord.setText("");
		int idx = (int) (Math.random() * words.length);
		lblWord.setText(words[idx]);
	}

}
