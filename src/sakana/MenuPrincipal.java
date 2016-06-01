package sakana;


import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import conexionMicro.SincronizadorPeceras;
import fabrica.FabricaAcciones;
import panel.PanelAjustes;


@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame{

	public static JSplitPane panelPrincipal;
	public static JDesktopPane desktopIzquierda, desktopDerecha;
	public static PanelAjustes panelAjustes;
	
	static FabricaAcciones fabrica;
	
	public MenuPrincipal(){
		
		setTitle("Piscis Control");
		setSize(1220, 719);
		setLocation(100, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
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
		
		@SuppressWarnings("unused")
		SincronizadorPeceras sincronizador = new SincronizadorPeceras();
    	MenuPrincipal menuPrincipal = new MenuPrincipal();
    	fabrica.setMenuPrincipal(menuPrincipal);
    	
    	
		
	}
}