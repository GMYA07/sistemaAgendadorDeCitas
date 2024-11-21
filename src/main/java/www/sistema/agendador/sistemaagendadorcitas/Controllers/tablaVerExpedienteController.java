package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.citasDAO;

public class tablaVerExpedienteController {

    private String llenarExpediente;

    @FXML
    private TableView<ObservableList<String>> tablaExpediente;
    @FXML
    private TableColumn<ObservableList<String>,String> columnaIdCita;
    @FXML
    private TableColumn<ObservableList<String>,String> columnaDoctor;
    @FXML
    private TableColumn<ObservableList<String>,String> columnnaFecha;
    @FXML
    private TableColumn<ObservableList<String>,String> columnaDescripcion;

    public String getLlenarExpediente() {
        return llenarExpediente;
    }

    public void setLlenarExpediente(String llenarExpediente) {
        this.llenarExpediente = llenarExpediente;

        columnaIdCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(0)));
        columnaDoctor.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(1)));
        columnnaFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(2)));
        columnaDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(3)));

        llenarTabla(llenarExpediente);
    }

    private void llenarTabla(String idPaciente){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        citasDAO ctD = new citasDAO();
        datosTabla = ctD.obtenerCitasExpiente(idPaciente);
        tablaExpediente.setItems(datosTabla);
    }
}
