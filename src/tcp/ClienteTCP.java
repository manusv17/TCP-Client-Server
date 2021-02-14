package tcp;

import java.io.*;
import java.net.*;

public class ClienteTCP {

	public static void main(String[] args) throws IOException {
		try {
			Socket echoSocket = new Socket("127.0.0.1", 12345);

			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

			//out.println("Soy el cliente y esto es un envio");
			System.out.println("Mensaje recibido del servidor: " + in.readLine());

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("\nIntroduzca caracter: ");

			String caracter = br.readLine();
			out.print(caracter + "\r\n");
			out.flush();

			while (!caracter.equals("F")) {

				System.out.println("Introduzca cadena: ");

				String linea = br.readLine();

				out.println(linea);

				System.out.println("Respuesta del servidor: " + in.readLine());

				System.out.println("\nIntroduzca caracter: ");

				caracter = br.readLine();
				out.print(caracter + "\r\n");
				out.flush();
			}

			String f = in.readLine();

			if (f.equals("VALE")) {
				System.out.println("Se cierra la conexion");

				out.close();
				in.close();

				echoSocket.close();
			} else {
				System.out.println("Error al cerrar la conexion");
			}

		} catch (Exception e) {
			System.err.println("Error");
			System.exit(1);
		}
	}
}
