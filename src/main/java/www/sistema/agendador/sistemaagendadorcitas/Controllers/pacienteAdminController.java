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
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;

import java.util.Date;
import java.util.List;

public class pacienteAdminController {

    public void initialize(){
        cargarTablaPacientes();
    }
    Alertas alerta = new Alertas();

    @FXML
    private Button botonFormPaciente;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonFormModificar;
    @FXML
    private Button botonFormEliminar;

    /*Para el uso de las tablas*/
    @FXML
    private TableView<PacienteModel> tablaPacientes;
    @FXML
    private TableColumn<PacienteModel,String> columnaId;
    @FXML
    private TableColumn<PacienteModel,String> columnaDui;
    @FXML
    private TableColumn<PacienteModel,String> columnaNombres;
    @FXML
    private TableColumn<PacienteModel,String> columnaApellidos;
    @FXML
    private TableColumn<PacienteModel, Date> columnaFechaNac;
    @FXML
    private TableColumn<PacienteModel,String> columnaCorreo;
    @FXML
    private TableColumn<PacienteModel,String> columnaTelefono;
    @FXML
    private TableColumn<PacienteModel,String> columnaProcedencia;
    @FXML
    private TableColumn<PacienteModel,String> columnaDescrip;
    @FXML
    private TableColumn<PacienteModel,String> columnaEstado;
    ObservableList<PacienteModel> observableListPaciente;

    public void cargarTablaPacientes(){
        columnaId.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
        columnaDui.setCellValueFactory(new PropertyValueFactory<>("duiPaciente"));
        columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombresPaciente"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidosPaciente"));
        columnaFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacPaciente"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoPaciente"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoPaciente"));
        columnaProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedenciaPaciente"));
        columnaDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcionPaciente"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPaciente"));

         pacienteDAO pacDAO = new pacienteDAO();

        List<PacienteModel> listaPacientes = pacDAO.obtenerPacientes();

        observableListPaciente = FXCollections.observableArrayList(listaPacientes);

        tablaPacientes.setItems(observableListPaciente);
    }

    @FXML
    public void redireccionSistema(ActionEvent event){

        try{
            if (event.getSource() == botonFormPaciente){
                /*aqui instanciamos para abrir la ventana*/
                FXMLLoader formAgregarDoc = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formAgregarPaciente.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formAgregarDoc.load(),956,537);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonFormPaciente.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if (event.getSource() == botonFormModificar){
                PacienteModel pacienteModificar = tablaPacientes.getSelectionModel().getSelectedItem();

                if (pacienteModificar != null){
                    FXMLLoader formModificarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formModificarPaciente.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(formModificarPaciente.load(),966,547);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);
                    Alertas.confirmacionCierre(nuevoStage);
                    /*esto nos ayudara a mandar el objeto*/
                    formModificarPacienteController controller = formModificarPaciente.getController();
                    controller.setPacienteModificar(pacienteModificar);
                    nuevoStage.show();
                    // Cerrar el formulario actual
                    Stage actualStage = (Stage) botonFormModificar.getScene().getWindow(); // Obtener el Stage actual
                    actualStage.close(); // Cerrar la ventana actual

                }else {
                    alerta.alertaAtencion("WARNING","Administrar Paciente","Debe de seleccionar a algun paciente");
                }



            }else if (event.getSource() == botonFormEliminar){

                FXMLLoader administrarDoctores = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
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

            } else if (event.getSource() == botonRegresar) {
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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void eliminarPaciente(ActionEvent event){
        PacienteModel pacienteEliminar = tablaPacientes.getSelectionModel().getSelectedItem();

        if(pacienteEliminar != null){
            pacienteDAO pacDao = new pacienteDAO();

            if (Alertas.confirmacionEliminar() == true){
                if (pacDao.eliminarPaciente(pacienteEliminar.getIdPaciente()) > 0){
                    alerta.alertaCorrecto("INFORMATION","Eliminar Paciente","El paciente ha sido eliminado!");
                    redireccionSistema(event);
                }else {
                    alerta.alertaAtencion("ERROR","Eliminar Paciente","No se pudo eliminar el paciente!");
                }
            }else {

            }
        }else {
            alerta.alertaAtencion("WARNING","Administrar Paciente","Debe de seleccionar a algun paciente");
        }
    }

}
