package edu.ieu.sockets.ProyectoNumerosYPotencias;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorExponentes {

	public void iniciar() {
		//declaramos el servidor 
		boolean finalizar = false;
        ServerSocket servidor;
        
        try {
        	//le pasamos el puerto
        servidor = new ServerSocket(6000);
        System.out.println("Servidor escuchando en el puerto 6000");
        
        //aqui el servidor se queda a la espera hasta que el un cliente se conecte
        Socket socketCliente = servidor.accept();
        
        //instanciamos los objetos de entrada y de salida 
        DataInputStream in= new DataInputStream(socketCliente.getInputStream());
        DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());
        do {
        	//declaramos una variable de tipo double para leer los datos de la entrada
        Double numero =0d;
        numero = in.readDouble();
        //imprimimos el numero que nos envio el cliente para verificar si todo handa bien hasta aqui
        System.out.println("El numero recibido del cliente:"+ numero);

       out.writeDouble(numero*numero);
       System.out.println("Se mando el numero²:"+ numero*numero);

       out.writeDouble(numero*numero*numero);
       System.out.println("Se mando el numero³:"+ numero*numero*numero);
        if (numero.equals(0.0)) {
        finalizar= true;
        System.out.println("EL SERVIDOR SE APAGANDO.... :");
        }
        }while(!finalizar);
        in.close();
        out.close();
        socketCliente.close();
        servidor.close();
    }catch (IOException e) {
        	e.printStackTrace();
        }
	}
	public static void main(String args[]) {
		ServidorExponentes servidor = new ServidorExponentes();
		servidor.iniciar();
} 
}
