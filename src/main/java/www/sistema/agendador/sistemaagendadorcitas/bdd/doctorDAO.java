package www.sistema.agendador.sistemaagendadorcitas.bdd;

import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class doctorDAO {

    public List<DoctorModel> obtenerDoctores(){
        List<DoctorModel> listaDoctores = new ArrayList<>();
        String consulta ="SELECT * FROM doctor";
        try {
            Connection conexion = conexionBdd.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()){
                DoctorModel doctorRetornar = new DoctorModel();
                doctorRetornar.setIdDoctor(rs.getString("idDoctor"));
                doctorRetornar.setDuiDoctor(rs.getString("duiDoctor"));
                doctorRetornar.setNombresDoctor(rs.getString("nombresDoctor"));
                doctorRetornar.setApellidosDoctor(rs.getString("apellidosDoctor"));
                doctorRetornar.setFechaNacDoctor(rs.getDate("fechaNacDoctor"));
                doctorRetornar.setTelefonoDoctor(rs.getString("telefonoDoctor"));
                doctorRetornar.setCorreoDoctor(rs.getString("correoDoctor"));
                doctorRetornar.setPasswordDoctor(rs.getString("passwordDoc"));
                doctorRetornar.setProcedenciaDoctor(rs.getString("procedenciaDoctor"));
                doctorRetornar.setEspecialidadDoctor(rs.getString("especialidadDoctor"));
                doctorRetornar.setEstadoDoctor(rs.getInt("estadoDoctor"));
                listaDoctores.add(doctorRetornar);
            }

            for (DoctorModel doctor : listaDoctores) {

                System.out.println("ID: " + doctor.getIdDoctor());
                System.out.println("Nombres: " + doctor.getNombresDoctor());
                System.out.println("Apellidos: " + doctor.getApellidosDoctor());
                System.out.println("---");
            }

            return listaDoctores;

        }catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String loginDoctor(String correo, String pass){
        try {
            String sql = "SELECT idDoctor FROM doctor WHERE correoDoctor = ? AND passwordDoc = ?";
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);
            stm.setString(1,correo);
            stm.setString(2,pass);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
               return rs.getString("idDoctor");
            } else {
                return "error";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertarDoctor(DoctorModel nuevoDoctor){
        try {
            int filasInsertadas = 0;
            Connection conexion = conexionBdd.getConnection();
            /*Aqui preparo la Consulta*/
            String consulta = "INSERT INTO doctor(idDoctor,duiDoctor,nombresDoctor,apellidosDoctor,fechaNacDoctor,telefonoDoctor,correoDoctor,passwordDoc,procedenciaDoctor,especialidadDoctor,estadoDoctor)";
            consulta += " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            /*Aqui preparo el stament*/
            PreparedStatement stament = conexion.prepareStatement(consulta);
            /*Ahora asignare los valores a cada una de las partes*/
            stament.setString(1,nuevoDoctor.getIdDoctor());
            stament.setString(2,nuevoDoctor.getDuiDoctor());
            stament.setString(3,nuevoDoctor.getNombresDoctor());
            stament.setString(4,nuevoDoctor.getApellidosDoctor());
            /*se pasa a tipo date sql*/
            Date fechaSql = new Date(nuevoDoctor.getFechaNacDoctor().getTime());
            stament.setDate(5,fechaSql);
            stament.setString(6,nuevoDoctor.getTelefonoDoctor());
            stament.setString(7,nuevoDoctor.getCorreoDoctor());
            stament.setString(8,nuevoDoctor.getPasswordDoctor());
            stament.setString(9,nuevoDoctor.getProcedenciaDoctor());
            stament.setString(10,nuevoDoctor.getEspecialidadDoctor());
            stament.setInt(11,nuevoDoctor.getEstadoDoctor());

            /*Aqui se ejecuta el insert*/
            filasInsertadas = stament.executeUpdate();
            return filasInsertadas;

        }catch (SQLException e) {
            e.printStackTrace();

            return 0;
        }

    }

    public int modificarDoctor(DoctorModel doctorModificar){
        String consulta = "UPDATE doctor SET duiDoctor = ?, nombresDoctor = ?, apellidosDoctor = ?, fechaNacDoctor = ?, "
                + "telefonoDoctor = ?, correoDoctor = ?, passwordDoc = ?, procedenciaDoctor = ?, "
                + "especialidadDoctor = ?, estadoDoctor = ? WHERE idDoctor = ?";
        try{
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(consulta);

            stm.setString(1, doctorModificar.getDuiDoctor());
            stm.setString(2, doctorModificar.getNombresDoctor());
            stm.setString(3, doctorModificar.getApellidosDoctor());

            java.sql.Date fechaSql = new java.sql.Date(doctorModificar.getFechaNacDoctor().getTime());
            stm.setDate(4, fechaSql);

            stm.setString(5, doctorModificar.getTelefonoDoctor());
            stm.setString(6, doctorModificar.getCorreoDoctor());
            stm.setString(7, doctorModificar.getPasswordDoctor());
            stm.setString(8, doctorModificar.getProcedenciaDoctor());
            stm.setString(9, doctorModificar.getEspecialidadDoctor());
            stm.setInt(10, doctorModificar.getEstadoDoctor());

            // Establecemos el ID para identificar el registro a actualizar
            stm.setString(11, doctorModificar.getIdDoctor());

            int filasActualizadas = stm.executeUpdate();

            return filasActualizadas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public int eliminarDoctor(String doctorEliminar){
        String consulta = "DELETE FROM doctor WHERE idDoctor = ?";

        try{
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(consulta);

            stm.setString(1,doctorEliminar);

            // Ejecutar la consulta de eliminación
            int filasEliminadas = stm.executeUpdate();

            return  filasEliminadas;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerTituloIndex(String idDoctor){
        String sql = "SELECT apellidosDoctor FROM doctor WHERE idDoctor = ?";

        try {
            Connection conexion = conexionBdd.getConnection();
            PreparedStatement stm = conexion.prepareStatement(sql);
            // Establecer el valor del parámetro
            stm.setString(1, idDoctor); // Asigna el valor de idDoctor al primer "?"

            // se ejecuta la consulta
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return  rs.getString("apellidosDoctor");
            } else {
                System.out.println("No se encontró el doctor con ese ID");
                return "";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
