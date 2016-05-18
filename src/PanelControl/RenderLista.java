package PanelControl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings({ "serial", "rawtypes" })
public class RenderLista extends JLabel implements ListCellRenderer{

	 HashMap<String, Icon> elementos;
	 HashMap<String, String> toolTip;
	 
	 ImageIcon icononulo = new ImageIcon(("Iconos/error.png"));
	 
	 public RenderLista(HashMap<String, Icon> pelementos, HashMap<String, String> tool){
	
		 this.elementos = pelementos;
	     toolTip = tool; 
	 }
	 
	 public RenderLista() {
	
		 elementos = new HashMap<String, Icon>();   
	 }

	 
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		if (elementos.get(value) != null) {
         
			setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            setIcon(elementos.get(value));
            
            
            setText(value.toString());

            if (isSelected) {
                 
            	setFont(new Font("Verdana", Font.ITALIC, 16));
                setForeground(Color.GRAY);
                setBackground(Color.LIGHT_GRAY);
                setOpaque(true);
                 
            } else {
                setFont(new Font("Verdana", Font.BOLD, 16));
                setForeground(Color.LIGHT_GRAY);
                setOpaque(false);
            }
        } else {
            
        	setIcon(icononulo);
            setText(value.toString());

            if (isSelected) {
                setFont(new Font("Verdana", Font.BOLD, 22));
                setForeground(new java.awt.Color(24,165,211));
            } else {
                setFont(null);
                setForeground(Color.BLACK);
            }
        }

        if (toolTip!=null) {
             if (toolTip.get(value) != null) {
                 setToolTipText(toolTip.get(value));
             }
        }
        return this;
    }

}

