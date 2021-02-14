package tcp;

import java.io.*;
import java.net.*;

public class ServidorTCP {

	public static void main(String[] args) throws IOException {

		try {
			ServerSocket server = new ServerSocket(12345, 10);
			String mensajeBienvenida = "Bienvenido  al  servicio  de manipulaci�n de textos";

			Socket client = server.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);

			out.println(mensajeBienvenida);
			String caracter = in.readLine();// primer caracter
			String cadena;
			String cadenaMod = null;

			while (!caracter.equals("F")) {

				cadena = in.readLine();
				System.out.println("La cadena a transformar es: " + cadena);

				if (caracter.equals("L")) {
					cadenaMod = funcionL(cadena);
				} else if (caracter.equals("B")) {
					cadenaMod = funcionB(cadena);
				} else {
					out.println("Opcion invalida");
				}
				out.println(cadenaMod);
				caracter = in.readLine();
			}

			if (caracter.equals("F")) {

				out.println("VALE");
				System.out.println("Peticion de cierre");
				in.close();
				out.close();
				client.close();

			} else {
				System.out.println("Opcion incorrecta");
			}

		} catch (Exception e) {
			System.out.println("Error");
			System.exit(-1);
		}
	}

	public static String funcionL(String cad) {
		cad = cad.trim().replaceAll("\\s{2,}", " ");
		return cad;
	}

	public static String funcionB(String cad) {

		String cadFinal ="";
	
		String[] cads = cad.split(" ");

		for (int i = cads.length - 1; i >= 0; i--) {
			cadFinal = cadFinal + cads[i] + " ";

		}
		cadFinal = cadFinal.trim().replaceAll("\\s{2,}", " ");
		return cadFinal;
	}

}
