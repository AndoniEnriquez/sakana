package Panel;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

@SuppressWarnings("serial")
public class PanelExample extends JInternalFrame{

	int xOffset;
	int yOffset;
	
	public PanelExample(int tamX, int tamY){
		
		super("",
				false, 
				false, 
				false, 
				false);
		
		this.setSize(tamX, tamY); 
		
		if (System.getProperty("os.name").startsWith("Mac OS")) {
			this.putClientProperty("JInternalFrame.isPalette", true);
			
		} else {
			((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		}
		
		this.xOffset = 0;
		this.yOffset = 0;
		this.setLocation(xOffset, yOffset);
	}
	
}
