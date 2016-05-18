package sakana;

import java.util.Calendar;

public class Pecera {

	int ID;
	String IP;
	int capacidad;
	Calendar horacomida;
	
	
	public Pecera(int ID, String IP, int capacidad, Calendar horacomida){
		this.ID = ID;
		this.IP = IP;
		this.capacidad = capacidad;
		this.horacomida = horacomida;
	}


	
	public Calendar getHoracomida() {
		return horacomida;
	}



	public void setHoracomida(Calendar horacomida) {
		this.horacomida = horacomida;
	}



	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getIP() {
		return IP;
	}


	public void setIP(String iP) {
		IP = iP;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
}
