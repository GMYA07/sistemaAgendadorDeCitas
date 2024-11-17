package www.sistema.agendador.sistemaagendadorcitas.bdd;

import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pacienteDAO {

    public List<PacienteModel> obtenerPacientes(){
        List<PacienteModel> listaPacientes = new ArrayList<>();
        String consulta ="SELECT * FROM paciente";
        try {
            Connection conexion = conexionBdd.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()){
                PacienteModel pacienteRetornar = new PacienteModel();
                pacienteRetornar.setIdPaciente(rs.getString("idPaciente"));
                pacienteRetornar.setDuiPaciente(rs.getString("duiPaciente"));
                pacienteRetornar.setNombresPaciente(rs.getString("nombrePaciente"));
                pacienteRetornar.setApellidosPaciente(rs.getString("apellidosPaciente"));
                pacienteRetornar.setFechaNacPaciente(rs.getDate("fechaNacPaciente"));
                pacienteRetornar.setTelefonoPaciente(rs.getString("telefonoPaciente"));
                pacienteRetornar.setCorreoPaciente(rs.getString("correoPaciente"));
                pacienteRetornar.setPasswordPaciente(rs.getString("passPaciente"));
                pacienteRetornar.setProcedenciaPaciente(rs.getString("procedenciaPaciente"));
                pacienteRetornar.setDescripcionPaciente(rs.getString("descripcionPaciente"));
                pacienteRetornar.setEstadoPaciente(rs.getInt("estadoPaciente"));
                listaPacientes.add(pacienteRetornar);
            }
            return listaPacientes;

        }catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public int insertarPaciente(PacienteModel nuevoPaciente){
        try{
            String sql = "INSERT INTO paciente(idPaciente, duiPaciente, nombrePaciente, apellidosPaciente, fechaNacPaciente, telefonoPaciente, correoPaciente, passPaciente, procedenciaPaciente, descripcionPaciente, estadoPaciente)";
            sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);

            stm.setString(1,nuevoPaciente.getIdPaciente());
            stm.setString(2,nuevoPaciente.getDuiPaciente());
            stm.setString(3,nuevoPaciente.getNombresPaciente());
            stm.setString(4,nuevoPaciente.getApellidosPaciente());
            /*se pasa a tipo date sql*/
            Date fechaSql = new Date(nuevoPaciente.getFechaNacPaciente().getTime());
            stm.setDate(5,fechaSql);
            stm.setString(6,nuevoPaciente.getTelefonoPaciente());
            stm.setString(7,nuevoPaciente.getCorreoPaciente());
            stm.setString(8,nuevoPaciente.getPasswordPaciente());
            stm.setString(9,nuevoPaciente.getProcedenciaPaciente());
            stm.setString(10,nuevoPaciente.getDescripcionPaciente());
            stm.setInt(11,nuevoPaciente.getEstadoPaciente());

            int filasInsertas = stm.executeUpdate();
            return filasInsertas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int modificarPaciente(PacienteModel pacienteModificado ){
        String consulta = "UPDATE paciente SET duiPaciente = ?, nombrePaciente = ?, apellidosPaciente = ?, fechaNacPaciente = ?, telefonoPaciente = ?, correoPaciente = ?, passPaciente = ?, procedenciaPaciente = ?, descripcionPaciente = ?, estadoPaciente = ? WHERE idPaciente = ?";

        try{
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(consulta);

            stm.setString(1, pacienteModificado.getDuiPaciente());
            stm.setString(2, pacienteModificado.getNombresPaciente());
            stm.setString(3, pacienteModificado.getApellidosPaciente());

            java.sql.Date fechaSql = new java.sql.Date(pacienteModificado.getFechaNacPaciente().getTime());
            stm.setDate(4, fechaSql);

            stm.setString(5, pacienteModificado.getTelefonoPaciente());
            stm.setString(6, pacienteModificado.getCorreoPaciente());
            stm.setString(7, pacienteModificado.getPasswordPaciente());
            stm.setString(8, pacienteModificado.getProcedenciaPaciente());
            stm.setString(9, pacienteModificado.getDescripcionPaciente());
            stm.setInt(10, pacienteModificado.getEstadoPaciente());

            // Establecemos el ID para identificar el registro a actualizar
            stm.setString(11, pacienteModificado.getIdPaciente());

            int filasActualizadas = stm.executeUpdate();

            return filasActualizadas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminarPaciente(String idPaciente){
        String consulta = "DELETE FROM paciente WHERE idPaciente = ?";

        try{
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(consulta);

            stm.setString(1,idPaciente);

            // Ejecutar la consulta de eliminación
            int filasEliminadas = stm.executeUpdate();

            return  filasEliminadas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerNombres(String idPaciente){
        String sql = "SELECT nombrePaciente,apellidosPaciente FROM paciente WHERE duiPaciente = ?";

        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);
            // Establecer el valor del parámetro
            stm.setString(1, idPaciente); // Asigna el valor de idDoctor al primer "?"

            // se ejecuta la consulta
            ResultSet rs = stm.executeQuery();

            System.out.println(idPaciente);

            if (rs.next()) {
                System.out.println(rs.getString("nombrePaciente") + " " + rs.getString("apellidosPaciente"));
                return  rs.getString("nombrePaciente") + " " + rs.getString("apellidosPaciente");

            } else {
                return"Ningun Paciente Encontrado";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String obtenerIdPaciente(String duiPaciente){
        String sql = "SELECT idPaciente FROM paciente WHERE duiPaciente = ?";

        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);
            // Establecer el valor del parámetro
            stm.setString(1, duiPaciente); // Asigna el valor de idDoctor al primer "?"

            // se ejecuta la consulta
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return  rs.getString("idPaciente");

            } else {
                return"Ningun Paciente Encontrado";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
