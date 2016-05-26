package conexionMicro;

import java.util.ArrayList;
import java.util.Calendar;

import ConexionDB.DAOMedicion;
import ConexionDB.DAOPecera;
import ConexionDB.DAORegComida;
import ConexionDB.DAOTipoMedicion;
import VarTypes.Medicion;
import VarTypes.Pecera;
import VarTypes.RegComida;

public class SincronizadorPeceras extends Thread {

	

	public static void main(String[] args) {
		
	}
	
	@Override
	public void run() {
		while(true){
			sincronizarPeceras();
			try {
				sleep(3000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		RegComida regComida;
		Float ph, temp;
		Medicion medicionPh, medicionTemp;
		while(!interfaz.isDisponible()){}
		ph = interfaz.getPh();
		while(!interfaz.isDisponible()){}
		temp = interfaz.getTemp();
		if(ph >= 0){
			medicionPh = new Medicion(	ph, 
										Calendar.getInstance(), 
										(DAOTipoMedicion.buscarPorNombre("Nivel PH")).getTipoMedicion_id(),
										p.getID());
			DAOMedicion.addMedicion(medicionPh);
		}
		if(temp >= 0){
			medicionTemp = new Medicion(	temp, 
											Calendar.getInstance(), 
											(DAOTipoMedicion.buscarPorNombre("Temperatura")).getTipoMedicion_id(),
											p.getID());
			DAOMedicion.addMedicion(medicionTemp);
		}
		while(!interfaz.isDisponible()){}
		while((regComida = interfaz.getFeedLogEntry()) != null){
			DAORegComida.addRegistro(regComida);
		}
	}
}
