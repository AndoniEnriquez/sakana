package panelControl;

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

import varTypes.Pez;

@SuppressWarnings("serial")
public class RenderLista extends JLabel implements ListCellRenderer<Pez> {

	public Component getListCellRendererComponent(JList<? extends Pez> list, Pez pez, int index, boolean isSelected,
			boolean cellHasFocus) {

		JPanel panel = new JPanel(new BorderLayout());
	
		if (pez.getGenero().toLowerCase().contains("macho")) {
		
			panel.add(new JLabel(new ImageIcon("Iconos/pez/Blinky.png")), BorderLayout.WEST);
		
		} else if (pez.getGenero().toLowerCase().contains("hembra")) {
			
			panel.add(new JLabel(new ImageIcon("Iconos/pez/Pez_Linterna.png")), BorderLayout.WEST);

		} else {
			
			panel.add(new JLabel(new ImageIcon("Iconos/pez/magikarp.png")), BorderLayout.WEST);

		}

		JPanel panelDer = new JPanel(new GridLayout(1, 1));

		JLabel nombre = new JLabel(pez.getNombrePez());
		nombre.setForeground(Color.MAGENTA);
		nombre.setFont(new Font("Arial", Font.ITALIC, 16));
		panelDer.add(nombre);

		panel.add(panelDer, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		if (isSelected)
		
			panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

		return panel;
	}

}
