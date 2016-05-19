package Panel;

import java.awt.Color;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Fabrica.FabricaAcciones;
import PanelControl.*;
import VarTypes.Pecera;

@SuppressWarnings("serial")
public class PanelControlPecera extends PanelExample implements ChangeListener{

	static int tamX = 820;
	static int tamY = 670;
	
	public JTabbedPane tab;
	
	PanelInformacion panelInformacion;
	PanelPeces panelPeces;
	
	FabricaAcciones fabrica;
	
	public PanelControlPecera(FabricaAcciones fabrica) {
		super(tamX, tamY);
	
		this.fabrica = fabrica;
		this.xOffset = 0;
		this.yOffset = -16;
		setLocation(xOffset, yOffset);
		
		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {
		
		tab = new JTabbedPane();

		tab.setBackground(Color.GRAY);
		tab.addChangeListener(this);
		
		try{
		panelInformacion = new PanelInformacion();
		fabrica.setPanelInformacion(panelInformacion);
	
		}catch(Exception e){
			panelInformacion = new PanelInformacion();
		}
		panelPeces = new PanelPeces();
		
		tab.addTab("", new ImageIcon("iconos/pestañas/information.png"), panelInformacion);
		tab.addTab("", new ImageIcon("iconos/pestañas/fish.png"), panelPeces);
		
		return tab;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
	
		
	}

}

//PECES INFO HISTORIAL CONFIGURACION DAR DE COMER (CONSOLA)
