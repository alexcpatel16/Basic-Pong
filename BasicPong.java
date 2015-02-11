import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.*;

public class BasicPong implements ActionListener {
	private JFrame frame;
	private JButton resetButton;
	private PicturePanel picture;

	public static void main(String[] args) {
		new BasicPong();
	}

	public void setupInputMaps(PicturePanel picture) {

		picture.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"upkey");
		picture.getActionMap().put("upkey", new KeyActionController(picture,"UP"));

		picture.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"downkey");
		picture.getActionMap().put("downkey", new KeyActionController(picture,"DOWN"));

		picture.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"wkey");
		picture.getActionMap().put("wkey", new KeyActionController(picture,"W"));

		picture.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0),"skey");
		picture.getActionMap().put("skey", new KeyActionController(picture,"S"));
	}

	public void actionPerformed(ActionEvent e) {
		picture.reset();
		picture.requestFocus();
	}

	public BasicPong() {
		picture = new PicturePanel();
		resetButton = new JButton("Reset Image");
		setupInputMaps(picture);

		picture.setFocusable(true);

		resetButton.addActionListener(this);
		frame = new JFrame("Custom Drawing Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(picture, BorderLayout.CENTER);
		frame.getContentPane().add(resetButton, BorderLayout.NORTH);

		frame.pack();
		frame.setVisible(true);

		picture.requestFocus();
	}
}
