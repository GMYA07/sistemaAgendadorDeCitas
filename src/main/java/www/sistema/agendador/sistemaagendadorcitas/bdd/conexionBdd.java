package www.sistema.agendador.sistemaagendadorcitas.bdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionBdd {
    private static final String URL ="jdbc:mysql://localhost:3306/sistemagendacioncitas";
    private static final String USER = "root";
    private static final String PASSWORD ="Ardillas_07";
    private static Connection conexion;

    public static Connection getConnection() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión a la base de datos establecida");
            } catch (Exception e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void closeConnection() {
        if (conexion!= null) {
            try {
                conexion.close();
                System.out.println("Conexión a la base de datos cerrada");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
            }
        }
    }

}
