package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Fabrica.*;


public class PanelAjustes implements ActionListener{

	JPanel panel;
	public JButton bBloqueo;
	JLabel labelHora;
	
	FabricaAcciones fabrica;
	
	Boolean bloqueado = true;
	
	
	public JPanel crearPanelAjustes(FabricaAcciones fabrica) {

		panel = new JPanel();
		
		this.fabrica = fabrica;
		labelHora = new JLabel();

		panel.setBackground(Color.lightGray); 

		Runnable runnable = new ControlHora();
		Thread threadHora = new Thread(runnable);
		threadHora.start();

		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		bBloqueo = new JButton(new ImageIcon("iconos/ajustes/lock.png"));
		bBloqueo.setActionCommand("Bloqueo");
		bBloqueo.addActionListener(this);
		bBloqueo.add(Box.createRigidArea(new Dimension(35,35)));
		bBloqueo.setBorder(BorderFactory.createEmptyBorder(10,2,2,2));
		
		labelHora.setFont(new Font("Arial", Font.BOLD, 15));
		labelHora.setForeground(Color.WHITE);

		panel.add(labelHora);
		panel.add(Box.createHorizontalGlue());
		
		panel.add(bBloqueo);
		panel.add(Box.createRigidArea(new Dimension(25,25)));

		return panel;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()){
		
			case "Bloqueo":
			
				this.controlBloqueo();
		
				break;
				
		}
	}
	
	public void controlBloqueo(){
		
		if(bloqueado == false){
			
			System.out.println("BLOQUEADO");
			bBloqueo.setIcon(new ImageIcon("iconos/ajustes/lock.png"));
			bloqueado = true;
			
		}else {
			
			System.out.println("DESBLOQUEADO");
			bBloqueo.setIcon(new ImageIcon("iconos/ajustes/unlock.png"));
			bloqueado = false;
			
		}
		
	}
	
	public class ControlHora  implements Runnable{

		DateFormat formatoHora = new SimpleDateFormat("     HH:mm:ss");

		@Override
		public void run() {
			while(true){

				labelHora.setText(formatoHora.format(new Date()));

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}		
		}	
	}

}
