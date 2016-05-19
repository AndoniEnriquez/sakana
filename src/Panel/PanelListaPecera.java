package Panel;


import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import VarTypes.Pecera;




@SuppressWarnings("serial")
public class PanelListaPecera extends PanelExample implements ListSelectionListener{

	static int tamX = 400;
	static int tamY = 650;
	
	public JPanel panel, trans, aux;
	

	JScrollPane scrollPane;
	JList<Pecera> list;
	
	DefaultListModel<Pecera> modelo;
	
	
	
	public PanelListaPecera() {
		super(tamX, tamY);
		this.setBorder(null);
		
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
		
		Pecera pecera;
		pecera = new Pecera(1,"192","NE",4, 7.1f , 23.4f);
		
		modelo.addElement(pecera);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new RenderListaPecera());
		list.addListSelectionListener(this);

		scrollPane.setViewportView(list);
			
		
	}
	


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
