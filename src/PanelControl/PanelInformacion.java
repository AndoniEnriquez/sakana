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

import Dialogo.DialogoAddPecera;
import Fabrica.FabricaAcciones;
import Panel.*;
import VarTypes.Pecera;
import sakana.MenuPrincipal;

@SuppressWarnings("serial")
public class PanelInformacion extends PanelExample implements ActionListener{

	static int tamX = 0;
	static int tamY = 0;
	
	JLabel txNom,txIP,txCapacidad, txPH, txTemp;
	JButton bEdit, bAdd;
	
	
	JPanel panel;
	MiPanel miPanel;
	
	Pecera pecera;
	FabricaAcciones fabrica;
	
	public PanelInformacion(FabricaAcciones fabrica) {
		super(tamX, tamY);
		this.setLocation(0, -30);
		this.fabrica = fabrica;
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
			bAdd = new JButton ("Add");
			bAdd.setActionCommand("Add");
			bAdd.addActionListener(this);
			
			panel.add(bAdd);
			panel.add(bEdit);
			
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
		
		public void setText(Pecera pecera){
			txNom.setText(pecera.getNombre());
			txIP.setText(pecera.getIP());
			txCapacidad.setText(String.valueOf(pecera.getCapacidad()));
			txPH.setText(String.valueOf(pecera.getPH()));
			txTemp.setText(String.valueOf(pecera.getTemp()));
		}

		private JLabel crearCampo(String titulo) {
			JLabel campo = new JLabel();
			campo.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.PINK),titulo));
			
			return campo;
		}

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()) {
			case "Add":
				
					new DialogoAddPecera(fabrica.getMenuPrincipal(), false, fabrica);
				
				break;

			default:
				break;
			}
			
		}
}
