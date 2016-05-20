package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Fabrica.FabricaAcciones;

@SuppressWarnings("serial")
public class FormLogin extends JFrame implements ActionListener {
				
  protected JButton btnOK,btnSalir;
  protected JTextField txtUserName;
  protected JPasswordField txtPassword;
  protected JLabel lbUserName,lbPassword;
  FabricaAcciones fabrica;
  

  
  public FormLogin(FabricaAcciones fabrica) {
  
	  super("Login");
    
    this.setSize(320,210);
    this.setLocation(550,300);
    
    this.fabrica = fabrica;
    
    this.setContentPane(crearPanelVentana());
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
   }
    
    
    private Container crearPanelVentana() {
    	JPanel panel = new JPanel(new BorderLayout(0,10));
    	panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    	
    	panel.add(crearPanelCampos(), BorderLayout.CENTER);
    	panel.add(crearPanelBotones(), BorderLayout.SOUTH);
	 
	   return panel;
    }
    
	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER, 50,0));
		
		btnOK=new JButton(" OK ");
	    btnOK.addActionListener(this);
	    panel.add(btnOK);
	    
	    btnSalir=new JButton("Salir");
	   
	    btnSalir.addActionListener(this);
	    panel.add(btnSalir);
	    
		return panel;
	}
	
	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(2,1));

		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.PINK),BorderFactory.createEmptyBorder()));
		panel.add(crearPanelUsername());
		panel.add(crearPanelPassword());
		return panel;
	}
	
	private Component crearPanelPassword() {
		JPanel panel = new JPanel (new GridLayout(1,2,30,0));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		lbPassword=new JLabel("Password :");
	    lbPassword.setHorizontalAlignment(JLabel.RIGHT);
	    panel.add(lbPassword);
	    txtPassword=new JPasswordField(10);
	    txtPassword.addActionListener(this);
	    txtPassword.setBorder(BorderFactory.createLoweredBevelBorder());
	    panel.add(txtPassword);
		return panel;
	}
	
	private Component crearPanelUsername() {
		JPanel panel = new JPanel (new GridLayout(1,2,30,0));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    lbUserName=new JLabel("Usuario :");
	    lbUserName.setHorizontalAlignment(JLabel.RIGHT);
	    panel.add(lbUserName);
	    txtUserName=new JTextField(10);
	    txtUserName.setBorder(BorderFactory.createLoweredBevelBorder());
	    panel.add(txtUserName);
		return panel;
	}
	
    
    
  
  @SuppressWarnings("static-access")
public void actionPerformed(ActionEvent e){
   
	if((e.getSource() == btnOK)||(e.getSource() == this.txtPassword)) {
		
      LogCtrl logador=new LogCtrl();
      
      if(logador.validarUser(txtUserName.getText(),String.valueOf(txtPassword.getPassword()))) {
    	  
    	  //new FormRecursos(Sesion.getInstance().getUsuario());
    	
    	  fabrica.getPanelAjustes().labelUser.setText(txtUserName.getText());
    	  fabrica.getPanelAjustes().bInicio.setEnabled(false);
    	  fabrica.getPanelAjustes().bSalir.setEnabled(true);
    	  fabrica.getPanelAjustes().bAdd.setEnabled(true);
    	  fabrica.getPanelAjustes().bBloqueo.setEnabled(true);
    	
    	  this.setVisible(false);
    	  this.dispose();
      }
      else {
    	  JOptionPane.showMessageDialog(this,"Identificacion no valida", "Error de identificacion",
    			    JOptionPane.ERROR_MESSAGE);
      }
    }
    
	else if(e.getSource()==btnSalir) {
       this.dispose();
    }
  }
  
  

   
}