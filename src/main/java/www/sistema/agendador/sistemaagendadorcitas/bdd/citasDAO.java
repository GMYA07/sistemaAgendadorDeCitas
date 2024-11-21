package www.sistema.agendador.sistemaagendadorcitas.bdd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.citasModel;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;

import java.sql.*;
import java.text.SimpleDateFormat;
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
    public ObservableList<ObservableList<String>> obtenerCitasExpiente(String idPaciente){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        String sql = "SELECT c.idCita,CONCAT(d.nombresDoctor,' ',d.apellidosDoctor) as NombrePaciente,c.fechaCita,c.descripcionCita FROM paciente as p ";
               sql += "INNER JOIN expedientecitas as e on p.idPaciente = e.idPaciente_Exp ";
               sql += "INNER JOIN citas as c on e.idExpediente = c.idExpediente_Cita ";
               sql += "INNER JOIN doctor as d on c.idDoctor_Cita = d.idDoctor ";
               sql +="WHERE p.idPaciente = ? AND c.estadioCita = ?";

        try{
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1,idPaciente);
            stmt.setInt(2,1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(rs.getString("c.idCita"));
                fila.add(rs.getString("NombrePaciente"));
                // Crear un formateador
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                // Convertir a String
                String fechaString = formatter.format(rs.getDate("c.fechaCita"));
                fila.add(fechaString);
                fila.add(rs.getString("c.descripcionCita"));
                datosTabla.add(fila);
            }

            return datosTabla;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<ObservableList<String>> obtenerCitasPasadas(String idDoctor){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        String sql = "SELECT c.idCita,CONCAT(p.nombrePaciente,' ',apellidosPaciente) as 'NombrePaciente' ,p.duiPaciente,p.descripcionPaciente,c.fechaCita,c.horaCita,c.descripcionCita FROM citas as c ";
               sql+="INNER JOIN expedientecitas as ex on c.idExpediente_Cita = ex.idExpediente ";
               sql+="INNER JOIN paciente as p on ex.idPaciente_Exp = p.idPaciente ";
               sql+="WHERE c.idDoctor_Cita = ? AND c.estadioCita = ?";

        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1,idDoctor);
            stmt.setInt(2,1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(rs.getString("c.idCita"));
                fila.add(rs.getString("NombrePaciente"));
                // Crear un formateador
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                // Convertir a String
                String fechaString = formatter.format(rs.getDate("c.fechaCita"));
                fila.add(fechaString);
                // Crear un formateador
                SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
                // Convertir a String
                String horaString = formatter2.format(rs.getTime("c.horaCita"));
                fila.add(rs.getString("p.duiPaciente"));
                fila.add(rs.getString("p.descripcionPaciente"));
                fila.add(rs.getString("c.descripcionCita"));
                fila.add(horaString);
                datosTabla.add(fila);
            }

            return datosTabla;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public ObservableList<ObservableList<String>> obtenerCitasPendientes(String idDoctor){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        String sql = "SELECT c.idCita,CONCAT(p.nombrePaciente,' ',apellidosPaciente) as 'NombrePaciente' ,p.idPaciente,c.fechaCita,c.horaCita FROM citas as c ";
        sql+="INNER JOIN expedientecitas as ex on c.idExpediente_Cita = ex.idExpediente ";
        sql+="INNER JOIN paciente as p on ex.idPaciente_Exp = p.idPaciente ";
        sql+="WHERE c.idDoctor_Cita = ? AND c.estadioCita = ?";

        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1,idDoctor);
            stmt.setInt(2,0);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(rs.getString("c.idCita"));
                fila.add(rs.getString("NombrePaciente"));
                // Crear un formateador
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                // Convertir a String
                String fechaString = formatter.format(rs.getDate("c.fechaCita"));
                fila.add(fechaString);
                // Crear un formateador
                SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
                // Convertir a String
                String horaString = formatter2.format(rs.getTime("c.horaCita"));
                fila.add(rs.getString("p.idPaciente"));
                fila.add(horaString);
                datosTabla.add(fila);
            }

            return datosTabla;
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
    public int finalizarCita(String descripCita,String idCita){
        String sql = "UPDATE citas SET descripcionCita = ? , estadioCita = ? WHERE idCita = ?";
        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1,descripCita);
            stmt.setInt(2,1);
            stmt.setString(3,idCita);
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
