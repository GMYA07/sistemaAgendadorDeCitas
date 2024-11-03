package www.sistema.agendador.sistemaagendadorcitas.src;

import javafx.scene.control.Alert;

public class Validaciones {

    public boolean validarLogin(String correo, String password){

        if (correo.isEmpty() && password.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta de Información");
            alerta.setHeaderText("Encabezado de la Alerta");
            alerta.setContentText("Este es el contenido de la alerta.");

            alerta.showAndWait();
            return false;
        }else {
            return true;
        }

    }
}
