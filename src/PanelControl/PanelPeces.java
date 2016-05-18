package PanelControl;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Panel.MiPanel;
import Panel.PanelExample;

@SuppressWarnings("serial")
public class PanelPeces extends PanelExample implements ActionListener{

	static int tamX = 0;
	static int tamY = 0;
	
	JPanel panel, panelSecundario;
	JScrollPane scrollPane;
	MiPanel miPanel;
	JButton add, delete, edit;

	private HashMap<String, Icon> elementos = new HashMap<String, Icon>();
	private HashMap<String, String> tooltip = new HashMap<String, String>();

	Object datos  []={"NUEVO PEZ","DORY","Pablo","Juana","NEMO","DORY","Pablo","Juana"};  
	String toolTipSexo[]={"Nuevo","Naranja","Naranja","Naranja","Naranja","Naranja","Naranja","Naranja"};
	
	JList<Object> list;
	
	public PanelPeces() {
		super(tamX, tamY);

		
		this.controlLista();
		this.setContentPane(crearPanelVentana());
	}

	private Container crearPanelVentana() {
		
		panel = new JPanel(new GridLayout(1, 2));
		
		scrollPane = new JScrollPane();
		this.setBorder(null);
		this.controlInformacion();
		
		scrollPane.getViewport().add(list);
		panel.add(scrollPane);
		panel.add(crearPanelSecundario());
		
		return panel;
	}
	
	private Component crearPanelSecundario(){
		panelSecundario = new JPanel(new GridLayout(3, 1));

		panelSecundario.add(crearPanelAdd());
		//panelSecundario.add(crearPanelDelete());
		//panelSecundario.add(crearPanelEdit());
		
		return panelSecundario;		
	}
	
	private Component crearPanelAdd(){
		
		JPanel panel = new JPanel();
		
		add = new JButton("ADD");
		add.setActionCommand("Add");
		add.addActionListener(this);
		
		panel.add(add);
		
		return panel;
	}
	

	private Component crearPanelDelete(){
		
		delete = new JButton("DELETE");
		
		return null;
	}
	
	private Component crearPanelEdit(){
		
		
		edit = new JButton("EDIT");
		return null;
	}

	@SuppressWarnings("unchecked")
	public void controlLista(){
		
		list = new JList<Object>();
		list.setListData(datos);
		
		 for(int i = 0; i < datos.length; i++){
			 
	            if(toolTipSexo[i].equals("Naranja")) {
	            	
	                elementos.put(datos[i].toString(), new ImageIcon("iconos/pez/pezNaranja.png"));
	                
	            }else {
	            	
	                elementos.put(datos[i].toString(), new ImageIcon("iconos/pez/plus.png"));
	            }
	            tooltip.put(datos[i].toString(), toolTipSexo[i]);
	        }

	        RenderLista render = new RenderLista(elementos, tooltip);
	        list.setCellRenderer(render);

	}

	private void controlInformacion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
		
			case "Add":
				
				break;
		}
		
	}
	
}
