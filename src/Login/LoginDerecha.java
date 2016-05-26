package Login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Panel.MiPanel;

@SuppressWarnings("serial")
public class LoginDerecha extends LoginExample {

	Image imageLogin;
	static Toolkit toolkit = Toolkit.getDefaultToolkit();

	public LoginDerecha(int tamX, int tamY) {
		
		super(tamX, tamY);
		this.cargarImagenes();
		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {

		JPanel panel = new JPanel(new GridLayout(1, 1));
		MiPanel panelLogin = new MiPanel(imageLogin);
		panel.add(panelLogin);

		return panel;
	}

	public void cargarImagenes() {
		
		imageLogin = toolkit.createImage("Imagenes/oceanoDerecha.jpg");

	}

}
