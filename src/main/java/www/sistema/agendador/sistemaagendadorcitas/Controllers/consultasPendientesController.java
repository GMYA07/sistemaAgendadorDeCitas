package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.bdd.citasDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.SessionManager;

public class consultasPendientesController {
    Alertas alerta = new Alertas();
    @FXML
    private Button botonRegresar;
    @FXML
    private Button btnEmpezar;

    public void initialize(){
        columPaciente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(0)));
        columFechaCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(1)));
        columHoraCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(2)));
        columIdPaciente.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(3)));
        columIdCita.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(4)));
        // Ocultar la columnas para que no sea visible
        columIdPaciente.setVisible(false);
        columIdCita.setVisible(false);
        llenarTabla();
    }

    @FXML
    private TableView<ObservableList<String>> tablaConsultasPendientes;
    @FXML
    private TableColumn<ObservableList<String>, String> columPaciente;
    @FXML
    private TableColumn<ObservableList<String>, String> columFechaCita;
    @FXML
    private TableColumn<ObservableList<String>, String> columHoraCita;
    @FXML
    private TableColumn<ObservableList<String>,String> columIdPaciente;
    @FXML
    private TableColumn<ObservableList<String>,String> columIdCita;

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

            }else if (event.getSource() == btnEmpezar){
                ObservableList<String> seleccion = tablaConsultasPendientes.getSelectionModel().getSelectedItem();

                if (seleccion != null){
                    FXMLLoader formConsultaActiva = new FXMLLoader(sistemAgendadorApp.class.getResource("views/DoctorView/formConsultaActiva.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(formConsultaActiva.load(),996,675);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);
                    Alertas.confirmacionCierre(nuevoStage);
                    formConsultaActivaController controller =formConsultaActiva.getController();
                    controller.setCitaFinalizar(seleccion.get(0));
                    controller.setPacienteCita(seleccion.get(3));
                    nuevoStage.show();

                    // Cerrar el formulario actual
                    Stage actualStage = (Stage) btnEmpezar.getScene().getWindow(); // Obtener el Stage actual
                    actualStage.close(); // Cerrar la ventana actual
                }else {
                    alerta.alertaAtencion("WARNING","Sistema Agendado de Citas","Debe de seleccionar alguna cita");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void llenarTabla(){
        ObservableList<ObservableList<String>> datosTabla = FXCollections.observableArrayList();
        citasDAO ciDao = new citasDAO();
        datosTabla = ciDao.obtenerCitasPendientes(SessionManager.getInstance().getIdDoctor());
        tablaConsultasPendientes.setItems(datosTabla);
    }
}
