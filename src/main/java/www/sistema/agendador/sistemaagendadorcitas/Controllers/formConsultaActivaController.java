package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.citasDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

public class formConsultaActivaController {

    Validaciones validar = new Validaciones();
    @FXML
    private TextArea descripcionCita;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonFinalizar;
    @FXML
    private Button botonExpediente;
    @FXML
    private String citaFinalizar;
    @FXML
    private String pacienteCita;
    @FXML
    private Label labelDui;
    @FXML
    private Label labelNamePaciente;
    @FXML
    private Label labelProcedencia;
    @FXML
    private Label labelDescripPaciente;
    @FXML
    private Label labelEdad;



    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonRegresar){
                FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/consultasPendientes.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(regresar.load(),728,571);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonFinalizar) {
                FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/consultasPendientes.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(regresar.load(),728,571);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonExpediente) {
                FXMLLoader formViewExp = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/tablaVerExpediente.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formViewExp.load(),840,551);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                /*esto nos ayudara a mandar el objeto*/
                tablaVerExpedienteController controller = formViewExp.getController();
                controller.setLlenarExpediente(this.getPacienteCita());
                nuevoStage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void finalizarCita(ActionEvent event){
        Alertas alerta = new Alertas();

        if (validar.valiarFormFinalizarCita(descripcionCita.getText())){
            citasDAO citDao = new citasDAO();
            if (citDao.finalizarCita(descripcionCita.getText(),this.citaFinalizar) > 0){
                alerta.alertaCorrecto("INFORMATION","Correcto al finalizar","La cita ha sido finalizada!");
                redireccionSistema(event);
            }else {
                alerta.alertaError("ERROR","Error al finalizar","La cita no ha sido finalizada!");
            }

        }
    }

    public String getCitaFinalizar() {
        return citaFinalizar;
    }

    public void setCitaFinalizar(String citaFinalizar) {
        this.citaFinalizar = citaFinalizar;
    }

    public String getPacienteCita() {
        return pacienteCita;
    }

    public void setPacienteCita(String pacienteCita) {
        this.pacienteCita = pacienteCita;

        /*con esto seteamos tambien la targeta de informacion del usuario*/
        PacienteModel infoPaciente;
        ProcedenciaModel procedenciaPac;
        pacienteDAO pDao = new pacienteDAO();
        procedenciaDAO prDAO= new procedenciaDAO();
        infoPaciente = pDao.obtenerPaciente(pacienteCita);
        procedenciaPac = prDAO.obtenerProcedenciaExacta(infoPaciente.getProcedenciaPaciente());

        labelDui.setText("Dui Paciente: "+infoPaciente.getIdPaciente());
        labelNamePaciente.setText("Paciente: "+infoPaciente.getNombresPaciente()+" "+infoPaciente.getApellidosPaciente());
        labelDescripPaciente.setText("Descripcion Paciente: "+infoPaciente.getDescripcionPaciente());
        labelProcedencia.setText("Procedencia: "+procedenciaPac.getProcedencia());
        labelEdad.setText("Edad paciente: " + Utilidades.calcularEdad(infoPaciente.getFechaNacPaciente()));
    }
}
