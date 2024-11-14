package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.SessionManager;

public class indexDoctorController {

    public void initialize(){
        obtenerTituloIndex();
    }

    @FXML
    private Button botonAgendar;
    @FXML
    private Button botonRevisarConsulta;
    @FXML
    private Button botonRegistrarCita;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Label tituloApellidos;

    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonAgendar){
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void obtenerTituloIndex(){
        doctorDAO dcDAO = new doctorDAO();
        String apellidosTitulo = dcDAO.obtenerTituloIndex(SessionManager.getInstance().getIdDoctor());
        tituloApellidos.setText(apellidosTitulo);

    }
}
