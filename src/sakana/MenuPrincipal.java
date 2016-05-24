package sakana;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Fabrica.FabricaAcciones;
import Panel.PanelAjustes;


@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame implements WindowListener{

	public static JSplitPane panelPrincipal;
	public static JDesktopPane desktopIzquierda, desktopDerecha;
	public static PanelAjustes panelAjustes;
	
	static FabricaAcciones fabrica;
	
	public MenuPrincipal(){
		
		setTitle("Piscis Control");
		addWindowListener(this);
		setSize(1220, 719);
		setLocation(100, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Eztakit zeozer in behar dan isteko, ezpabere windowListener SOBRA!!!
		
		desktopIzquierda = new JDesktopPane();
		desktopDerecha = new JDesktopPane();
		panelAjustes = new PanelAjustes();
		
		fabrica = new FabricaAcciones();
		desktopIzquierda.add(fabrica.accionamientoLoginIzquierda());
		desktopDerecha.add(fabrica.accionamientoLoginDerecha());
		
		this.getContentPane().add(crearPanelPrincipal(), BorderLayout.CENTER);
		this.getContentPane().add(panelAjustes.crearPanelAjustes(fabrica), BorderLayout.NORTH);
	}
	
	private Component crearPanelPrincipal() {

		panelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		panelPrincipal.setDividerLocation(400);
		panelPrincipal.setEnabled(false);
		panelPrincipal.setDividerSize(0);
		panelPrincipal.add(desktopIzquierda);
		panelPrincipal.add(desktopDerecha);
		
		return panelPrincipal;
	}
	
	

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
    	MenuPrincipal menuPrincipal = new MenuPrincipal();	
    	fabrica.setMenuPrincipal(menuPrincipal);
		
	}
	
	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}