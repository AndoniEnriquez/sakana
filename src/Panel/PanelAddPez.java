package Panel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PanelAddPez extends JPanel implements ActionListener{
	
	JComboBox<String> comboResponsable;
	JTextField txNomUsuario,txNombre,txPassword,txDNI;
	
	public PanelAddPez (){
		crearVentana();
		this.setVisible(true);
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
		JButton bOk = new JButton ("Validar");
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
		JPanel panel = new JPanel (new GridLayout(5,1,0,20));
		
		String nombres [];
		panel.setOpaque(false);
		panel.add(txNombre = crearCampo("Nombre"));
		panel.add(txNomUsuario = crearCampo("Nombre de Pez"));
		panel.add(txPassword = crearCampo("Contraseña"));
		panel.add(txDNI = crearCampo("DNI"));
		

		nombres = new String [2];
		nombres[0] = "1.Administrador";
		nombres[1] = "2.Normal";
		
		/*nombres = new String [listaPersonas.size()];
		for (int i = 0; i<nombres.length; i++){
			nombres [i] = listaPersonas.get(i).getNombre();
		}*/
		comboResponsable = new JComboBox<>(nombres);
		comboResponsable.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.PINK),"Tipo Usuario"));
		panel.add(comboResponsable);
		return panel;
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

	private int seleccionarTipo() {
		return comboResponsable.getSelectedIndex() + 1;
	}

	private boolean camposIncompletos() {
		
		return false;//txNombre.getText().length()==0 ||txUbicaci�n.getText().length()==0 || txDescripci�n.getText().length()==0;
	}
}

