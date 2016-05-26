package dialogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import conexionDB.DAOTipoPez;
import fabrica.FabricaAcciones;
import varTypes.TipoPez;

@SuppressWarnings("serial")
public class DialogoConfiguracion extends JDialog implements ActionListener {

	JButton bAddUsuario, bAddTipoPez, bEditTipoPez;

	JComboBox<String> cTipoPez;

	ArrayList<TipoPez> tipoPez;

	FabricaAcciones fabrica;

	public DialogoConfiguracion(JFrame ventana, FabricaAcciones fabrica) {
		super(ventana, "Configuracion", false);
		
		this.fabrica = fabrica;
		
		this.setLocation(200, 100);
		this.setSize(250, 320);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		
		JPanel panel = new JPanel(new FlowLayout());
		
		panel.add(crearBotonUsuario());
		panel.add(crearBotonTipoPez());
		panel.add(crearEditBoton());
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		return panel;
	}

	private Component crearEditBoton() {
		
		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN), "Editar Tipo de Pez"));
		
		bEditTipoPez = new JButton("Edit");
		bEditTipoPez.setActionCommand("EditTipoPez");
		bEditTipoPez.addActionListener(this);

		try {
			
			tipoPez = DAOTipoPez.getTiposPez();
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}

		cTipoPez = new JComboBox<>(getTipoPez(tipoPez));
		cTipoPez.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK), "Tipo de Pez"));

		panel.add(cTipoPez);
		panel.add(bEditTipoPez);
		
		return panel;
	}

	private Component crearBotonTipoPez() {
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(200, 70));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN), "Añadir Nuevo Tipo de Pez"));
		
		bAddTipoPez = new JButton("Add");
		bAddTipoPez.setActionCommand("AddTipoPez");
		bAddTipoPez.addActionListener(this);
		
		panel.add(bAddTipoPez);
		
		return panel;
	}

	private Component crearBotonUsuario() {
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(200, 70));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.CYAN), "Añadir Nuevo Usuario"));
		
		bAddUsuario = new JButton("Add");
		bAddUsuario.setActionCommand("AddUsuario");
		bAddUsuario.addActionListener(this);
		
		panel.add(bAddUsuario);
		
		return panel;
	}

	public String[] getTipoPez(ArrayList<TipoPez> tipo) {

		String tipoPez[] = new String[tipo.size()];

		for (int i = 0; i < tipo.size(); i++) {

			tipoPez[i] = tipo.get(i).getDescripcion();

		}

		return tipoPez;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {

		case "AddUsuario":

			new DialogoAddDueno(fabrica.getMenuPrincipal(), false, fabrica);
			
			break;
			
		case "AddTipoPez":
			
			new DialogoAddTipoPez(fabrica.getMenuPrincipal(), fabrica);
			
			break;

		case "EditTipoPez":
			
			new DialogoAddTipoPez(fabrica.getMenuPrincipal(), fabrica, tipoPez.get(cTipoPez.getSelectedIndex()));
			
			break;
		}
	}

}
