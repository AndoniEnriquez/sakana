package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Dialogo.DialogoAddDueno;
import Dialogo.DialogoAddPecera;
import Dialogo.DialogoConfiguracion;
import Fabrica.*;
import Login.FormLogin;
import sakana.MenuPrincipal;


public class PanelAjustes implements ActionListener{

	JPanel panel;
	public JButton bBloqueo, bInicio, bSalir, bAdd;
	public JLabel labelHora, labelUser;
	
	FabricaAcciones fabrica;
	
	Boolean bloqueado = true;
	
	
	@SuppressWarnings("static-access")
	public JPanel crearPanelAjustes(FabricaAcciones fabrica) {

		panel = new JPanel();
		
		this.fabrica = fabrica;
		fabrica.setPanelAjustes(this);
		labelHora = new JLabel();

		panel.setBackground(Color.lightGray); 

		Runnable runnable = new ControlHora();
		Thread threadHora = new Thread(runnable);
		threadHora.start();

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		labelUser = new JLabel();
		labelUser.setFont(new Font("Arial", Font.BOLD, 15));
		labelUser.setForeground(Color.WHITE);
		
		bBloqueo = new JButton(new ImageIcon("Iconos/ajustes/lock.png"));
		bBloqueo.setActionCommand("Bloqueo");
		bBloqueo.addActionListener(this);
		bBloqueo.setEnabled(false);
		bBloqueo.add(Box.createRigidArea(new Dimension(35,35)));
		bBloqueo.setBorder(BorderFactory.createEmptyBorder(10,2,2,2));
		
		bInicio = new JButton(new ImageIcon("Iconos/ajustes/login.png"));
		bInicio.setActionCommand("Inicio");
		bInicio.addActionListener(this);
		bInicio.add(Box.createRigidArea(new Dimension(35,35)));
		bInicio.setBorder(BorderFactory.createEmptyBorder(10,2,2,2));
		
		bSalir = new JButton(new ImageIcon("Iconos/ajustes/logout.png"));
		bSalir.setActionCommand("Salir");
		bSalir.addActionListener(this);
		bSalir.setEnabled(false);
		bSalir.add(Box.createRigidArea(new Dimension(35,35)));
		bSalir.setBorder(BorderFactory.createEmptyBorder(10,2,2,2));
		
		bAdd = new JButton(new ImageIcon("Iconos/ajustes/plus.png"));
		bAdd.setActionCommand("Add");
		bAdd.addActionListener(this);
		bAdd.setEnabled(false);
		bAdd.add(Box.createRigidArea(new Dimension(35,35)));
		bAdd.setBorder(BorderFactory.createEmptyBorder(10,2,2,2));
		
		labelHora.setFont(new Font("Arial", Font.BOLD, 15));
		labelHora.setForeground(Color.WHITE);

		panel.add(labelHora);
		panel.add(Box.createHorizontalGlue());
		
		panel.add(labelUser);
		panel.add(Box.createRigidArea(new Dimension(25,25)));
		panel.add(bInicio);
		panel.add(Box.createRigidArea(new Dimension(25,25)));
		panel.add(bAdd);
		panel.add(Box.createRigidArea(new Dimension(25,25)));
		panel.add(bSalir);
		panel.add(Box.createRigidArea(new Dimension(25,25)));
		panel.add(bBloqueo);
		panel.add(Box.createRigidArea(new Dimension(25,25)));

		return panel;

	}
	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()){
		
			case "Bloqueo":
			
				this.controlBloqueo();
		
				break;
				
			case "Inicio":
				
				new FormLogin(fabrica);
		
				break;
				
			case "Add":
				
				//new DialogoAddDueno(fabrica.getMenuPrincipal(), false, fabrica);
				new DialogoConfiguracion(fabrica.getMenuPrincipal(),fabrica);
				
				break;
				
			case "Salir":
				
				bInicio.setEnabled(true);
				bSalir.setEnabled(false);
				bAdd.setEnabled(false);
				bBloqueo.setEnabled(false);
				fabrica.getPanelAjustes().labelUser.setText("");
		
				break;
				
		}
	}
	
	public void controlBloqueo(){
		
		if(bloqueado == false){

			this.bloquearCandado();
			bBloqueo.setIcon(new ImageIcon("Iconos/ajustes/lock.png"));
			bAdd.setEnabled(true);
			bSalir.setEnabled(true);
			bloqueado = true;
			
		}else {
			
			this.desbloquearCandado();
			bBloqueo.setIcon(new ImageIcon("Iconos/ajustes/unlock.png"));
			bSalir.setEnabled(false);
			bAdd.setEnabled(false);
			bloqueado = false;
			
		}
		
	}
	
	public void bloquearCandado(){
		
		MenuPrincipal.desktopIzquierda.removeAll();
		MenuPrincipal.desktopDerecha.removeAll();
		
		MenuPrincipal.desktopIzquierda.add(fabrica.accionamientoLoginIzquierda());
		MenuPrincipal.desktopDerecha.add(fabrica.accionamientoLoginDerecha());
	}
	
	public void desbloquearCandado(){
		
		MenuPrincipal.desktopIzquierda.removeAll();
		MenuPrincipal.desktopDerecha.removeAll();
		
		MenuPrincipal.desktopIzquierda.add(fabrica.accionamientoListaPecera());
		MenuPrincipal.desktopDerecha.add(fabrica.accionamientoControlPecera());
	}
	
	public class ControlHora  implements Runnable{

		DateFormat formatoHora = new SimpleDateFormat("     HH:mm:ss");

		@Override
		public void run() {
			while(true){

				labelHora.setText(formatoHora.format(new Date()));

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}	
	}

}
