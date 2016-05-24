package conexionMicro;

import java.util.ArrayList;

import ConexionDB.DAOComida;
import ConexionDB.DAOPecera;
import VarTypes.Pecera;

public class SincronizadorPeceras extends Thread {

	

	public static void main(String[] args) {
		
	}
	
	@Override
	public void run() {
		while(true){
			sincronizarPeceras();
		}
		
		
	}
	
	private void sincronizarPeceras() {
		ArrayList<Pecera> peceras = null;
		try {
			peceras = DAOPecera.getPeceras();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Pecera p : peceras) {
			sincronizarPecera(p);
		}
	}

	private void sincronizarPecera(Pecera p) {
		InterfazMicro interfaz = new InterfazMicro(p);
		
		interfaz.getPh();
		interfaz.getTemp();
		DAOComida.
		interfaz.getFeedLogEntry(c)
		
	}

}
