package www.sistema.agendador.sistemaagendadorcitas.bdd;

import www.sistema.agendador.sistemaagendadorcitas.Models.expedienteCitaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class expedientecitasDAO {

    public int verificarExistencia(String duiPaciente){
        try {
            String sql = "SELECT p.idPaciente FROM expedientecitas as exp INNER JOIN paciente as p on p.idPaciente = exp.idPaciente_Exp WHERE p.idPaciente = ?";

            try {
                Connection conexion = conexionBdd.getConnection();
                PreparedStatement stm = conexion.prepareStatement(sql);
                // Establecer el valor del parámetro
                stm.setString(1, duiPaciente); // Asigna el valor de idDoctor al primer "?"

                // se ejecuta la consulta
                ResultSet rs = stm.executeQuery();

                System.out.println(duiPaciente);

                if (rs.next()) {
                    return  1;
                } else {
                    return 0;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertarExistencia(expedienteCitaModel nuevoExpediente){
        String sql = "INSERT INTO expedientecitas(idExpediente,idPaciente_Exp) VALUES(?,?)";
        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);

            stm.setString(1,nuevoExpediente.getIdExpediente());
            stm.setString(2,nuevoExpediente.getIdPaciente_Exp());

            int filasInsertas = stm.executeUpdate();
            return filasInsertas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerIdExpediente(String idPaciente){
        try {
            String sql = "SELECT idExpediente FROM expedientecitas WHERE idPaciente_Exp = ?";

            try {
                Connection conexion = conexionBdd.getConnection();
                PreparedStatement stm = conexion.prepareStatement(sql);
                // Establecer el valor del parámetro
                stm.setString(1, idPaciente); // Asigna el valor de idDoctor al primer "?"

                // se ejecuta la consulta
                ResultSet rs = stm.executeQuery();

                System.out.println(idPaciente);

                if (rs.next()) {
                    return  rs.getString("idExpediente");
                } else {
                    return "-";
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
