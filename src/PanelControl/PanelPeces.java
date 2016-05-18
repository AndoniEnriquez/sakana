package PanelControl;

import java.awt.Color;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Panel.MiPanel;
import Panel.PanelAddPez;
import Panel.PanelExample;

@SuppressWarnings("serial")
public class PanelPeces extends PanelExample implements ActionListener, ListSelectionListener{

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
		panelSecundario = new JPanel();

		panelSecundario.setBackground(Color.WHITE);
		//panelSecundario.add(crearPanelAdd());
		//panelSecundario.add(crearPanelDelete());
		//panelSecundario.add(crearPanelEdit());
		
		return panelSecundario;		
	}
	
	private Component crearPanelAdd(){
		
		PanelAddPez panelAdd = new PanelAddPez();
		
		return panelAdd;
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
		list.addListSelectionListener(this);
		
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int index = list.getSelectedIndex();
		
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
