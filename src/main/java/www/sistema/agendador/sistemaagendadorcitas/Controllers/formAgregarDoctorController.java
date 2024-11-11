package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class formAgregarDoctorController {

    public void initialize(){
        cargarCombobox();
    }
    @FXML
    private Button botonRegresar;
    @FXML
    private Button doctorAgregar;

    @FXML
    private ComboBox selectProcencia;

    ObservableList<ProcedenciaModel> observableListProcedencia;

    public void redireccionSistema(ActionEvent event){
        try{
            if (event.getSource() == botonRegresar){
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
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            }else if(event.getSource() == doctorAgregar){
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
                Stage actualStage = (Stage) doctorAgregar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarCombobox(){
        try {
            procedenciaDAO lista = new procedenciaDAO();
            List<ProcedenciaModel> listaPro = lista.obtenerProcedencia();
            observableListProcedencia = FXCollections.observableArrayList(listaPro);
            selectProcencia.setItems(observableListProcedencia);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*AQUI SE HACE LA PARTE DEL CRUD OSEA INSERTAR, MODIFICAR Y ELIMINAR*/
    @FXML
    TextField inputDui;
    @FXML
    TextField inputTelefono;
    @FXML
    DatePicker inputFecha;
    @FXML
    TextField inputNombres;
    @FXML
    TextField inputApellidos;
    @FXML
    TextField inputCorreo;
    @FXML
    TextField inputPass;
    @FXML
    TextField inputEspecialidad;
    @FXML
    TextArea inputDescripcionPaciente;

    Validaciones validar = new Validaciones();
    doctorDAO daoDoctor = new doctorDAO();

    @FXML
    public void agregarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();

        if (validar.validarFormAgregarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputEspecialidad.getText())){
            /*creamos un nuevo doctor*/
            DoctorModel nuevoDoctor = new DoctorModel();
            /*Aqui insertamos los datos al objeto*/
            nuevoDoctor.setIdDoctor(Utilidades.generadorId(0));
            nuevoDoctor.setDuiDoctor(inputDui.getText());
            nuevoDoctor.setTelefonoDoctor(inputTelefono.getText());
            nuevoDoctor.setNombresDoctor(inputNombres.getText());
            nuevoDoctor.setApellidosDoctor(inputApellidos.getText());
            nuevoDoctor.setCorreoDoctor(inputCorreo.getText());
            nuevoDoctor.setPasswordDoctor(Utilidades.generarHash(inputPass.getText()));
            nuevoDoctor.setProcedenciaDoctor(seleccion.getIdProcedencia());
            nuevoDoctor.setEspecialidadDoctor(inputEspecialidad.getText());
            /*ahora convertiremos el valor localDate a date*/
            Date fechaNacDoc =  Date.from(inputFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            nuevoDoctor.setFechaNacDoctor(fechaNacDoc);
            nuevoDoctor.setEstadoDoctor(0);

            if (daoDoctor.insertarDoctor(nuevoDoctor) > 0){
                alerta.alertaCorrecto("INFORMATION","Correcto al Agregar","El doctor ha sido registrado!");
                redireccionSistema(event);
            }else {
                alerta.alertaError("ERROR","Error al Agregar","El doctor no ha sido registrado");
            }
        }
    }

}
