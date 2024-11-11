package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class formAgregarPacienteController {

    public void initialize(){
        cargarCombobox();
    }
    Validaciones validar = new Validaciones();

    @FXML
    private Button botonRegresar;
    @FXML
    private Button doctorPaciente;

    @FXML
    private ComboBox selectProcencia;

    ObservableList<ProcedenciaModel> observableListProcedencia;

    @FXML
    public void redireccionSistema(ActionEvent event){
        try{
            if (event.getSource() == botonRegresar){
                FXMLLoader administrarDoctores = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarDoctores.load(),1465,798);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();
                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if(event.getSource() == doctorPaciente){
                FXMLLoader administrarDoctores = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarDoctores.load(),1465,798);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();
                // Cerrar el formulario actual
                Stage actualStage = (Stage) doctorPaciente.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarCombobox(){
        try {
            procedenciaDAO lista = new procedenciaDAO();
            List<ProcedenciaModel> listaPro = lista.obtenerProcedencia();
            observableListProcedencia = FXCollections.observableArrayList(listaPro);
            selectProcencia.setItems(observableListProcedencia);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*AQUI SE HACE LA PARTE DEL CRUD OSEA INSERTAR, MODIFICAR Y ELIMINAR*/
    @FXML
    TextField inputDui;
    @FXML
    TextField inputTelefono;
    @FXML
    DatePicker inputFecha;
    @FXML
    TextField inputNombres;
    @FXML
    TextField inputApellidos;
    @FXML
    TextField inputCorreo;
    @FXML
    TextField inputPass;
    @FXML
    TextArea inputDescripcionPaciente;

    pacienteDAO daoPaciente = new pacienteDAO();
    @FXML
    public void agregarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();
        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputDescripcionPaciente.getText())){
            /*creamos un nuevo paciente*/
            PacienteModel nuevoPaciente = new PacienteModel();
            nuevoPaciente.setIdPaciente(Utilidades.generadorId(1));
            nuevoPaciente.setDuiPaciente(inputDui.getText());
            nuevoPaciente.setNombresPaciente(inputNombres.getText());
            nuevoPaciente.setApellidosPaciente(inputApellidos.getText());
            nuevoPaciente.setCorreoPaciente(inputCorreo.getText());
            nuevoPaciente.setTelefonoPaciente(inputTelefono.getText());
            nuevoPaciente.setPasswordPaciente(Utilidades.generarHash(inputPass.getText()));
            nuevoPaciente.setProcedenciaPaciente(seleccion.getIdProcedencia());
            nuevoPaciente.setDescripcionPaciente(inputDescripcionPaciente.getText());
            nuevoPaciente.setEstadoPaciente(0);
            /*ahora convertiremos el valor localDate a date*/
            Date fechaNacPac =  Date.from(inputFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            nuevoPaciente.setFechaNacPaciente(fechaNacPac);

            if (daoPaciente.insertarPaciente(nuevoPaciente) > 0){
                alerta.alertaCorrecto("INFORMATION","Correcto al Agregar","El paciente ha sido registrado!");
                redireccionSistema(event);
            }else {
                alerta.alertaError("ERROR","Error al Agregar","El paciente no ha sido registrado");
            }



        }
    }
}
