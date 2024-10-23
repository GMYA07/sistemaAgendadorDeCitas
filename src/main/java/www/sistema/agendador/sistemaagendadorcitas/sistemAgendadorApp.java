package www.sistema.agendador.sistemaagendadorcitas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class sistemAgendadorApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        /*Aqui contruimos la app*/
        /*este es un objeto para crear el formulario*/
        FXMLLoader login = new FXMLLoader(sistemAgendadorApp.class.getResource("views/login.fxml"));
        /*aqui le damos los tamaños*/
        Scene form = new Scene(login.load(),300,300);
        /*ocuapmos el argumento stage para preparar y ejecutar el form*/
        stage.setTitle("Sistema Agendador de Citas");
        stage.setScene(form);
        stage.show();
    }

    public static void main(String[] args){
        launch(); /*esto ejecuta la app*/
    }
}
