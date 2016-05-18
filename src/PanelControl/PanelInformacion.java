package PanelControl;


import java.awt.Container;


import javax.swing.JPanel;

import Panel.*;

@SuppressWarnings("serial")
public class PanelInformacion extends PanelExample{

	static int tamX = 0;
	static int tamY = 0;
	
	JPanel panel;
	MiPanel miPanel;
	
	public PanelInformacion() {
		super(tamX, tamY);
		
		this.setLocation(0, -30);

		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {
		
		panel = new JPanel();
		
		this.setBorder(null);
	
		this.controlInformacion();
		
		return panel;
	}

	private void controlInformacion() {

		
		
	}
	
	
	
}
