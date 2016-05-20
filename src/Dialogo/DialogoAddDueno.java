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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ConexionDB.DAODueno;
import ConexionDB.DAOPecera;
import Fabrica.FabricaAcciones;
import VarTypes.Dueno;
import VarTypes.Pecera;
import sakana.MenuPrincipal;

public class DialogoAddDueno extends JDialog implements ActionListener{
	
	final static String  TITULO = "Anadir dueno";
	JComboBox<String> comboResponsable;
	JTextField txNombre, txPassword;

	FabricaAcciones fabrica;
	
	public DialogoAddDueno (JFrame frame, boolean modo, FabricaAcciones fabrica){
		super ( frame,TITULO,modo );
		crearVentana();
		this.fabrica = fabrica;
		this.setVisible(true);
	}

	private void crearVentana() {
		this.setLocation(280,200);
		this.setSize(300, 200);
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
		JPanel panel = new JPanel (new GridLayout(5,1,0,20));

		panel.add(txNombre = crearCampo("Nombre"));
		panel.add(txPassword = crearCampo("Password"));
		return panel;
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
							
						Dueno d = new Dueno(txNombre.getText(), txPassword.getText());
						//anadir = DAODueno
						
						if(anadir){
							
							JOptionPane.showMessageDialog(this, "Dueno anadido","Accion realizada", JOptionPane.INFORMATION_MESSAGE);
							MenuPrincipal.desktopIzquierda.removeAll();
							MenuPrincipal.desktopIzquierda.add(fabrica.accionamientoListaPecera());
							this.dispose();
							
						}else{
							JOptionPane.showMessageDialog(this, "ERROR",
									"Imposible to Add Dueno", JOptionPane.INFORMATION_MESSAGE);
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
