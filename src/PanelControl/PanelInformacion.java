package PanelControl;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Panel.*;
import VarTypes.Pecera;

@SuppressWarnings("serial")
public class PanelInformacion extends PanelExample implements ActionListener{

	static int tamX = 0;
	static int tamY = 0;
	
	JLabel txNom,txIP,txCapacidad, txPH, txTemp;
	JButton bEdit, bComida;
	
	
	JPanel panel;
	MiPanel miPanel;
	
	Pecera pecera;
	
	public PanelInformacion(Pecera pecera) {
		super(tamX, tamY);
		this.setLocation(0, -30);
		this.pecera = pecera;
		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {
		
		panel = new JPanel();
		
		this.setBorder(null);
		panel.add(crearPanel());
		
		return panel;
	}


	

	private Container crearPanel() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.setOpaque(false);
			panel.add(crearPanelCampos (), BorderLayout.CENTER);
			panel.add(crearPanelBotones(), BorderLayout.SOUTH);
			return panel;
		}

		private Component crearPanelBotones() {
			JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER,30,0));
			panel.setOpaque(false);
			bEdit = new JButton ("Editar");
			bEdit.setActionCommand("Editar");
			bEdit.addActionListener(this);
			bComida = new JButton ("Comida");
			bComida.setActionCommand("Comida");
			bComida.addActionListener(this);
			
			panel.add(bEdit);
			panel.add(bComida);
			return panel;
		}

		private Component crearPanelCampos() {
			JPanel panel = new JPanel (new GridLayout(5,1,0,20));
			
			panel.setOpaque(false);
			panel.add(txNom = crearCampo("Nombre"));
			panel.add(txIP = crearCampo("IP"));
			panel.add(txCapacidad = crearCampo("Capacidad"));
			panel.add(txPH = crearCampo("PH Actual"));
			panel.add(txTemp = crearCampo("Temperatura Actual"));
			
			return panel;
		}

		private JLabel crearCampo(String titulo) {
			JLabel campo = new JLabel();
			campo.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.PINK),titulo));
			
			return campo;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/*boolean añadir;
			switch (e.getActionCommand()){
			case "OK" : if (camposIncompletos()){
							JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
									"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
						}else{
							try{
							//Persona p = new Persona(txNomUsuario.getText(), txNombre.getText(), txPassword.getText(), Integer.parseInt(txDNI.getText()), seleccionarTipo());
							//a�adir = DAOPersonas.addPersona(p);
							//DAOPersonas.eliminarPersona(p.getUserName());
							if(a�adir){
							JOptionPane.showMessageDialog(this, "Usuario a�adido",
									"Accion realizada", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							}else{
								JOptionPane.showMessageDialog(this, "Usuario existente. Cambia el nombre de usuario y/o DNI",
										"Imposible to Add Person", JOptionPane.INFORMATION_MESSAGE);
							}
							}catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(this, "El DNI solo puede tener numeros. SIN LETRA",
										"Format Error", JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						break;
						
			case "Cancelar":
						this.dispose();
			}
			*/
		}



		private boolean camposIncompletos() {
			
			return false;//txNombre.getText().length()==0 ||txUbicaci�n.getText().length()==0 || txDescripci�n.getText().length()==0;
		}
}
