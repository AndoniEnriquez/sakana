package Dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.script.SimpleBindings;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ConexionDB.DAOPecera;
import VarTypes.Pecera;

@SuppressWarnings("serial")
public class DialogoAddPecera  extends JDialog implements ActionListener{
	
	final static String  TITULO = "Crear nueva Pecera";
	JComboBox<String> comboResponsable;
	JTextField txNombrePecera, txIP, txtCapacidad, txMin;
	JFormattedTextField txHora;
	
	SimpleDateFormat simpleDateFormat;
	
	public DialogoAddPecera (JFrame frame, boolean modo){
		super ( frame,TITULO,modo );
		crearVentana();
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
		JButton bOk = new JButton ("OK");
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

		panel.add(txNombrePecera = crearCampo("Nombre de la Pecera"));
		panel.add(txIP = crearCampo("IP"));
		panel.add(txtCapacidad = crearCampo("Capacidad"));
		panel.add(txHora = crearCampoFecha("Hora comida"));		
		return panel;
	}

	
	private JFormattedTextField crearCampoFecha(String titulo) {
	
		simpleDateFormat = new SimpleDateFormat("hh:mm");
		JFormattedTextField campoFecha = new JFormattedTextField(simpleDateFormat);
		campoFecha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN),titulo));
		campoFecha.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,18));
		return campoFecha;
	}
	

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN),titulo));
		
		return campo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean anadir;
		
		switch (e.getActionCommand()){
		case "OK" :
						try{
						Pecera p = new Pecera(txIP.getText(), txNombrePecera.getText(), Integer.parseInt(txtCapacidad.getText()));
						
						anadir = DAOPecera.addPecera(p);
						if(anadir){
							
							JOptionPane.showMessageDialog(this, "Pecera anadida","Accion realizada", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							
						}else{
							JOptionPane.showMessageDialog(this, "ERROR",
									"Imposible to Add Pecera", JOptionPane.INFORMATION_MESSAGE);
						}
						}catch (NumberFormatException e2) {

						} catch (Exception e1) {
							
							JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
									"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
					}
					break;
					
		case "Cancelar":
					this.dispose();
		}
		
	}



}
