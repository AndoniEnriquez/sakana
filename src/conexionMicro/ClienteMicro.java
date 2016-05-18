package conexionMicro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteMicro {
	String servidor;
	int puerto;
	
	public ClienteMicro(String servidor, int puerto){
		this.servidor = servidor;
		this.puerto = puerto;
	}
	
	public String enviarComando(String comando){
		String respuesta = "";
		char c;
		if(!comando.endsWith(";")) comando = comando + ";";
		 try {
			Socket client = new Socket(servidor, puerto);
			 OutputStream outToServer = client.getOutputStream();
			 DataOutputStream out = new DataOutputStream(outToServer);
			 
			 char[] ca = comando.toCharArray();
			 for(char a : ca){
				 out.writeInt(a);
			 }
			 InputStream inFromServer = client.getInputStream();
			 DataInputStream in = new DataInputStream(inFromServer);
			 while((c = (char) in.read()) != ';'){
				 respuesta = "" + respuesta + c;
			 }
			 respuesta = respuesta + ";";
			 client.close();
		} catch (Exception e) {
			respuesta = null;
		}	
		return respuesta;
		
	}
	
	
	
	
	
}
