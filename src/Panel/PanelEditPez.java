package Panel;


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
import javax.swing.JPanel;
import javax.swing.JTextField;

import ConexionDB.DAOTipoPez;
import Fabrica.FabricaAcciones;
import VarTypes.Pez;
import VarTypes.TipoPez;



public class PanelEditPez extends JPanel implements ActionListener{
	
	JComboBox<String> comboResponsable;
	JTextField txNom;
	JComboBox<String> comboGenero, comboTipo;
	ArrayList<TipoPez> tipoPez;
	FabricaAcciones fabrica;
	
	public PanelEditPez (FabricaAcciones fabrica){
		this.fabrica = fabrica;
		crearVentana();
		this.setVisible(true);
		fabrica.setPanelEditPez(this);
	}

	private void crearVentana() {
		this.setLocation(280,200);
		this.setSize(300, 380);
		this.add(crearPanel());
		this.setOpaque(false);
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
		JButton bEdit = new JButton ("Editar");
		bEdit.setActionCommand("Editar");
		bEdit.addActionListener(this);
		JButton bBorrar = new JButton ("Borrar");
		bBorrar.setActionCommand("Borrar");
		bBorrar.addActionListener(this);
		
		panel.add(bEdit);
		panel.add(bBorrar);
		return panel;
	}

	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(5,1,0,20));
		
		String nombres [];
		panel.setOpaque(false);
	
		panel.add(txNom = crearCampo("Nombre de Pez"));

		String genero [];
		genero = new String [3];
		genero[0] = "Hembra";
		genero[1] = "Macho";
		genero[2] = "Desconocido";
		

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
		
		this.setTextPez();
		
		return panel;
	}
	
	public int seleccionarGenero(Pez p){
		
		if(p.getGenero().toLowerCase().contains("macho")){
			
			return 1;
			
		}else if(p.getGenero().toLowerCase().contains("hembra")) {
			
			return 0;
			
		}else{
			
			return 2;
		}
		
	}

	public void setTextPez(){
		
		txNom.setText(fabrica.getModeloPez().getElementAt(fabrica.getListaPez().getSelectedIndex()).getNombrePez());
		comboGenero.setSelectedIndex(this.seleccionarGenero(fabrica.getModeloPez().getElementAt(fabrica.getListaPez().getSelectedIndex())));
		comboTipo.setSelectedIndex(fabrica.getModeloPez().getElementAt(fabrica.getListaPez().getSelectedIndex()).getTipoPez_id()-1);
	}

	
	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
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

	public String[] getTipoPez(ArrayList<TipoPez> tipo){
		
		String tipoPez[] = new String[tipo.size()];
		
		for(int i = 0; i < tipo.size(); i++){
			
			tipoPez[i] = tipo.get(i).getDescripcion();
		
		}
		
		return tipoPez;
	}
	
	private int seleccionarTipo() {
		return comboResponsable.getSelectedIndex() + 1;
	}

	private boolean camposIncompletos() {
		
		return false;//txNombre.getText().length()==0 ||txUbicaci�n.getText().length()==0 || txDescripci�n.getText().length()==0;
	}
}
