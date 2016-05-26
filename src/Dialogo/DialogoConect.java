package Dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Fabrica.FabricaAcciones;
import VarTypes.Pecera;
import conexionMicro.InterfazMicro;

@SuppressWarnings("serial")
public class DialogoConect extends JDialog implements ActionListener{

	final static String  TITULO = "Gestion Micro";
	
	JLabel txPH, txTemp, txFedTime, txCurTime, txPHOff, txFischN, txMeals;
	
	Pecera p; 
	FabricaAcciones fabrica;
	InterfazMicro interfaz;
	
	Float ph, phOff, temp;
	int meal, fishN;
	Calendar dateTime, feedTime;
	
	public DialogoConect(JFrame frame, FabricaAcciones fabrica, Pecera p, boolean modo){
		
		super ( frame, TITULO, modo );
		this.p = p;
		this.fabrica = fabrica;
		interfaz = new InterfazMicro(p);
		if(sincronizarDatos() == true){
			crearVentana();
		}	
		this.setVisible(true);
	}

	private void crearVentana() {
		
		this.setLocation(600,300);
		this.setSize(500, 350);
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
		
		JButton bSinc = new JButton ("Sincronizar");
		bSinc.setActionCommand("Sinc");
		bSinc.addActionListener(this);
		
		JButton bActu = new JButton ("Actualizar");
		bActu.setActionCommand("Actu");
		bActu.addActionListener(this);
		
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("Cancelar");
		bCancel.addActionListener(this);
		
		panel.add(bSinc);
		panel.add(bActu);
		panel.add(bCancel);
		
		return panel;
	}

	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(4,2,0,20));
		
		panel.add(txPH = crearCampo("PH"));
		panel.add(txPHOff = crearCampo("Calibrar PH"));
		
		panel.add(txTemp = crearCampo("Temperatura"));
		panel.add(txCurTime = crearCampo("Time"));
		
		panel.add(txFedTime = crearCampo("Hora de comer"));
		panel.add(txMeals = crearCampo("Comidas restantes"));
		
		panel.add(txFischN = crearCampo("Numero de peces"));
		
		colocarDatos();
		
		return panel;
	}

	private JLabel crearCampo(String titulo) {
		
		JLabel campo = new JLabel();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK),titulo));
		return campo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	public boolean sincronizarDatos(){
				
		if(		(ph = interfaz.getPh()) != -1 
				&& (phOff = interfaz.getPhOffset()) != -1
				&& (temp = interfaz.getTemp()) != -1 
				&& (meal = interfaz.getMeals()) != -1 
				&& (fishN = interfaz.getFishNo()) != -1 
				&& (dateTime = interfaz.getDateTime()) != null 
				&& (feedTime = interfaz.getFeedTime()) != null ){
			
			return true;
			
		}else {
			
			JOptionPane.showMessageDialog(this, "Conexion Fallida", "Accion no realizada", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
	}
	
	public void colocarDatos(){
		
		txPH.setText(String.valueOf(ph));
		txPHOff.setText(String.valueOf(phOff));
		txTemp.setText(String.valueOf(temp));
		txMeals.setText(String.valueOf(meal));
		txFischN.setText(String.valueOf(fishN));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
		String feedT = simpleDateFormat.format(feedTime);
		
		simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		String dateT = simpleDateFormat.format(dateTime);
		
		txFedTime.setText(feedT);
		txCurTime.setText(dateT);
		
	}
}
