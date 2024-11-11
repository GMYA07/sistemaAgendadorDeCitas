package www.sistema.agendador.sistemaagendadorcitas.src;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Alertas {

    public void alertaError(String tipoAlerta, String tituloAlerta,String contenidoAlerta){
        contructorAlerta(tipoAlerta,tituloAlerta,contenidoAlerta);
    }
    public void alertaCorrecto(String tipoAlerta, String tituloAlerta,String contenidoAlerta){
        contructorAlerta(tipoAlerta,tituloAlerta,contenidoAlerta);
    }

    public void alertaAtencion(String tipoAlerta, String tituloAlerta,String contenidoAlerta){
        contructorAlerta(tipoAlerta,tituloAlerta,contenidoAlerta);
    }
    private static void  contructorAlerta(String tipoAlerta,String tituloAlerta,String contenidoAlerta){
        Alert alerta = new Alert(Alert.AlertType.valueOf(tipoAlerta));
        alerta.setTitle(tituloAlerta);
        alerta.setHeaderText(contenidoAlerta);
        alerta.showAndWait();
    }

    public static void confirmacionCierre(Stage stage) {
        stage.setOnCloseRequest(event -> {
            // Crear botones personalizados
            ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
            ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            // Crear la alerta de confirmación con botones personalizados
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea cerrar el programa?", botonSi, botonNo);
            alerta.setTitle("Confirmación de Cierre");
            alerta.setHeaderText("Confirmación necesaria");

            // Mostrar la alerta y capturar la respuesta del usuario
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isEmpty() || resultado.get() != botonSi) {
                // Cancela el cierre si el usuario selecciona "No" o cierra la alerta
                event.consume();
            }
        });
    }
    public static boolean confirmacionCierreSesion(Button botonCerrarSesion) {
        // Crear botones personalizados
        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        // Crear la alerta de confirmación con botones personalizados
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea cerrarar Sesion?", botonSi, botonNo);
        alerta.setTitle("Confirmación de Cierre");
        alerta.setHeaderText("Confirmación necesaria");

        // Mostrar la alerta y capturar la respuesta del usuario
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isEmpty() || resultado.get() == botonSi) {
            // Cancela el cierre si el usuario selecciona "No" o cierra la alerta
            // Cerrar el formulario actual
            Stage actualStage = (Stage) botonCerrarSesion.getScene().getWindow(); // Obtener el Stage actual
            actualStage.close(); // Cerrar la ventana actual
            return true;
        }else {
            return false ;
        }
    }
    public static boolean confirmacionEliminar() {
        // Crear botones personalizados
        ButtonType botonSi = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        // Crear la alerta de confirmación con botones personalizados
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea cerrarar Sesion?", botonSi, botonNo);
        alerta.setTitle("Confirmación de Cierre");
        alerta.setHeaderText("Confirmación necesaria");

        // Mostrar la alerta y capturar la respuesta del usuario
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isEmpty() || resultado.get() == botonSi) {
            return true;
        }else {
            return false ;
        }
    }
}
