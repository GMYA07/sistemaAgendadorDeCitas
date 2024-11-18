package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.bdd.citasDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.SessionManager;

import java.util.Date;

public class registroConsultasController {

    @FXML
    private Button botonRegresar;
    /*Elementos para tabla*/
    @FXML
    private TableView<ObservableList<String>> tablaRegistroCitas;
    @FXML
    private TableColumn<ObservableList<String>, String> colIdCita;
    @FXML
    private TableColumn<ObservableList<String>, String> colPaciente;
    @FXML
    private TableColumn<ObservableList<String>, String> colFechaCita;
    @FXML
    private TableColumn<ObservableList<String>, String> colHoraCita;
    @FXML
    private TableColumn<ObservableList<String>, String> colDuiPaciente;
    @FXML
    private TableColumn<ObservableList<String>, String> colDescripcionPaciente;
    @FXML
    private TableColumn<ObservableList<String>, String> colDescripcionCita;
    /*Datos para la targeta de al lado*/
    @FXML
    TextField inputIdCita;
    @FXML
    TextField inputDuiP;
    @FXML
    TextField inputPaciente;
    @FXML
    TextField inputFecha;
    @FXML
    TextField inputHora;
    @FXML
    TextArea inputDescripcionP;
    @FXML
    TextArea inputDescripC;

    public void initialize(){
        // Configurar columnas para que manejen índices de datos
        colIdCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(0)));
        colPaciente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(1)));
        colFechaCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(2)));
        colHoraCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(3)));
        colDuiPaciente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(4)));
        colDescripcionPaciente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(5)));
        colDescripcionCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(6)));
        // Ocultar la columnas para que no sea visible
        colDuiPaciente.setVisible(false);
        colDescripcionPaciente.setVisible(false);
        colDescripcionCita.setVisible(false);
        // Llenar datos en la tabla
        llenarTabla();

        // Listener para cambios en la selección de la tabla
        tablaRegistroCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Mostrar los datos seleccionados en los controles
                inputIdCita.setText(newSelection.get(0));
                inputPaciente.setText(newSelection.get(1));
                inputFecha.setText(newSelection.get(2));
                inputHora.setText(newSelection.get(6));
                inputDuiP.setText(newSelection.get(3));
                inputDescripcionP.setText(newSelection.get(4));
                inputDescripC.setText(newSelection.get(5));
            }
        });
    }

    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonRegresar){
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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void llenarTabla(){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        citasDAO cDao = new citasDAO();
        datosTabla = cDao.obtenerCitasPasadas(SessionManager.getInstance().getIdDoctor());
        tablaRegistroCitas.setItems(datosTabla);
    }
}
