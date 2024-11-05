package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

public class AdminController {
    @FXML
    private Button botonAdministrarPaciente;
    @FXML
    private Button botonAdministrarDoctores;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonFormPaciente;
    @FXML
    private Button botonFormDoctores;
    @FXML
    private Button botonCerrarSesion;

    Validaciones validar = new Validaciones();

    @FXML
    public void redireccionSistema(ActionEvent event){
        try{
            if (event.getSource() == botonAdministrarPaciente){
                FXMLLoader administrarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarPaciente.load(),936,597);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonAdministrarPaciente.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonAdministrarDoctores) {

                FXMLLoader administrarDoctores = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/doctoresAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarDoctores.load(),946,558);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonAdministrarDoctores.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
                
            } else if (event.getSource() == botonRegresar){
                FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/indexAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(regresar.load(),845,560);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonFormPaciente){
                FXMLLoader formAgregarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formAgregarPaciente.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formAgregarPaciente.load(),966,547);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonFormDoctores) {
                FXMLLoader formAgregarDoc = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formAgregarDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formAgregarDoc.load(),956,537);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
            }else if (event.getSource() == botonCerrarSesion){

                if (Alertas.confirmacionCierreSesion(botonCerrarSesion)){
                    FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/login.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(regresar.load(),824,434);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);

                    nuevoStage.show();
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
    ComboBox<String> selectProcencia;
    @FXML
    TextField inputEspecialidad;
    @FXML
    TextArea inputDescripcionPaciente;

    @FXML
    public void agregarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        if (validar.validarFormAgregarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),selectProcencia.getValue(),inputEspecialidad.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Agregar","El doctor ha sido registrado!");
        }
    }
    @FXML
    public void modificarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        if (validar.validarFormAgregarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),selectProcencia.getValue(),inputEspecialidad.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El doctor ha sido modificado!");
        }
    }
    @FXML
    public void agregarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),selectProcencia.getValue(),inputDescripcionPaciente.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Agregar","El paciente ha sido registrado!");
        }
    }
    @FXML
    public void modificarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),selectProcencia.getValue(),inputDescripcionPaciente.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El paciente ha sido modificado!");
        }
    }
}
