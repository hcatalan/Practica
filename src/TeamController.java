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

public class TeamController {
    private Connection connection;

    /**Añadir equipos*/
    public TeamController(Connection connection) {
        this.connection = connection;
    }

    public void showTeams() throws SQLException, IOException {
        Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM team");

        while(rs.next()) {
            PrintStream var10000 = System.out;
            String var10001 = rs.getString("nombre");
            var10000.println("Nombre: " + var10001 + " Ciudad: " + rs.getString("ciudad") + " Posicion: " + rs.getString("posicion") + " Victorias: " + rs.getString("victorias") + " Empates: " + rs.getString("empates") + " Derrotas: " + rs.getString("derrotas"));
        }

        rs.close();
        st.close();
    }

    /**Mirar equipo que estan en la base de datos*/
    public void showTeamPlayers() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM team");

        PrintStream var10000;
        String var10001;
        while(rs.next()) {
            var10000 = System.out;
            var10001 = rs.getString("type");
            var10000.println(var10001 + ": " + rs.getString("Nombre: " + var10001 + " Ciudad: " + rs.getString("ciudad") + " Posicion: " + rs.getString("posicion") + " Victorias: " + rs.getString("victorias") + " Empates: " + rs.getString("empates") + " Derrotas: " + rs.getString("derrotas")));
        }

        System.out.println("\nSeleccioni un dels equips llistats per veure la seva plantilla. ");
        String equip = br.readLine();
        rs = st.executeQuery("SELECT * FROM player WHERE team_name LIKE '" + equip + "'");

        while(rs.next()) {
            var10000 = System.out;
            var10001 = rs.getString(1);
            var10000.println("num: " + var10001 + " Player: " + rs.getString(2) + "  " + rs.getString(3) + " birthdate:  " + String.valueOf(rs.getDate(4)) + " sex: " + rs.getString(5) + " hight: " + rs.getInt(6) + " team: " + rs.getString(7) + " mvp_total: " + rs.getInt(8));
        }

        rs.close();
        st.close();
        br.close();
    }

    /**Añador equipo*/
    public void addTeam() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserta nom: ");
        String nom = br.readLine();
        System.out.println("Inserta posicion: ");
        String posicion = br.readLine();
        System.out.println("Inserta victorias: ");
        String victorias = br.readLine();
        System.out.println("Inserta empate: ");
        String empate = br.readLine();
        System.out.println("Inserta derrotas: ");
        String derrotas = br.readLine();
        String sql = "INSERT INTO team (nom, posicion, victorias, empate, derrotas) VALUES (?,?,?,?,?)";
        PreparedStatement pst = this.connection.prepareStatement(sql);
        pst.setString(1, nom);
        pst.setString(2, posicion);
        pst.setString(3, victorias);
        pst.setString(4, empate);
        pst.setString(5, derrotas);
        pst.executeUpdate();
    }
}
