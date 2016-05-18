package Fabrica;

import Login.*;

public class FabricaAcciones {

	int tamX, tamY;
	
	
	public LoginIzquierda accionamientoLoginIzquierda(){
		
		this.tamX = 610;
		this.tamY = 650;
		
		LoginIzquierda ventana = new LoginIzquierda(tamX, tamY);
		ventana.setVisible(true);
		return ventana;
		
	}
	
	public LoginDerecha accionamientoLoginDerecha(){
		
		this.tamX = 610;
		this.tamY = 650;
		
		LoginDerecha ventana = new LoginDerecha(tamX, tamY);
		ventana.setVisible(true);
		return ventana;
		
	}
		
}
