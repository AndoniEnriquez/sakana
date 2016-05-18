package Fabrica;

import Login.*;
import Panel.*;

public class FabricaAcciones {

	int tamX, tamY;
	public static PanelListaPecera panelListaPecera;
	public static PanelControlPecera panelControlPecera;
	public static PanelAjustes panelAjustes;
	
	public static PanelAjustes getPanelAjustes() {
		return panelAjustes;
	}

	public static void setPanelAjustes(PanelAjustes panelAjustes) {
		FabricaAcciones.panelAjustes = panelAjustes;
	}

	public LoginIzquierda accionamientoLoginIzquierda(){
		
		this.tamX = 400;
		this.tamY = 650;
		
		LoginIzquierda ventana = new LoginIzquierda(tamX, tamY);
		ventana.setVisible(true);
		return ventana;	
	}
	
	public LoginDerecha accionamientoLoginDerecha(){
		
		this.tamX = 820;
		this.tamY = 650;
		
		LoginDerecha ventana = new LoginDerecha(tamX, tamY);
		ventana.setVisible(true);
		return ventana;
	}
	
	public PanelListaPecera accionamientoListaPecera(){
		
		panelListaPecera = new PanelListaPecera();
		panelListaPecera.setVisible(true);
		return panelListaPecera;
	}
		
	public PanelControlPecera accionamientoControlPecera(){
		
		panelControlPecera = new PanelControlPecera();
		panelControlPecera.setVisible(true);
		return panelControlPecera;
	}
	

}
