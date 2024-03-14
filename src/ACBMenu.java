//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACBMenu {
	private int option;

	public ACBMenu() {
	}

	/**Acceder al menu*/
	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {
			System.out.println(" \nMENU PRINCIPAL \n");
			System.out.println("1. Borrar tablas y su informacion");
			System.out.println("2. Crear Tabla");
			System.out.println("3. Crear Equipo");
			System.out.println("4. Crear Jugador");
			System.out.println("6. Seleccionar elementos");
			System.out.println("7.Eliminar Conjunto Registro");
			System.out.println("10. Sortir");
			System.out.println("Esculli opció: ");

			try {
				this.option = Integer.parseInt(br.readLine());
			} catch (IOException | NumberFormatException var3) {
				System.out.println("valor no vàlid");
				var3.printStackTrace();
			}
		} while(this.option != 1 && this.option != 2 && this.option != 3 && this.option != 4 && this.option != 5 && this.option != 6 && this.option != 7 && this.option != 8 && this.option != 9 && this.option != 10);

		return this.option;
	}
}
