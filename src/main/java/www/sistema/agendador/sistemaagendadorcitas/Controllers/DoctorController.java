package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.conexionBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DoctorController {

    @FXML
    private Button botonIniciarSesion; // Declaración del botón
    @FXML
    private TextField correoUsuarioIniciarSesion;
    @FXML
    private TextField passUsuarioIniciarSesion;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonAgendar;
    @FXML
    private Button botonRevisarConsulta;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Button botonRegistrarCita;

    Validaciones validar = new Validaciones();

    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonIniciarSesion){
                if (correoUsuarioIniciarSesion.getText().equals("admin@clinicasalvadoreña.com")){

                    FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/indexAdmin.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(indexDoctor.load(),845,560);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);
                    Alertas.confirmacionCierre(nuevoStage);
                    nuevoStage.show();

                    // Cerrar el formulario actual
                    Stage actualStage = (Stage) botonIniciarSesion.getScene().getWindow(); // Obtener el Stage actual
                    actualStage.close(); // Cerrar la ventana actual

                }else {
                    if (conexionBdd.getConnection() != null){
                        if (validar.validarLogin(correoUsuarioIniciarSesion.getText(),passUsuarioIniciarSesion.getText())){
                            FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/indexDoctor.fxml"));
                            Stage nuevoStage = new Stage();
                            Scene form = new Scene(indexDoctor.load(),949,526);
                            /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                            nuevoStage.setTitle("Sistema Agendador de Citas");
                            nuevoStage.setResizable(false);
                            nuevoStage.setScene(form);
                            Alertas.confirmacionCierre(nuevoStage);
                            nuevoStage.show();

                            // Cerrar el formulario actual
                            Stage actualStage = (Stage) botonIniciarSesion.getScene().getWindow(); // Obtener el Stage actual
                            actualStage.close(); // Cerrar la ventana actual
                        }
                    }else {
                        System.out.println("XD no se pudo jaskdas");
                    }
                }

            } else if (event.getSource() == botonAgendar){
                FXMLLoader vistaAgendar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/agendarCitas.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(vistaAgendar.load(),991,571);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();
                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonAgendar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual



            } else if (event.getSource() == botonRegistrarCita) {

                FXMLLoader vistaRegistroCitas = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/registroConsultas.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(vistaRegistroCitas.load(),996,583);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegistrarCita.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

                
            } else if (event.getSource() == botonRevisarConsulta) {
                FXMLLoader vistaRevisarConsulta = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/consultasPendientes.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(vistaRevisarConsulta.load(),996,722);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRevisarConsulta.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonRegresar){
                FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/indexDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(regresar.load(),949,526);
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
    public void redireccionFormCitaActiva(ActionEvent event) throws IOException {

        FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/formConsultaActiva.fxml"));
        Stage nuevoStage = new Stage();
        Scene form = new Scene(indexDoctor.load(),996,675);
        /*ocuapmos el argumento stage para preparar y ejecutar el form*/
        nuevoStage.setTitle("Sistema Agendador de Citas");
        nuevoStage.setResizable(false);
        nuevoStage.setScene(form);
        nuevoStage.show();
    }

    @FXML
    private TextField duiCliente;
    @FXML
    private TextField horaCita;
    @FXML
    private DatePicker fechaCita;

    @FXML
    public void agendarCita(ActionEvent event){
        Alertas alerta = new Alertas();

        if (validar.validarAgendarCita(duiCliente.getText(),horaCita.getText(),fechaCita.getValue())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Agendar","La cita ha sido registrada!");
        }
    }
    @FXML
    private TextArea descripcionCita;
    @FXML
    public void finalizarCita(){
        Alertas alerta = new Alertas();

        if (validar.valiarFormFinalizarCita(descripcionCita.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al finalizar","La cita ha sido finalizada!");
        }
    }

}
