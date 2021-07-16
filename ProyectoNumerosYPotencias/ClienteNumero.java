package edu.ieu.sockets.ProyectoNumerosYPotencias;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClienteNumero {

	private BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
   
	private Socket socket;
	private DataOutputStream out; 
	private DataInputStream in;	
	
	public void conectar() {
Double numero = 0d;


try {
	socket = new Socket ("localhost",6000);
	in = new DataInputStream(socket.getInputStream());
	out = new DataOutputStream(socket.getOutputStream());
	System.out.println("conectado al servidor en el puerto 6000");
	do {
		System.out.println("\nDigite un numero para elevarlo al ²y³:");
		numero =Double.parseDouble(entradaTeclado.readLine()) ;
		
		out.writeDouble(numero);
		System.out.println("EL NUMERO SE ENVIO CON EXITO: " + numero);
		Double respuesta = in.readDouble();
		System.out.println("NUMERO² RECIBIDO DEL SERVIDOR:"+ respuesta);
		

		Double respuesta2 = in.readDouble();
		System.out.println("NUMERO³ RECIBIDO DEL SERVIDOR:"+ respuesta2);
		
		
	}while(!numero.equals(0.0) );
	in.close();
	out.close();
	socket.close();
	System.out.println("SE FINALIZO LA CONEXION....");
} catch (IOException e) {
	e.printStackTrace();
}
		}
		
		public static void main (String args[]) {
			ClienteNumero cliente = new ClienteNumero();
			cliente.conectar();
		}
}
