package www.sistema.agendador.sistemaagendadorcitas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;

import java.io.IOException;
import java.util.Optional;

public class sistemAgendadorApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        /*Aqui contruimos la app*/
        /*este es un objeto para crear el formulario*/
        FXMLLoader login = new FXMLLoader(sistemAgendadorApp.class.getResource("views/login.fxml"));
        /*aqui le damos los tamaños*/
        Scene form = new Scene(login.load(),824,434);
        /*ocuapmos el argumento stage para preparar y ejecutar el form*/
        stage.setTitle("Sistema Agendador de Citas");
        stage.setResizable(false);
        stage.setScene(form);
        Alertas.confirmacionCierre(stage);
        stage.show();
    }

    public static void main(String[] args){
        launch(); /*esto ejecuta la app*/
    }
}
