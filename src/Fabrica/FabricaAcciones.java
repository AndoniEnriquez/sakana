package Fabrica;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import Login.LoginDerecha;
import Login.LoginIzquierda;
import Panel.PanelAjustes;
import Panel.PanelControlPecera;
import Panel.PanelListaPecera;
import PanelControl.PanelInformacion;
import VarTypes.Pecera;
import sakana.MenuPrincipal;

public class FabricaAcciones {

	int tamX, tamY;
	public int getTamY() {
		return tamY;
	}

	public void setTamY(int tamY) {
		this.tamY = tamY;
	}

	public static PanelControlPecera getPanelControlPecera() {
		return panelControlPecera;
	}

	public static void setPanelControlPecera(PanelControlPecera panelControlPecera) {
		FabricaAcciones.panelControlPecera = panelControlPecera;
	}

	public static PanelListaPecera panelListaPecera;
	public static PanelControlPecera panelControlPecera;
	public static PanelAjustes panelAjustes;
	public static PanelInformacion panelInformacion;
	public static MenuPrincipal menuPrincipal;
	
	public static MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}

	public static void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		FabricaAcciones.menuPrincipal = menuPrincipal;
	}

	public static PanelInformacion getPanelInformacion() {
		return panelInformacion;
	}

	public static void setPanelInformacion(PanelInformacion panelInformacion) {
		FabricaAcciones.panelInformacion = panelInformacion;
	}

	JList<Pecera> listaPecera;
	public JList<Pecera> getListaPecera() {
		return listaPecera;
	}

	public void setListaPecera(JList<Pecera> listaPecera) {
		this.listaPecera = listaPecera;
	}

	DefaultListModel<Pecera> modeloPecera;
	
	
	public DefaultListModel<Pecera> getModeloPecera() {
		return modeloPecera;
	}

	public void setModeloPecera(DefaultListModel<Pecera> modeloPecera) {
		this.modeloPecera = modeloPecera;
	}

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
		
		panelListaPecera = new PanelListaPecera(this);
		panelListaPecera.setVisible(true);
		return panelListaPecera;
	}
		
	public PanelControlPecera accionamientoControlPecera(){
		
		panelControlPecera = new PanelControlPecera(this);
		panelControlPecera.setVisible(true);
		return panelControlPecera;
	}
	

}
