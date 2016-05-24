package Panel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ConexionDB.DAOPecera;
import Dialogo.DialogoAddPecera;
import Fabrica.FabricaAcciones;
import VarTypes.Pecera;
import sakana.MenuPrincipal;




@SuppressWarnings("serial")
public class PanelListaPecera extends PanelExample implements ListSelectionListener, ActionListener{

	static int tamX = 400;
	static int tamY = 650;
	
	public JPanel panel, panelSup, trans, aux;
	
	JScrollPane scrollPane;
	JList<Pecera> list;
	
	DefaultListModel<Pecera> modelo;
	FabricaAcciones fabrica;
	
	JButton bAdd, bDelete;
	
	
	public PanelListaPecera(FabricaAcciones fabrica) {
		super(tamX, tamY);
		this.setBorder(null);
		
		this.fabrica = fabrica;
		modelo = new DefaultListModel<>();
		this.setContentPane(crearPanelPrin());
		
	}
	
	private Container crearPanelPrin(){
		
		panel = new JPanel(new BorderLayout());
		
		panelSup = new JPanel();
		panelSup.setBackground(Color.LIGHT_GRAY);
		panelSup.setPreferredSize(new Dimension(0, 45));
		
		bAdd = new JButton (new ImageIcon("Iconos/ajustes/plus.png"));
		bAdd.setActionCommand("Add");
		bAdd.addActionListener(this);
		bAdd.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		
		bDelete = new JButton (new ImageIcon("Iconos/ajustes/delete.png"));
		bDelete.setActionCommand("Delete");
		bDelete.addActionListener(this);
		bDelete.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		panelSup.add(bAdd);
		panelSup.add(Box.createRigidArea(new Dimension(25,25)));
		panelSup.add(bDelete);
		
		panel.add(panelSup, BorderLayout.SOUTH);
		panel.add(this.crearPanelVentana(), BorderLayout.CENTER);
		
		return panel;
	}
	
	private Container crearPanelVentana() {

		scrollPane = new JScrollPane();
		this.setBorder(null);
		this.controlPeceras();
		
		return scrollPane;
		
	}

	public void controlPeceras(){

		list = new JList<Pecera>(modelo);
		this.cargarPeceras();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new RenderListaPecera());
		list.addListSelectionListener(this);
		fabrica.setModeloPecera(modelo);
		fabrica.setListaPecera(list);
		scrollPane.setViewportView(list);
	}
	
	public void cargarPeceras(){
		
		ArrayList<Pecera> listaPecera = new ArrayList<>();
		modelo.removeAllElements();
		try {
			listaPecera = DAOPecera.getPeceras();
		} catch (Exception e) {
			// TODO Auto-generated catch blocko
			e.printStackTrace();
		}
		
		for(int i = 0; i < listaPecera.size(); i++){
			
			modelo.addElement(listaPecera.get(i));
			
		}
		
	}


	@SuppressWarnings("static-access")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		fabrica.getPanelPeces().controlLista();
		fabrica.getPanelInformacion().setText(modelo.getElementAt(list.getSelectedIndex()));
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "Add":
			
			new DialogoAddPecera(fabrica.getMenuPrincipal(), false, fabrica);
			
			break;

		case "Delete":
			
			try{
				
				Pecera p = modelo.getElementAt(list.getSelectedIndex());
				DAOPecera.eliminarPecera(p);
				MenuPrincipal.desktopIzquierda.removeAll();
				MenuPrincipal.desktopIzquierda.add(fabrica.accionamientoListaPecera());
				
			}catch (Exception e1){
				
			}
			
			break;
		default:
			break;
		}
		
	}

}
