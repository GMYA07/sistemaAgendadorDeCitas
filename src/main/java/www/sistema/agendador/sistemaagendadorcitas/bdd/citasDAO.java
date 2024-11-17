package www.sistema.agendador.sistemaagendadorcitas.bdd;

import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.citasModel;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class citasDAO {

    public List<citasModel> obtenerCitasDoctor(String idDoctor){
        List<citasModel> listCitasDoc = new ArrayList<>();

        try {
            String sql = "SELECT * FROM citas WHERE idDoctor_Cita = ? AND estadioCita = ?";
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, idDoctor);
            stmt.setInt(2,0);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                citasModel citaDoc = new citasModel();
                citaDoc.setIdExpedienteCita(rs.getString("idCita"));
                citaDoc.setIdExpedienteCita(rs.getString("idDoctor_Cita"));
                citaDoc.setFechaCita(rs.getDate("fechaCita"));
                citaDoc.setHoraCita(rs.getTime("horaCita").toString());
                citaDoc.setDescripcionCita(rs.getString("descripcionCita"));
                citaDoc.setEstadoCita(rs.getInt("estadioCita"));
                listCitasDoc.add(citaDoc);
            }
            return listCitasDoc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int insertarCita(citasModel nuevaCita){
        String sql = "INSERT INTO citas(idCita,idExpediente_Cita,idDoctor_Cita,fechaCita,horaCita,descripcionCita,estadioCita) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);
            stm.setString(1,nuevaCita.getIdCita());
            stm.setString(2,nuevaCita.getIdExpedienteCita());
            stm.setString(3,nuevaCita.getIdDoctor_Cita());
            /*se pasa a tipo date sql*/
            Date fechaSql = new Date(nuevaCita.getFechaCita().getTime());
            stm.setDate(4,fechaSql);
            stm.setTime(5, Utilidades.convertirHora(nuevaCita.getHoraCita()));
            stm.setString(6,"-");
            stm.setInt(7,0);

            int filasInsertas = stm.executeUpdate();
            return filasInsertas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
