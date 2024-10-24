package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;

import java.util.Objects;

public class DoctorController {

    @FXML
    private Button iniciarSesionDoctor; // Declaración del botón

    @FXML
    public void iniciarSesionDoc(){
            try{
                FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/indexDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(indexDoctor.load(),824,434);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) iniciarSesionDoctor.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }catch (Exception e){
                e.printStackTrace();
            }

    }
}
