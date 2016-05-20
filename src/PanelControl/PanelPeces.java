package PanelControl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ConexionDB.DAODueno;
import ConexionDB.DAOPecera;
import ConexionDB.DAOPez;
import Fabrica.FabricaAcciones;
import Login.Sesion;
import Panel.MiPanel;
import Panel.PanelEditPez;
import Panel.PanelExample;
import Panel.RenderListaPecera;
import VarTypes.Dueno;
import VarTypes.Pecera;
import VarTypes.Pez;

@SuppressWarnings("serial")
public class PanelPeces extends PanelExample implements ActionListener, ListSelectionListener{

	static int tamX = 0;
	static int tamY = 0;
	
	JPanel panel, panelSecundario;
	JScrollPane scrollPane;
	MiPanel miPanel;
	JButton add, delete, edit;
	DefaultListModel<Pez> modelo;
	
	FabricaAcciones fabrica;

	JList<Pez> list;
	
	@SuppressWarnings("static-access")
	public PanelPeces(FabricaAcciones fabrica) {
		super(tamX, tamY);
		this.fabrica = fabrica;
		modelo = new DefaultListModel<>();
		this.setContentPane(crearPanelVentana());
		fabrica.setPanelPeces(this);
	}

	private Container crearPanelVentana() {
		
		panel = new JPanel(new GridLayout(1, 2));
		
		scrollPane = new JScrollPane();
		this.setBorder(null);
		this.controlLista();
		
		//scrollPane.getViewport().add(list);
		panel.add(scrollPane);
		panel.add(crearPanelSecundario());
		
		return panel;
	}
	
	private Component crearPanelSecundario(){
		panelSecundario = new JPanel();

		panelSecundario.setBackground(Color.WHITE);

		return panelSecundario;		
	}
	
	private Component crearPanelAdd(){
		
		PanelEditPez panelAdd = new PanelEditPez(fabrica);
		
		return panelAdd;
	}
	



	@SuppressWarnings("unchecked")
	public void controlLista(){
		
		list = new JList<Pez>(modelo);
		this.cargarPeces();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new RenderLista());
		list.addListSelectionListener(this);
	
		fabrica.setModeloPez(modelo);
		fabrica.setListaPez(list);
		scrollPane.setViewportView(list);
	
	}
	
	public void cargarPeces(){
		Pecera p;
		ArrayList<Pez> listaPecera = new ArrayList<>();
		modelo.removeAllElements();
		Dueno d = Sesion.getInstance().getUsuario();
		try{
		p =fabrica.getModeloPecera().getElementAt(fabrica.getListaPecera().getSelectedIndex());
		}catch (Exception e) {
			p = fabrica.getModeloPecera().getElementAt(0);
		}
		try {
			listaPecera = DAOPez.getPecesPeceraDueno(p, d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < listaPecera.size(); i++){
			
			modelo.addElement(listaPecera.get(i));
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch(e.getActionCommand()){
		
			case "Add":
				
				break;
		}
		

		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int index = list.getSelectedIndex();
		try{
			fabrica.getPanelEditPez().setTextPez();
		}catch (Exception e1){
			
			
			
		}
		
		
		switch (index) {
		
		case 0:
			
			panelSecundario.removeAll();
			panelSecundario.add(crearPanelAdd());
			
			break;

			
		default:
			
			try{
				
				//INDEX DE CADA PEZ
				
			}catch(Exception e1){
				
				
			}
			
			break;
		}
		
	}
	
}
