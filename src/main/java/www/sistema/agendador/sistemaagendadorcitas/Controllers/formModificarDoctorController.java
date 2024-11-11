package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class formModificarDoctorController {
    public void initialize(){
        cargarCombobox();
    }
    Validaciones validar = new Validaciones();

    @FXML
    private DoctorModel doctorModificar;
    @FXML
    private ComboBox selectProcencia;
    ObservableList<ProcedenciaModel> observableListProcedencia;
    @FXML
    private ComboBox selectEstadoDoctor;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonModificar;

    /*para el uso del form*/
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
    TextField inputIdDoctor;

    @FXML
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

            }else if(event.getSource() == botonModificar){
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
                Stage actualStage = (Stage) botonModificar.getScene().getWindow(); // Obtener el Stage actual
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

    @FXML
    public void modificarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        /*con esto agarramos la seleccion del usuario osea el objeto para sacarle la procedencia*/
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();
        int estado = 0;
        if (selectEstadoDoctor.getSelectionModel().getSelectedItem().toString().equals("Retirado")){
            estado = 1;

        }

        if (validar.validarFormModificarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),seleccion,inputEspecialidad.getText(),estado)){
            doctorDAO docDao= new doctorDAO();
            /*creamos un objeto doctor para modificar*/
            DoctorModel modificarDoctor = new DoctorModel();
            /*Aqui insertamos los datos al objeto*/
            modificarDoctor.setIdDoctor(inputIdDoctor.getText());
            modificarDoctor.setDuiDoctor(inputDui.getText());
            modificarDoctor.setTelefonoDoctor(inputTelefono.getText());
            modificarDoctor.setNombresDoctor(inputNombres.getText());
            modificarDoctor.setApellidosDoctor(inputApellidos.getText());
            modificarDoctor.setCorreoDoctor(inputCorreo.getText());
            modificarDoctor.setPasswordDoctor(inputPass.getText());
            modificarDoctor.setProcedenciaDoctor(seleccion.getIdProcedencia());
            modificarDoctor.setEspecialidadDoctor(inputEspecialidad.getText());
            /*ahora convertiremos el valor localDate a date*/
            Date fechaNacDoc =  Date.from(inputFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            modificarDoctor.setFechaNacDoctor(fechaNacDoc);
            modificarDoctor.setEstadoDoctor(0);

            if (docDao.modificarDoctor(modificarDoctor) > 0){
                alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El doctor ha sido modificado!");
                redireccionSistema(event);
            }else {
                alerta.alertaCorrecto("WARNING","Error al Modificar","El doctor no ha sido modificado!");
            }
        }
    }

    public DoctorModel getDoctorModificar() {
        return doctorModificar;
    }

    public void setDoctorModificar(DoctorModel doctorModificar) {
        this.doctorModificar = doctorModificar;

        Date fechaPasar = doctorModificar.getFechaNacDoctor();
        java.sql.Date sqlDate = new java.sql.Date(fechaPasar.getTime());
        LocalDate fechaNac = sqlDate.toLocalDate();
        inputDui.setText(doctorModificar.getDuiDoctor());
        inputTelefono.setText(doctorModificar.getTelefonoDoctor());
        inputNombres.setText(doctorModificar.getNombresDoctor());
        inputApellidos.setText(doctorModificar.getApellidosDoctor());
        inputCorreo.setText(doctorModificar.getCorreoDoctor());
        inputFecha.setValue(fechaNac);
        inputIdDoctor.setText(doctorModificar.getIdDoctor());
        inputPass.setText(doctorModificar.getPasswordDoctor());
        procedenciaDAO proDao = new procedenciaDAO();
        ProcedenciaModel procedencia = proDao.obtenerProcedenciaExacta(doctorModificar.getProcedenciaDoctor());
        selectProcencia.setValue(procedencia);
        inputEspecialidad.setText(doctorModificar.getEspecialidadDoctor());



        if (doctorModificar.getEstadoDoctor() == 0){
            selectEstadoDoctor.setValue("Activo");
        }else {
            selectEstadoDoctor.setValue("Retirado");
        }
    }
}
