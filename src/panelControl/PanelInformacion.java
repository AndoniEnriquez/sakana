package panelControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conexionDB.DAOPecera;
import dialogo.DialogoAddPecera;
import dialogo.DialogoAddPez;
import dialogo.DialogoConect;
import dialogo.DialogoRegistroComida;
import fabrica.FabricaAcciones;
import panel.MiPanel;
import panel.PanelExample;
import varTypes.Pecera;

@SuppressWarnings("serial")
public class PanelInformacion extends PanelExample implements ActionListener {

	static int tamX = 0;
	static int tamY = 0;

	JLabel txNom, txIP, txCapacidad, txHoraComida, txPH, txTemp;
	JButton bEdit, bAdd, bRegComida, bConect;

	JPanel panel;
	MiPanel miPanel;

	Pecera pecera;
	FabricaAcciones fabrica;

	public PanelInformacion(FabricaAcciones fabrica) {
		
		super(tamX, tamY);
		this.setLocation(0, -30);
		this.fabrica = fabrica;
		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {

		panel = new JPanel();
		this.setBorder(null);
		panel.add(crearPanel());

		return panel;
	}

	private Container crearPanel() {
		
		JPanel panel = new JPanel(new BorderLayout(0, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setOpaque(false);
		panel.add(crearPanelCampos(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelBotones() {
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		panel.setOpaque(false);
		
		bEdit = new JButton("Editar Pecera");
		bEdit.setActionCommand("Editar");
		bEdit.addActionListener(this);
		
		bAdd = new JButton("Add Pez");
		bAdd.setActionCommand("Add");
		bAdd.addActionListener(this);
		
		bRegComida = new JButton("Registro Comida");
		bRegComida.setActionCommand("Reg");
		bRegComida.addActionListener(this);
		
		bConect = new JButton("Conectarse");
		bConect.setActionCommand("Connect");
		bConect.addActionListener(this);

		panel.add(bAdd);
		panel.add(bEdit);
		panel.add(bRegComida);
		panel.add(bConect);

		return panel;
	}

	private Component crearPanelCampos() {
		
		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 20));

		panel.setOpaque(false);
		panel.add(txNom = crearCampo("Nombre"));
		panel.add(txIP = crearCampo("IP"));
		panel.add(txCapacidad = crearCampo("Capacidad"));
		panel.add(txHoraComida = crearCampo("HoraComida"));
		panel.add(txPH = crearCampo("PH Actual"));
		panel.add(txTemp = crearCampo("Temperatura Actual"));

		return panel;
	}

	public void setText(Pecera pecera) {
		
		this.pecera = pecera;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

		txNom.setText(pecera.getNombre());
		txIP.setText(pecera.getIP());
		txHoraComida.setText(simpleDateFormat.format(pecera.getHoracomida()));
		txCapacidad.setText(String.valueOf(pecera.getCapacidad()));
		txPH.setText(recuperarValorPH());
		txTemp.setText(recuperarValorTemp());
	}

	private String recuperarValorTemp() {
		
		String valor;
		valor = DAOPecera.getValorTempPecera(pecera);
		return valor;
	}

	private String recuperarValorPH() {
	
		String valor;
		valor = DAOPecera.getValorPHPecera(pecera);
		return valor;
	}

	private JLabel crearCampo(String titulo) {
	
		JLabel campo = new JLabel();
		campo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.PINK), titulo));

		return campo;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		
		case "Add":

			new DialogoAddPez(fabrica.getMenuPrincipal(), false, fabrica);

			break;

		case "Editar":

			if (pecera != null) {
			
				new DialogoAddPecera(fabrica.getMenuPrincipal(), false, fabrica, pecera);
			}
			
			break;

		case "Reg":

			if (pecera != null) {
				
				new DialogoRegistroComida(fabrica.getMenuPrincipal(), pecera);
			}

			break;

		case "Connect":

			if (pecera != null) {
				
				new DialogoConect(fabrica.getMenuPrincipal(), fabrica, pecera, false);
			}

			break;

		default:
			break;
		}
	}
}
