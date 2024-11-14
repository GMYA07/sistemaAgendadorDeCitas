package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;

public class indexAdminController {

    @FXML
    private Button botonAdministrarPaciente;
    @FXML
    private Button botonAdministrarDoctores;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonCerrarSesion;

    @FXML
    public void redireccionSistema(ActionEvent event){

       try {
           if (event.getSource() == botonAdministrarPaciente){
               FXMLLoader administrarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
               Stage nuevoStage = new Stage();
               Scene form = new Scene(administrarPaciente.load(),1465,798);
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
               Scene form = new Scene(administrarDoctores.load(),1465,798);
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
}
