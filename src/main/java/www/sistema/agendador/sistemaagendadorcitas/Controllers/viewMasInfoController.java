package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;

public class viewMasInfoController {
    private String procedencia;
    private Integer estado;

    @FXML
    private Label labelEstado;
    @FXML
    private Label labelProcedencia;

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
        procedenciaDAO proDAO = new procedenciaDAO();
        labelProcedencia.setText(proDAO.obtenerProcedenciaExacta(procedencia).getProcedencia());
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;

        if (estado == 0){
            labelEstado.setText("Estado: Activo");
        }else {
            labelEstado.setText("Estado: Desactivado");
        }

    }
}
