package VarTypes;

import java.util.Date;

public class Pecera {

	int ID;

	String IP;
	String nombre;
	int capacidad;
	int comida_id;
	Date horacomida;



	public Pecera(String IP, String nombre, int capacidad, int comida_id){
		this.IP = IP;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.comida_id = comida_id;
	}	
	
	public int getComida_id() {
		return comida_id;
	}

	public void setComida_id(int comida_id) {
		this.comida_id = comida_id;
	}

	public Pecera(int id, String IP, String nombre, int capacidad,  int comida_id){
		this.ID = id;
		this.IP = IP;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.comida_id = comida_id;
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
