package Dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ConexionDB.DAOPez;
import ConexionDB.DAOTipoPez;
import Fabrica.FabricaAcciones;
import Login.Sesion;
import VarTypes.Pez;
import VarTypes.TipoPez;

public class DialogoAddPez extends JDialog implements ActionListener{
	
	final static String  TITULO = "Crear nuevo usuario";
	JComboBox<String> comboGenero, comboTipo;
	JTextField txNomPez;
	ArrayList<TipoPez> tipoPez;
	FabricaAcciones fabrica;
	
	public DialogoAddPez (JFrame frame, boolean modo, FabricaAcciones fabrica){
		super ( frame,TITULO,modo );
		crearVentana();
		this.fabrica = fabrica;
		this.setVisible(true);
	}

	private void crearVentana() {
		this.setLocation(280,200);
		this.setSize(300, 380);
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(crearPanelCampos (), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER,30,0));
		JButton bOk = new JButton ("Add");
		bOk.setActionCommand("OK");
		bOk.addActionListener(this);
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("Cancelar");
		bCancel.addActionListener(this);
		
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}

	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(3,1,0,20));
	
		panel.add(txNomPez = crearCampo("Nombre"));
		
		String genero [];
		genero = new String [3];
		genero[0] = "1.Hembra";
		genero[1] = "2.Macho";
		genero[1] = "3.Desconocido";
		

		comboGenero = new JComboBox<>(genero);
		comboGenero.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.PINK),"Genero del Pez"));
		panel.add(comboGenero);
	
		
		
		try {
			tipoPez = DAOTipoPez.getTiposPez();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
				
		comboTipo = new JComboBox<>(getTipoPez(tipoPez));
		comboTipo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.PINK),"Tipo de Pez"));
		panel.add(comboTipo);
		
		return panel;
	}

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),titulo));
		
		return campo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean anadir;
		
		switch (e.getActionCommand()){
		case "OK" : 
						JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
								"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
					
						try{
						Pez p = new Pez(txNomPez.getText(), seleccionarGenero(), seleccionarTipo(), Sesion.getInstance().getUsuario().getId(),  
								fabrica.getModeloPecera().getElementAt(fabrica.getListaPecera().getSelectedIndex()).getID());
						anadir = DAOPez.addPez(p);
						
								if(anadir){
						JOptionPane.showMessageDialog(this, "Pez OK",
								"Accion realizada", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
						}else{
							
						}
						}catch (NumberFormatException e2) {
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					break;
					
		case "Cancelar":
					this.dispose();
		}
		
	}

	private String seleccionarGenero() {
		return comboGenero.getSelectedItem().toString();
	}
	

	private int seleccionarTipo() {
		return comboTipo.getSelectedIndex() + 1;
	}
	
	public String[] getTipoPez(ArrayList<TipoPez> tipo){
		
		String tipoPez[] = new String[tipo.size()];
		
		for(int i = 0; i < tipo.size(); i++){
			
			tipoPez[i] = tipo.get(i).getDescripcion();
		
		}
		
		return tipoPez;
	}
	
	



}
