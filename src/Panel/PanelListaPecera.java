package Panel;


import java.awt.Container;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ConexionDB.DAOPecera;
import Fabrica.FabricaAcciones;
import VarTypes.Pecera;




@SuppressWarnings("serial")
public class PanelListaPecera extends PanelExample implements ListSelectionListener{

	static int tamX = 400;
	static int tamY = 650;
	
	public JPanel panel, trans, aux;
	
	JScrollPane scrollPane;
	JList<Pecera> list;
	
	DefaultListModel<Pecera> modelo;
	FabricaAcciones fabrica;
	
	
	public PanelListaPecera(FabricaAcciones fabrica) {
		super(tamX, tamY);
		this.setBorder(null);
		
		this.fabrica = fabrica;
		modelo = new DefaultListModel<>();
		this.setContentPane(crearPanelVentana());
		
	}
	
	private Container crearPanelVentana() {

		scrollPane = new JScrollPane();
		this.setBorder(null);
		
		this.controlPeceras();
		//scrollPane.getViewport().add(list);
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < listaPecera.size(); i++){
			
			modelo.addElement(listaPecera.get(i));
			
		}
		
	}


	@SuppressWarnings("static-access")
	@Override
	public void valueChanged(ListSelectionEvent e) {

		fabrica.getPanelInformacion().setText(modelo.getElementAt(list.getSelectedIndex()));
	}

}
