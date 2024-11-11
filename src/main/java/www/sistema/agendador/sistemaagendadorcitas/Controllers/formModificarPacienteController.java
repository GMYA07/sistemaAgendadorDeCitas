package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.PacienteModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class formModificarPacienteController {

    public void initialize(){
        cargarCombobox();
    }

    Validaciones validar = new Validaciones();

    @FXML
    private PacienteModel pacienteModificar;

    @FXML
    private ComboBox selectProcencia;
    ObservableList<ProcedenciaModel> observableListProcedencia;
    @FXML
    private ComboBox selectEstadoPaciente;
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
    TextArea inputDescripcionPaciente;
    @FXML
    TextField inputIdPaciente;

    @FXML
    public void redireccionSistema(ActionEvent event){

        try {
            if (event.getSource() == botonRegresar){
                FXMLLoader administrarPacientes = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarPacientes.load(),1465,798);
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
                FXMLLoader administrarPacientes = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarPacientes.load(),1465,798);
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
            throw new RuntimeException(e);
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
    public void modificarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();

        int estado = 0;
        if (selectEstadoPaciente.getSelectionModel().getSelectedItem().toString().equals("Retirado")){
            estado = 1;
        }

        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputDescripcionPaciente.getText())){
            pacienteDAO pacDao = new pacienteDAO();

            /*creamos el paciente para modificar*/
            PacienteModel pacienteModificado = new PacienteModel();
            /*Aqui insertamos los datos al objeto*/
            pacienteModificado.setIdPaciente(inputIdPaciente.getText());
            pacienteModificado.setDuiPaciente(inputDui.getText());
            pacienteModificado.setNombresPaciente(inputNombres.getText());
            pacienteModificado.setApellidosPaciente(inputApellidos.getText());
            pacienteModificado.setCorreoPaciente(inputCorreo.getText());
            pacienteModificado.setTelefonoPaciente(inputTelefono.getText());
            pacienteModificado.setPasswordPaciente(inputPass.getText());
            pacienteModificado.setProcedenciaPaciente(seleccion.getIdProcedencia());
            pacienteModificado.setDescripcionPaciente(inputDescripcionPaciente.getText());
            /*ahora convertiremos el valor localDate a date*/
            Date fechaNacDoc =  Date.from(inputFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            pacienteModificado.setFechaNacPaciente(fechaNacDoc);
            pacienteModificado.setEstadoPaciente(estado);

            if (pacDao.modificarPaciente(pacienteModificado) > 0){
                alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El paciente ha sido modificado!");
                redireccionSistema(event);
            }else {
                alerta.alertaCorrecto("WARNING","Error al Modificar","El paciente no ha sido modificado!");
            }
        }


    }

    public PacienteModel getPacienteModificar() {
        return pacienteModificar;
    }

    public void setPacienteModificar(PacienteModel pacienteModificar) {
        this.pacienteModificar = pacienteModificar;

        inputIdPaciente.setText(pacienteModificar.getIdPaciente());
        inputDui.setText(pacienteModificar.getDuiPaciente());
        inputNombres.setText(pacienteModificar.getNombresPaciente());
        inputApellidos.setText(pacienteModificar.getApellidosPaciente());
        inputCorreo.setText(pacienteModificar.getCorreoPaciente());
        inputTelefono.setText(pacienteModificar.getTelefonoPaciente());
        inputPass.setText(pacienteModificar.getPasswordPaciente());
        procedenciaDAO proDao = new procedenciaDAO();
        ProcedenciaModel procedencia = proDao.obtenerProcedenciaExacta(pacienteModificar.getProcedenciaPaciente());
        selectProcencia.setValue(procedencia);
        inputDescripcionPaciente.setText(pacienteModificar.getDescripcionPaciente());
        Date fechaPasar = pacienteModificar.getFechaNacPaciente();
        java.sql.Date sqlDate = new java.sql.Date(fechaPasar.getTime());
        LocalDate fechaNac = sqlDate.toLocalDate();
        inputFecha.setValue(fechaNac);

        if (pacienteModificar.getEstadoPaciente() == 0){
            selectEstadoPaciente.setValue("Activo");
        }else {
            selectEstadoPaciente.setValue("Retirado");
        }
    }
}
