package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import VarTypes.Pecera;

@SuppressWarnings({"serial"})
public class RenderListaPecera extends JLabel implements ListCellRenderer<Pecera>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Pecera> list, Pecera pecera, int index,boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(new ImageIcon("Imagenes/peceras.png")), BorderLayout.WEST);
		JPanel panelDer = new JPanel(new GridLayout(1, 1));
		
		JLabel nombre = new JLabel(pecera.getNombre());
		nombre.setForeground(Color.MAGENTA);
		nombre.setFont(new Font("Arial",Font.ITALIC,16));
		panelDer.add(nombre);
		
		panel.add(panelDer, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		if(isSelected) panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
						
		return panel;
	}



}

