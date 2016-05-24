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
import javax.swing.plaf.synth.SynthSplitPaneUI;

import ConexionDB.DAOPecera;
import ConexionDB.DAOTipoPez;
import Fabrica.FabricaAcciones;
import VarTypes.*;
import sakana.MenuPrincipal;

public class DialogoAddTipoPez extends JDialog implements ActionListener{
	
	final static String  TITULO = "Anadir tipo de pez";
	JComboBox<String> comboResponsable;
	JTextField txNombretipoPez;
	JTextField txPhMin, txPhMax, txTempMin, txTempMax;
	JFormattedTextField txHora;

	SimpleDateFormat simpleDateFormat;
	FabricaAcciones fabrica;

	TipoPez tipoPez;
	boolean edit;
	
	public DialogoAddTipoPez (JFrame frame, FabricaAcciones fabrica){
		super ( frame,TITULO,false );
		edit = false;
		crearVentana();
		this.fabrica = fabrica;
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public DialogoAddTipoPez (JFrame frame, FabricaAcciones fabrica, TipoPez p){
		super ( frame,TITULO,true);
		crearVentana();
		this.tipoPez = p;
		txNombretipoPez.setText(tipoPez.getDescripcion());
		txPhMin.setText(String.valueOf(tipoPez.getPhMin()));
		txPhMax.setText(String.valueOf(tipoPez.getPhMax()));
		txTempMin.setText(String.valueOf(tipoPez.getTemMin()));
		txTempMax.setText(String.valueOf(tipoPez.getTemMax()));
		edit = true;
		this.fabrica = fabrica;
		this.setVisible(true);
	}

	private void crearVentana() {
		this.setLocation(600,300);
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
	
	private Component crearPanelPH(){
		JPanel panel = new JPanel(new GridLayout(1,2,30,60));
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Niveles PH"));
		
		panel.add(txPhMin = crearCampo("PH" + " min."));
		panel.add(txPhMax = crearCampo("PH" + " max."));
		
		
		
		return panel;
	}
	
	private Component crearPanelTemp(){
		JPanel panel = new JPanel(new GridLayout(1,2,30,60));
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Temperatura"));
		
		panel.add(txTempMin = crearCampo("Temp" + " min."));
		panel.add(txTempMax = crearCampo("Temp" + " max."));
		
		
		
		return panel;
	}


	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(3,1,0,20));
		
		panel.add(txNombretipoPez = crearCampo("Nombre del tipo de Pez"));
	
		panel.add(crearPanelPH());
		panel.add(crearPanelTemp());	
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

			if(edit==false){
				try{
					
					TipoPez tp = new TipoPez(txNombretipoPez.getText(),Float.parseFloat(txPhMin.getText()),Float.parseFloat(txPhMax.getText()),Float.parseFloat(txTempMin.getText()),Float.parseFloat(txTempMax.getText()));
					
					anadir = DAOTipoPez.addTipoPez(tp);
					if(anadir){

						JOptionPane.showMessageDialog(this, "TipoPez anadido","Accion realizada", JOptionPane.INFORMATION_MESSAGE);
						this.dispose();

					}else{
						JOptionPane.showMessageDialog(this, "ERROR",
								"Imposible to Add TipoPez", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (NumberFormatException e2) {
					e2.printStackTrace();
					} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
							"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				try{
					tipoPez.setDescripcion(txNombretipoPez.getText());
					tipoPez.setPhMin(Float.parseFloat(txPhMin.getText()));
					tipoPez.setPhMax(Float.parseFloat(txPhMax.getText()));
					tipoPez.setTemMin(Float.parseFloat(txTempMin.getText()));
					tipoPez.setTemMax(Float.parseFloat(txTempMax.getText()));
					anadir = DAOTipoPez.updatePecera(tipoPez);
					if(anadir){

						JOptionPane.showMessageDialog(this, "TipoPez editado","Accion realizada", JOptionPane.INFORMATION_MESSAGE);
						//MenuPrincipal.desktopIzquierda.removeAll();
						//MenuPrincipal.desktopIzquierda.add(fabrica.accionamientoListaPecera());
						this.dispose();

					}else{
						JOptionPane.showMessageDialog(this, "ERROR",
								"Imposible to Edit TipoPez", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (NumberFormatException e2) {
					e2.printStackTrace();

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
							"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;

		case "Cancelar":
			this.dispose();
		}		
	}



}
