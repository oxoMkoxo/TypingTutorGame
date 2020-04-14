import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * @author Mohit Kumar
 * @date AUG 9, 2019
 */

public class TTClient extends JFrame implements ActionListener {
	int i = 0;
	JProgressBar prog;

	public TTClient() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setUndecorated(true);
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(dim.width / 2, dim.height / 2);
		setLocationRelativeTo(null);

		ImageIcon img = new ImageIcon(
				"C:\\Users\\MOHIT\\eclipse-workspace\\Typing_Tutor_Project\\src\\typingImage.jpg");
		Image imgFit = img.getImage();
		Image newimg = imgFit.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_AREA_AVERAGING);
		ImageIcon imgScalled = new ImageIcon(newimg);
		JLabel background = new JLabel(imgScalled);
		background.setLayout(new BorderLayout());

		JLabel text = new JLabel("         TYPING TUTOR");
		text.setFont(new Font("Helvetica", 1, 60));
		text.setForeground(new Color(252, 172, 3));
		prog = new JProgressBar();
		prog.setValue(0);
		prog.setForeground(new Color(252, 172, 3));
		prog.setStringPainted(true);

		add(background);
		background.add(text, "North");

		background.add(prog, "South");
		setVisible(true);
		Timer time = new Timer(25, this);
		time.start();

	}

	public static void main(String[] args) {

		new TTClient();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (i == 100) {
			Random random = new Random();
			String[] demo = new String[15];
			for (int i = 0; i < 15; i++) {
				int r = random.nextInt(26)+'a';
				demo[i] = "" + (char)r;
			}
			new TypingTutor(demo);
			dispose();
		}
		i++;
		prog.setValue(i);
	}

}
