package varTypes;

import java.util.Calendar;

public class Medicion {

	int ID;
	float valor;
	Calendar datetime;
	int tipoMedicion_id;
	int pecera_id;

	public Medicion(float valor, Calendar datetime, int tipoMedicion_id, int pecera_id) {

		this.valor = valor;
		this.datetime = datetime;
		this.tipoMedicion_id = tipoMedicion_id;
		this.pecera_id = pecera_id;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Calendar getDatetime() {
		return datetime;
	}

	public void setDatetime(Calendar datetime) {
		this.datetime = datetime;
	}

	public int getTipoMedicion_id() {
		return tipoMedicion_id;
	}

	public void setTipoMedicion_id(int tipoMedicion_id) {
		this.tipoMedicion_id = tipoMedicion_id;
	}

	public int getPecera_id() {
		return pecera_id;
	}

	public void setPecera_id(int pecera_id) {
		this.pecera_id = pecera_id;
	}

}
