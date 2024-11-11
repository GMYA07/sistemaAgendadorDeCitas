package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.bdd.conexionBdd;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

public class formLoginController {

    @FXML
    private TextField correoUsuarioIniciarSesion;
    @FXML
    private TextField passUsuarioIniciarSesion;
    @FXML
    private Button botonIniciarSesion;

    Validaciones validar = new Validaciones();

    public void redireccionSistema(ActionEvent event){
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
