package conexionMicro;

public class SincronizadorPeceras extends Thread {

	

	public static void main(String[] args) {
		
	}
	
	@Override
	public void run() {
		while(true) {
			
			sincronizarHora();
			sincronizarLogComidas();
			sincronizarDatosSensores();
			sincronizar
			
			
			try {
				sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
