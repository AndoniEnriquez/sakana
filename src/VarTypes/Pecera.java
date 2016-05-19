package VarTypes;

import java.util.Calendar;

public class Pecera {

	int ID;
	String IP;
	String nombre;
	int capacidad;
	Calendar horacomida;
	
	float PH, Temp;
	
	
	public Pecera(int ID, String IP, String nombre, int capacidad, Calendar horacomida){
		this.ID = ID;
		this.IP = IP;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.horacomida = horacomida;
	}

	public Pecera(int ID, String IP, String nombre, int capacidad, float PH, float Temp){
		this.ID = ID;
		this.IP = IP;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.PH = PH;
		this.Temp = Temp;
	}


	
	public float getPH() {
		return PH;
	}

	public void setPH(float pH) {
		PH = pH;
	}

	public float getTemp() {
		return Temp;
	}

	public void setTemp(float temp) {
		Temp = temp;
	}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
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
