package varTypes;

import java.util.Date;

public class RegComida {

	int id;
	int comida_id, pecera_id;
	Date datetime;

	public RegComida(int comida_id, int pecera_id, Date datetime) {

		this.comida_id = comida_id;
		this.pecera_id = pecera_id;
		this.datetime = datetime;
	}

	public RegComida(int id, int comida_id, int pecera_id, Date datetime) {

		this.id = id;
		this.comida_id = comida_id;
		this.pecera_id = pecera_id;
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComida_id() {
		return comida_id;
	}

	public void setComida_id(int comida_id) {
		this.comida_id = comida_id;
	}

	public int getPecera_id() {
		return pecera_id;
	}

	public void setPecera_id(int pecera_id) {
		this.pecera_id = pecera_id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
