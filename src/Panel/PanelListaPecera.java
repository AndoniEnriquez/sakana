package Panel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JPanel;




@SuppressWarnings("serial")
public class PanelListaPecera extends PanelExample implements MouseListener{

	static int tamX = 400;
	static int tamY = 650;
	
	public JPanel panel, trans, aux;
	Image fondoLista;
	Toolkit toolkit;
	
	MiPanel miPanel;
	
	public PanelListaPecera() {
		super(tamX, tamY);
		this.setBorder(null);
		
		toolkit = Toolkit.getDefaultToolkit();
		fondoLista = toolkit.createImage("Imagenes/oceano.jpg");
		
		this.setContentPane(crearPanelVentana());
		
	}
	
	private Container crearPanelVentana() {
		
		panel = new JPanel(new GridLayout(1,1));

		miPanel = new MiPanel(fondoLista);
		
		this.controlPeceras();
		
		return panel;
		
	}

	public void controlPeceras(){

		aux = crearPanelTrans();
		aux.add(Box.createRigidArea(new Dimension(0,115)));
		//aux.add(); AÑADIR LABEL CON ICONO PARA DISTINGUIR PECERAS ? 

		
		
		for(int i = 0; i < 8; i++){
			aux = crearPanelTrans();
			miPanel.add(aux);
			panel.add(miPanel);
		}
	}
	
	public JPanel crearPanelTrans(){

		trans = new JPanel();
		trans.setBackground(new Color(0,0,0,125));
		trans.setPreferredSize(new Dimension(350,75));

		return trans;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
