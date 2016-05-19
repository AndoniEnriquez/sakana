package VarTypes;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Pecera {

	int ID;

	String IP;
	String nombre;
	int capacidad;
	Date horacomida;
	
	float PH, Temp;
	
	
	public Pecera(String IP, String nombre, int capacidad){
		this.IP = IP;
		this.nombre = nombre;
		this.capacidad = capacidad;
		
	}

	public Pecera(String IP, String nombre, int capacidad, float PH, float Temp){
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



	public Date getHoracomida() {
		return horacomida;
	}



	public void setHoracomida(Date horacomida) {
		this.horacomida = horacomida;
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
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
