package conexionMicro;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import VarTypes.Pecera;



public class InterfazMicro {
	
	private final int PORT = 4444;
	
	private String respuesta;
	
	public static final int OK = 1;
	public static final int NO_CONEXION = 2;
	public static final int NO_RESPUESTA = 3;
	public static final int CAMBIO_NO_REALIZADO = 4;
	public static final int ERROR_DESCONOCIDO = 5;
	
	@SuppressWarnings("deprecation")
	int setDateTime(final Calendar c, Pecera p) {
		final ClienteMicro cliente = new ClienteMicro(p.getIP(), PORT);
		respuesta = null;
		
		Thread t = new Thread( new Runnable() {
			@Override
			public void run() {
				respuesta = cliente.enviarComando("set time " + c.getTimeInMillis()/1000 +";");				
			}
		});
		
		t.start();
		long start = Calendar.getInstance().getTimeInMillis();
		while(t.isAlive() && Calendar.getInstance().getTimeInMillis() - start < 2000 ){}
		
		if(t.isAlive()){
			t.stop();
			return NO_RESPUESTA;
		}
		if (respuesta == null) return NO_CONEXION;
		if(respuesta.startsWith("time")) {
			System.out.println(respuesta);
			return OK;
		}
		return ERROR_DESCONOCIDO;
	}
}
