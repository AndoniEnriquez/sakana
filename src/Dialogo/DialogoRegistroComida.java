package Dialogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Login.Sesion;
import VarTypes.Pecera;
import tablas.ModeloColumnasTablaRegistroComida;
import tablas.ModeloTablaRegistroComida;
import tablas.TrazadorTablaRegistroComida;

@SuppressWarnings("serial")
public class DialogoRegistroComida extends JDialog implements ListSelectionListener{

	JTable vTabla;
	ModeloColumnasTablaRegistroComida columnas;
	TrazadorTablaRegistroComida trazador;
	ModeloTablaRegistroComida tabla;

	Pecera pecera;
	
	public DialogoRegistroComida (JFrame ventana, Pecera p){
		super (ventana,"Registro Comidas",true);
		this.pecera = p;
		this.setLocation(200,100);
		this.setSize(600, 450);
		this.setContentPane(crearPanelVentana());
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(0,10));
		panel.add(crearPanelDatos(),BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		return panel;
	}

	private Container crearPanelDatos() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN), "Datos Pecera"));
		panel.add(crearPanelRecurso(),BorderLayout.NORTH);
		panel.add(crearPanelTabla(),BorderLayout.CENTER);
		return panel;
	}


	


	private Component crearPanelTabla() {
		JPanel panel = new JPanel(new BorderLayout(10,0));
		panel.add(crearPanelTitulo(),BorderLayout.NORTH);
		panel.add(crearScrollTabla(),BorderLayout.CENTER);
		return panel;
	}

	private Component crearScrollTabla() {
		JScrollPane panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		crearTabla();
		panelS.setViewportView(vTabla);
		return panelS;
	}
	
	private void crearTabla() {
		trazador = new TrazadorTablaRegistroComida();
		columnas = new ModeloColumnasTablaRegistroComida (trazador);
		try {
			tabla = new ModeloTablaRegistroComida(columnas,pecera);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vTabla = new JTable(tabla,columnas);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.getSelectionModel().addListSelectionListener(this);
		vTabla.setFillsViewportHeight(true);
		vTabla.getTableHeader().setReorderingAllowed(false);
		
		if (tabla.getRowCount()>0)
			vTabla.setRowSelectionInterval(0, 0);
		vTabla.setFillsViewportHeight(true);

	}
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER));
		panel.setBorder (BorderFactory.createRaisedBevelBorder());
		panel.add(new JLabel("Registro de comidas"));
		return panel;
	}

	private Component crearPanelRecurso() {
		JPanel panel = new JPanel (new GridLayout(2,1,20,20));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10,10,0,10),
				BorderFactory.createLoweredBevelBorder()));
		panel.add(crearCampo("Nombre",pecera.getNombre()));
		panel.add(crearCampo("Direccion IP",pecera.getIP()));
		return panel;
	}

	private JPanel crearCampo(String etiqueta, String valor) {
		JPanel panel = new JPanel (new FlowLayout(FlowLayout.LEFT,20,0));
		JLabel labelEtiqueta = new JLabel (etiqueta);
		JLabel labelValor = new JLabel(valor);
		labelValor.setFont (new Font("Arial",Font.BOLD|Font.ITALIC,16));
		labelValor.setForeground(Color.DARK_GRAY);
		panel.add(labelEtiqueta);
		panel.add(labelValor);
		return panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		/*int index = vTabla.getSelectedRow();
		DateTimeFormatter formatter =   DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");
		if (index == -1){
			accDelete.setEnabled(false);
			accEdit.setEnabled(false);
			accTake.setEnabled(false);
		}else if (tabla.getReservaAt(index).getPersona().getId()!=Sesion.getInstance().getUsuario().getId()){
			accDelete.setEnabled(false);
			accEdit.setEnabled(false);
			accTake.setEnabled(false);
		}else if(index == 0){
			accTake.setEnabled(true);
			accDelete.setEnabled(true);
			accEdit.setEnabled(true);
		}else{
			accDelete.setEnabled(true);
			accEdit.setEnabled(true);
		}*/
	}
}
