//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerController {
    private Connection connection;

    public PlayerController(Connection connection) {
        this.connection = connection;
    }

    /**Añadir Jugador*/
    public void addPlayer() throws SQLException, NumberFormatException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserta nombre: ");
        String nombre = br.readLine();
        System.out.println("Inserta apellidos: ");
        String apellidos = br.readLine();
        System.out.println("Inserta dorsal: ");
        int dorsal = Integer.parseInt(br.readLine());
        System.out.println("Inserta nacimiento: ");
        String data = br.readLine();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date naixement = df.parse(data);
        java.sql.Date sDate = new java.sql.Date(naixement.getTime());
        System.out.println("Inserta posicion: ");
        String posicion = br.readLine();
        System.out.println("Inserta valor de mercado: ");
        int vmercado = Integer.parseInt(br.readLine());
        System.out.println("Inserta equipo: ");
        String equipo = br.readLine();
        String sql = "INSERT INTO player(nombre,apellidos,dorsal,nacimiento,posicion,valormercado,equipo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = this.connection.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setString(2,apellidos);
        pst.setInt(3, dorsal);
        pst.setDate(4, sDate);
        pst.setString(5, posicion);
        pst.setInt(6, vmercado);
        pst.setString(7, equipo);
        pst.executeUpdate();
    }

    }

