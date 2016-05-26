package Dialogo;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Fabrica.FabricaAcciones;
import VarTypes.Pecera;

@SuppressWarnings("serial")
public class DialogoConect extends JDialog implements ActionListener{

	final static String  TITULO = "Gestion Micro";
	
	JTextField txPH, txTemp, txFedTime, txCurTime, txPHOff, txFischN, txMeals;
	
	Pecera p; 
	FabricaAcciones fabrica;
	
	public DialogoConect(JFrame frame, FabricaAcciones fabrica, Pecera p, boolean modo){
		
		super ( frame, TITULO, modo );
		this.p = p;
		this.fabrica = fabrica;
		crearVentana();
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
		
		return panel;
	}

	private JTextField crearCampo(String titulo) {
		
		JTextField campo = new JTextField();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK),titulo));
		return campo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
