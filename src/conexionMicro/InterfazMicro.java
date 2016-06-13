package conexionMicro;

import java.util.Calendar;
import java.util.Date;

import conexionDB.DAOPecera;
import varTypes.Pecera;
import varTypes.RegComida;

public class InterfazMicro {

	private final int PORT = 4444;

	private String respuesta;
	private ClienteMicro cliente;

	public static final int OK = 1;
	public static final int NO_CONEXION = 2;
	public static final int NO_RESPUESTA = 3;
	public static final int CAMBIO_NO_REALIZADO = 4;
	public static final int ERROR_DESCONOCIDO = 5;
	
	public static final int HOUR_MILIS = 3600000;
	
	public InterfazMicro(Pecera p) {
		cliente = new ClienteMicro(p.getIP(), PORT);
	}

	public boolean isDisponible() {
		return ClienteMicro.DISPONIBLE;
	}

	@SuppressWarnings("deprecation")
	public int setDateTime(final Calendar c) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set time " + c.getTimeInMillis() / 1000 + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
	
		if (respuesta.startsWith("time")) return OK;

		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public Calendar getDateTime() {
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get time;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {
		}

		if (t.isAlive()) {
			t.stop();
			return null;
		}
		
		if (respuesta == null)
			return null;
		
		if (respuesta.startsWith("time")) {
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Long f = Long.parseLong(sa[1]);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis((long) (f * 1000));
			return c;
		}
		
		return null;
	}

	@SuppressWarnings("deprecation")
	public int setFeedTime(final Calendar c) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set feedtime " + (c.getTimeInMillis()) / 1000 + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("feedtime")) return OK;

		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public Calendar getFeedTime() {
		
		respuesta = null;
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get feedtime;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return null;
		}
		
		if (respuesta == null) return null;

		if (respuesta.startsWith("feedtime")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Long f = Long.parseLong(sa[1]);
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis((long) (f * 1000));
			
			return c;
		}
		
		return null;
	}

	@SuppressWarnings("deprecation")
	public int setMeals(final int meals) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set meals " + meals + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("meals")) 	return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public int getMeals() {
	
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get meals;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("meals")) {
		
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Integer i = Integer.parseInt(sa[1]);
			
			return (int) i;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setFishNo(final int fishNo) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set fishNo " + fishNo + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("fishno"))	return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public int getFishNo() {
		
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get fishno;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("fishno")) {
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Integer i = Integer.parseInt(sa[1]);
		
			return (int) i;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public float getPh() {
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get ph;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {
		}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("ph")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			
			return f;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public float getPhOffset() {
		
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get phoffset;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("phoffset")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			
			return f;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setPhOffset(final float phOffset) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set phoffset " + phOffset + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("phoffset")) return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public float getPhMax() {
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get phmax;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("phmax")) {
		
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			
			return f;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setPhMax(final float phMax) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set phmax " + phMax + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("phmax")) return OK;
	
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public float getPhMin() {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get phmin;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null)	return -1;
		
		if (respuesta.startsWith("phmin")) {
		
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			return f;
		
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setPhMin(final float phMin) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set phmin " + phMin + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null)	return NO_CONEXION;
		
		if (respuesta.startsWith("phmin")) 	return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public float getTemp() {
		
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get t;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("t")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			
			return f;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public float getTempMin() {
		
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get tmin;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		
		if (respuesta == null)	return -1;
		
		if (respuesta.startsWith("tmin")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
			return f;
		
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setTempMin(final float tempMin) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set tmin " + tempMin + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null)	return NO_CONEXION;
		
		if (respuesta.startsWith("tmin"))	return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public float getTempMax() {
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get tmax;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {
		}

		if (t.isAlive()) {
			t.stop();
			return -1;
		}
		if (respuesta == null) return -1;
		
		if (respuesta.startsWith("tmax")) {
		
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Float f = Float.parseFloat(sa[1]);
		
			return f;
		}
		
		return -1;
	}

	@SuppressWarnings("deprecation")
	public int setTempMax(final float tempMax) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set tmax " + tempMax + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null) return NO_CONEXION;
		
		if (respuesta.startsWith("tmax")) return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public RegComida getFeedLogEntry() {
	
		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("get feedlog;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return null;
		}
		if (respuesta == null)	return null;
		
		if (respuesta.startsWith("fed")) {
			
			respuesta = respuesta.replace(";", "");
			String[] sa = respuesta.split(" ");
			Long l = Long.parseLong(sa[2]);
			RegComida rc = null;

			Pecera p = null;
			p = DAOPecera.buscarPorIP(cliente.servidor);
			
			if (p != null) {
			
				rc = new RegComida(p.getComida_id(), p.getID(), new Date(l * 1000));
			}
			
			return rc;
		}
		
		return null;
	}

	@SuppressWarnings("deprecation")
	public int feed(final int times) {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("feed " + times + ";");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null)	return NO_CONEXION;
		
		if (respuesta.startsWith("fed")) return OK;
		
		return ERROR_DESCONOCIDO;
	}

	@SuppressWarnings("deprecation")
	public int reset() {

		respuesta = null;

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("reset;");
			}
		});

		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		
		while (t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000) {}

		if (t.isAlive()) {
			t.stop();
			return NO_RESPUESTA;
		}
		
		if (respuesta == null)	return NO_CONEXION;
		
		if (respuesta.startsWith("reset")) 	return OK;
		
		return ERROR_DESCONOCIDO;
	}

}
