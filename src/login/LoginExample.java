package login;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

@SuppressWarnings("serial")
public class LoginExample extends JInternalFrame {

	int xOffset;
	int yOffset;

	public LoginExample(int tamX, int tamY) {

		super("", false, false, false, false);

		setSize(tamX, tamY);

		if (System.getProperty("os.name").startsWith("Mac OS")) {

			this.putClientProperty("JInternalFrame.isPalette", true);
			this.setBorder(null);

		} else {

			((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		}

		this.xOffset = 0;
		this.yOffset = 0;
		setLocation(xOffset, yOffset);

	}
}
