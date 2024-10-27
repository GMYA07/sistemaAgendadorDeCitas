package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
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
    private Button botonIniciarSesion; // Declaración del botón
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonAgendar;
    @FXML
    private Button botonRevisarConsulta;
    @FXML
    private Button botonRegistrarCita;

    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonIniciarSesion){
                FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/indexDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(indexDoctor.load(),949,526);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonIniciarSesion.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonAgendar){
                FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/agendarCitas.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(indexDoctor.load(),991,571);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonAgendar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonRegistrarCita) {
                
            } else if (event.getSource() == botonRevisarConsulta) {
                FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/consultasPendientes.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(indexDoctor.load(),996,722);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRevisarConsulta.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonRegresar){
                FXMLLoader indexDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/indexDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(indexDoctor.load(),949,526);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
