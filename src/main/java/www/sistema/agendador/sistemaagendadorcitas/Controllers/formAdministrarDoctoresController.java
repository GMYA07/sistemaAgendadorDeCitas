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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;

import java.util.Date;
import java.util.List;

public class formAdministrarDoctoresController {

    public void initialize(){
        cargarTablaDoctores();
    }
    Alertas alerta = new Alertas();

    @FXML
    private Button botonFormDoctores;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonFormModificar;
    @FXML
    private Button botonFormEliminar;


    /*Para el uso de las tablas*/
    @FXML
    private TableView<DoctorModel> tablaDoctores;
    @FXML
    private TableColumn<DoctorModel,String> columnaId;
    @FXML
    private TableColumn<DoctorModel,String> columnaDui;
    @FXML
    private TableColumn<DoctorModel,String> columnaNombres;
    @FXML
    private TableColumn<DoctorModel,String> columnaApellidos;
    @FXML
    private TableColumn<DoctorModel, Date> columnaFechaNac;
    @FXML
    private TableColumn<DoctorModel,String> columnaCorreo;
    @FXML
    private TableColumn<DoctorModel,String> columnaTelefono;
    @FXML
    private TableColumn<DoctorModel,String> columnaProcedencia;
    @FXML
    private TableColumn<DoctorModel,String> columnaEspecialidad;
    @FXML
    private TableColumn<DoctorModel,String> columnaEstado;
    ObservableList<DoctorModel> observableListDoctores;

    public void cargarTablaDoctores(){
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idDoctor"));
        columnaDui.setCellValueFactory(new PropertyValueFactory<>("duiDoctor"));
        columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombresDoctor"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidosDoctor"));
        columnaFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacDoctor"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoDoctor"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoDoctor"));
        columnaProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedenciaDoctor"));
        columnaEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidadDoctor"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoDoctor"));

        doctorDAO daoDoc = new doctorDAO();

        List<DoctorModel> listaDoctores = daoDoc.obtenerDoctores();

        observableListDoctores = FXCollections.observableArrayList(listaDoctores);

        tablaDoctores.setItems(observableListDoctores);
    }

    @FXML
    public void redireccionSistema(ActionEvent event){
        try {
            if (event.getSource() == botonFormDoctores) {

                /*aqui instanciamos para abrir la ventana*/
                FXMLLoader formAgregarDoc = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formAgregarDoctor.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formAgregarDoc.load(),956,537);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonRegresar){
                FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/indexAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(regresar.load(),845,560);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonFormModificar){
                DoctorModel doctorModificar = tablaDoctores.getSelectionModel().getSelectedItem();

                if(doctorModificar != null){

                    FXMLLoader formModificarDoctor = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formModificarDoctor.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(formModificarDoctor.load(),966,547);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);
                    Alertas.confirmacionCierre(nuevoStage);
                    formModificarDoctorController controller = formModificarDoctor.getController();
                    controller.setDoctorModificar(doctorModificar);
                    nuevoStage.show();
                    // Cerrar el formulario actual
                    Stage actualStage = (Stage) botonFormModificar.getScene().getWindow(); // Obtener el Stage actual
                    actualStage.close(); // Cerrar la ventana actual

                }else {
                    alerta.alertaAtencion("WARNING","Administrar Doctor","Debe de seleccionar a algun doctor");
                }
            }else if (event.getSource() == botonFormEliminar){
                FXMLLoader administrarDoctores = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/doctoresAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarDoctores.load(),1465,798);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonFormEliminar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void eliminarDoctor(ActionEvent event){
        DoctorModel doctorEliminar = tablaDoctores.getSelectionModel().getSelectedItem();

        if(doctorEliminar != null){
            doctorDAO docDao = new doctorDAO();

            if (Alertas.confirmacionEliminar() == true){
                if (docDao.eliminarDoctor(doctorEliminar.getIdDoctor()) > 0){
                    alerta.alertaCorrecto("INFORMATION","Eliminar Doctor","El Doctor ha sido eliminado!");
                    redireccionSistema(event);
                }else {
                    alerta.alertaAtencion("ERROR","Eliminar Doctor","No se pudo eliminar el doctor!");
                }
            }else {

            }
        }else {
            alerta.alertaAtencion("WARNING","Administrar Doctor","Debe de seleccionar a algun doctor");
        }
    }


}
