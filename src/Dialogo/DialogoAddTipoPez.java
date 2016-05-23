package Dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Fabrica.FabricaAcciones;
import VarTypes.*;

public class DialogoAddTipoPez extends JDialog{
	
	final static String  TITULO = "Añadir tipo de pez";
	JComboBox<String> comboResponsable;
	JTextField txNombretipoPez, txIP, txtCapacidad, txMin;
	JTextField txPhMin, txPhMax, txTempMin, txTempMax;
	JFormattedTextField txHora;

	SimpleDateFormat simpleDateFormat;
	FabricaAcciones fabrica;

	TipoPez tipoPez;
	boolean edit;
	
	public DialogoAddTipoPez (JFrame frame, FabricaAcciones fabrica){
		super ( frame,TITULO,true );
		edit = false;
		crearVentana();
		this.fabrica = fabrica;
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public DialogoAddTipoPez (JFrame frame, FabricaAcciones fabrica, TipoPez p){
		super ( frame,TITULO,true);
		edit = true;
		this.tipoPez = p;
		simpleDateFormat = new SimpleDateFormat("hh:mm");
		crearVentana();
		this.fabrica = fabrica;
		this.setVisible(true);
		//txNombretipoPez.setText(tipoPez.getNombre());
		//txIP.setText(tipoPez.getIP());
		//txtCapacidad.setText(String.valueOf(tipoPez.getCapacidad()));
		//txHora.setText(simpleDateFormat.format(tipoPez.getHoracomida()));
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
		//bOk.addActionListener(this);
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("Cancelar");
		//bCancel.addActionListener(this);

		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}
	
	private Component crearPanelCampoDoble(String titulo, JTextField TF1, JTextField TF2){
		JPanel panel = new JPanel(new GridLayout(1,2,30,60));
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),titulo));
		
		panel.add(TF1 = crearCampo(titulo+ " min."));
		panel.add(TF2 = crearCampo(titulo+ " max."));
		
		
		return panel;
	}

	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(3,1,0,20));

		panel.add(txNombretipoPez = crearCampo("Nombre del tipo de Pez"));
		panel.add(crearPanelCampoDoble("PH",txPhMin, txPhMax));
		panel.add(crearPanelCampoDoble("Temperatura",txTempMin, txTempMax));	
		return panel;
	}

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN),titulo));

		return campo;
	}



}
