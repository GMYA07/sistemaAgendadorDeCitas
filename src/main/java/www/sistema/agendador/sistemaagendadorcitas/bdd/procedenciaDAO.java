package www.sistema.agendador.sistemaagendadorcitas.bdd;

import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class procedenciaDAO {

    public List<ProcedenciaModel> obtenerProcedencia(){
        List<ProcedenciaModel> listaProcedencia = new ArrayList<>();
        String consulta = "SELECT pr.idProcedencia as 'idProcedencia',d.departamento as 'departamento', m.municipio as 'municipio' FROM procedencia as pr INNER JOIN departamento as d on pr.idDepartamento = d.idDepartamento INNER JOIN municipio as m on pr.idMunicipio = m.idMunicipio ";

        try {
            Connection conexion = conexionBdd.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()){
                ProcedenciaModel procedenciaRetornar = new ProcedenciaModel();
                procedenciaRetornar.setIdProcedencia(rs.getString("idProcedencia"));
                procedenciaRetornar.setProcedencia(rs.getString("departamento") +","+ rs.getString("municipio"));
                listaProcedencia.add(procedenciaRetornar);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return listaProcedencia;
    }
}
