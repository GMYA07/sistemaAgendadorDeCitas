package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.beans.Observable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.DoctorModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.doctorDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.procedenciaDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.util.List;

public class AdminController {

    public void initialize(){
        cargarCombobox();
        //cargarTablaDoctores();
    }

    @FXML
    private Button botonAdministrarPaciente;
    @FXML
    private Button botonAdministrarDoctores;
    @FXML
    private Button botonRegresar;
    @FXML
    private Button botonFormPaciente;
    @FXML
    private Button botonFormDoctores;
    @FXML
    private Button doctorAgregar;
    @FXML
    private Button botonCerrarSesion;
    /*para el uso de los combobox*/
    @FXML
    private ComboBox selectProcencia;

    ObservableList<ProcedenciaModel> observableListProcedencia;


    Validaciones validar = new Validaciones();
    doctorDAO daoDoctor = new doctorDAO();

    @FXML
    public void redireccionSistema(ActionEvent event){
        try{
            if (event.getSource() == botonAdministrarPaciente){
                FXMLLoader administrarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/pacientesAdmin.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(administrarPaciente.load(),936,597);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonAdministrarPaciente.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonAdministrarDoctores) {

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
                Stage actualStage = (Stage) botonAdministrarDoctores.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

                
            } else if (event.getSource() == botonRegresar){
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

            }else if (event.getSource() == botonFormPaciente){
                FXMLLoader formAgregarPaciente = new FXMLLoader(sistemAgendadorApp.class.getResource("views/AdminView/formAgregarPaciente.fxml"));
                Stage nuevoStage = new Stage();
                Scene form = new Scene(formAgregarPaciente.load(),966,547);
                /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                nuevoStage.setTitle("Sistema Agendador de Citas");
                nuevoStage.setResizable(false);
                nuevoStage.setScene(form);
                Alertas.confirmacionCierre(nuevoStage);
                nuevoStage.show();

                // Cerrar el formulario actual
                Stage actualStage = (Stage) botonRegresar.getScene().getWindow(); // Obtener el Stage actual
                actualStage.close(); // Cerrar la ventana actual

            } else if (event.getSource() == botonFormDoctores) {

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

            }else if (event.getSource() == botonCerrarSesion){

                if (Alertas.confirmacionCierreSesion(botonCerrarSesion)){
                    FXMLLoader regresar = new FXMLLoader(sistemAgendadorApp.class.getResource("views/login.fxml"));
                    Stage nuevoStage = new Stage();
                    Scene form = new Scene(regresar.load(),824,434);
                    /*ocuapmos el argumento stage para preparar y ejecutar el form*/
                    nuevoStage.setTitle("Sistema Agendador de Citas");
                    nuevoStage.setResizable(false);
                    nuevoStage.setScene(form);

                    nuevoStage.show();
                }
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


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*estas funciones son herramientas para generar datos*/
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


    public static String generarHash(String pass) {
        try {
            // Crear un digest de SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el hash a la contraseña
            byte[] hashBytes = digest.digest(pass.getBytes());

            // Convertir el hash a formato hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al aplicar SHA-256", e);
        }
    }

    public String generadorId(Integer tipoId){
        String idGenerado = "";
        // Definir la semilla
        long semilla = System.currentTimeMillis(); // Puedes usar cualquier valor como semilla
        // Crear un generador de números aleatorios con la semilla
        Random random = new Random(semilla);
        // Generar un número aleatorio entre 100 y 999 (3 cifras)
        int numeroAleatorio = 100 + random.nextInt(900); // 900 es el rango para obtener un número de 3 cifras

        if (tipoId == 0){
            idGenerado = "DC"+numeroAleatorio;
            return idGenerado;
        }else if (tipoId == 1){
            return idGenerado;
        }else {
            return "error";
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

    @FXML
    public void agregarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();

        if (validar.validarFormAgregarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputEspecialidad.getText())){
            /*creamos un nuevo doctor*/
            DoctorModel nuevoDoctor = new DoctorModel();
            /*Aqui insertamos los datos al objeto*/
            nuevoDoctor.setIdDoctor(generadorId(0));
            nuevoDoctor.setDuiDoctor(inputDui.getText());
            nuevoDoctor.setTelefonoDoctor(inputTelefono.getText());
            nuevoDoctor.setNombresDoctor(inputNombres.getText());
            nuevoDoctor.setApellidosDoctor(inputApellidos.getText());
            nuevoDoctor.setCorreoDoctor(inputCorreo.getText());
            nuevoDoctor.setPasswordDoctor(generarHash(inputPass.getText()));
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
    @FXML
    public void modificarDoctor(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();
        if (validar.validarFormAgregarDoctor(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputEspecialidad.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El doctor ha sido modificado!");
        }
    }
    @FXML
    public void agregarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();
        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputDescripcionPaciente.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Agregar","El paciente ha sido registrado!");
        }
    }
    @FXML
    public void modificarPaciente(ActionEvent event){
        Alertas alerta = new Alertas();
        ProcedenciaModel seleccion = (ProcedenciaModel) selectProcencia.getSelectionModel().getSelectedItem();
        if (validar.validarFormAgregarPaciente(inputDui.getText(),inputTelefono.getText(),inputFecha.getValue(),inputNombres.getText(),inputApellidos.getText(),inputCorreo.getText(),inputPass.getText(),seleccion,inputDescripcionPaciente.getText())){
            alerta.alertaCorrecto("INFORMATION","Correcto al Modificar","El paciente ha sido modificado!");
        }
    }


}
