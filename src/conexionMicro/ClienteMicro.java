package conexionMicro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMicro {
	String servidor;
	int puerto;

	public ClienteMicro(String servidor, int puerto){
		this.servidor = servidor;
		this.puerto = puerto;
	}

	public String enviarComando(String comando){
		String respuesta = "";
		Socket client = null;
		if(!comando.endsWith(";")) comando = comando + ";";
		try {
			client =  new Socket(servidor, puerto);
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			
			byte[] b = comando.getBytes();
			for(byte by : b){
				out.write(by);		
			}
						
			char c;
			do {
				 c = (char) in.read();
				 respuesta = "" + respuesta + c;
			 }while(c != ';');
			 client.close();
		} catch (Exception e) {
			respuesta = null;
			try {
				client.close();
			} catch (Exception e1 ) {
			}
		}	
		return respuesta;

	}


	public static void main(String[] args) {
		while(true){
			ClienteMicro c = new ClienteMicro("192.168.1.15", 4444);
			Scanner s = new Scanner(System.in);
			while(true){
				System.out.println(c.enviarComando(s.nextLine()));
			}
		}
	}





}
