//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public class ACBMain {
	/**Entrar al menu*/
	public ACBMain() {
	}

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		ACBMenu menu = new ACBMenu();
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();
		TeamController teamController = new TeamController(c);
		PlayerController playerController = new PlayerController(c);

		for(int option = menu.mainMenu(); option > 0 && option < 12; option = menu.mainMenu()) {
			switch (option) {
				case 1:
					BorradorTablaEstadisticas.borrarTablaEstadisticas();
					break;
				case 2:
					CreadorTablaEstadisticas.crearTablaEstadisticas();
					break;
				case 3:
					teamController.addTeam();
					break;
				case 4:
					playerController.addPlayer();
					break;
				case 5:
					SeleccionarElementos.main();
					break;
				case 6:
					SeleccionarElementosTextoConcretos.getConnection();
					break;
				case 9:
					break;
				case 7:
					EliminarConjuntoRegistro.main();
					break;
				case 8:
					break;
				case 10:
					System.exit(0);
					break;
				default:
					System.out.println("Introdueixi una de les opcions anteriors");
			}
		}

	}
}
