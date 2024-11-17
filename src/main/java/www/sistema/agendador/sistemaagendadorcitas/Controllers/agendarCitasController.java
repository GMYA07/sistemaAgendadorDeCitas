package www.sistema.agendador.sistemaagendadorcitas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import www.sistema.agendador.sistemaagendadorcitas.Models.citasModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.expedienteCitaModel;
import www.sistema.agendador.sistemaagendadorcitas.bdd.citasDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.expedientecitasDAO;
import www.sistema.agendador.sistemaagendadorcitas.bdd.pacienteDAO;
import www.sistema.agendador.sistemaagendadorcitas.sistemAgendadorApp;
import www.sistema.agendador.sistemaagendadorcitas.src.Alertas;
import www.sistema.agendador.sistemaagendadorcitas.src.SessionManager;
import www.sistema.agendador.sistemaagendadorcitas.src.Utilidades;
import www.sistema.agendador.sistemaagendadorcitas.src.Validaciones;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class agendarCitasController {

    public void initialize() {

        // Agregar un listener para detectar cambios en el texto
        duiCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            // Aquí puedes realizar la lógica, por ejemplo, buscar un nombre relacionado con el ID
            if (!newValue.isEmpty()) {
                pacienteDAO daoPa = new pacienteDAO();
                String nombre = daoPa.obtenerNombres(newValue);
                nombresPaciente.setText(nombre);
            } else {
                nombresPaciente.setText("Ningun Paciente Encontrado"); // Limpiar si el campo está vacío
            }
        });
    }

    Validaciones validar = new Validaciones();
    @FXML
    Button botonRegresar ;
    @FXML
    TextField duiCliente;
    @FXML
    ComboBox horaCita;
    @FXML
    DatePicker fechaCita;
    @FXML
    TextField nombresPaciente;

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
    public void agendarCita(ActionEvent event){
        Alertas alerta = new Alertas();
        expedientecitasDAO expDAO = new expedientecitasDAO();

        if (validar.validarAgendarCita(duiCliente.getText(),horaCita.getSelectionModel().getSelectedItem().toString(),fechaCita.getValue())){
           if (nombresPaciente.getText().equals("Ningun Paciente Encontrado")){
               alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada dado que no existe ese paciente!");
           }else {
               pacienteDAO pacDAO = new pacienteDAO();
               String idPaciente = pacDAO.obtenerIdPaciente(duiCliente.getText()); /*esta variable la ocupamos mas para saber lo si existe el expediente del paciente*/

               if (expDAO.verificarExistencia(idPaciente) == 1){ /*Aqui verificamos si el tiene un expediente existente o necesitara crearsele uno*/
                   /*Como segundo ahora agendaremos la cita*/
                   String idExpediente = expDAO.obtenerIdExpediente(idPaciente);/*aqui obtenemos el id del expediente*/
                   if (!idExpediente.equals("-")){ /*Aqui verificamos si se encontro el id del expediente del usuario*/

                       /*Aqui obtenemos todas las citas del doctor para mandar a verificar si no tiene problemas de horario*/
                       citasDAO citDao = new citasDAO();
                       List<citasModel> listaCitasDoc = citDao.obtenerCitasDoctor(SessionManager.getInstance().getIdDoctor());

                       if (!validar.validarHorarioCita(listaCitasDoc,horaCita.getSelectionModel().getSelectedItem().toString(),fechaCita.getValue())){
                           citasDAO citaDao = new citasDAO();
                           citasModel nuevaCita = new citasModel();
                           nuevaCita.setIdCita(Utilidades.generadorId(3));
                           nuevaCita.setIdExpedienteCita(idExpediente);
                           nuevaCita.setIdDoctor_Cita(SessionManager.getInstance().getIdDoctor());
                           /*ahora convertiremos el valor localDate a date*/
                           Date fechaAgendada =  Date.from(fechaCita.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                           nuevaCita.setFechaCita(fechaAgendada);
                           nuevaCita.setHoraCita(horaCita.getSelectionModel().getSelectedItem().toString());
                           if (citaDao.insertarCita(nuevaCita) > 0){
                               alerta.alertaCorrecto("INFORMATION","Correcto al Agendar","La cita ha sido registrada!");
                           }else {
                               alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada!");
                           }
                       }

                   }else {
                       alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada dado que no se encontro el expediente!");
                   }
               }else {
                   /*Primero crearemos el expediente del paciente luego insertaremos la cita*/
                   expedienteCitaModel nuevoExpediente = new expedienteCitaModel();
                   nuevoExpediente.setIdExpediente(Utilidades.generadorId(2));
                   nuevoExpediente.setIdPaciente_Exp(idPaciente);
                   if (expDAO.insertarExistencia(nuevoExpediente) > 0){ /*verificamos si se inserto el nuevo expediente*/
                       /*Como segundo ahora agendaremos la cita*/
                       String idExpediente = expDAO.obtenerIdExpediente(idPaciente);/*aqui obtenemos el id del expediente*/
                       if (!idExpediente.equals("-")){ /*Aqui verificamos si se encontro el id del expediente del usuario*/
                           citasDAO citaDao = new citasDAO();
                           citasModel nuevaCita = new citasModel();
                           nuevaCita.setIdCita(Utilidades.generadorId(3));
                           nuevaCita.setIdExpedienteCita(idExpediente);
                           nuevaCita.setIdDoctor_Cita(SessionManager.getInstance().getIdDoctor());
                           /*ahora convertiremos el valor localDate a date*/
                           Date fechaAgendada =  Date.from(fechaCita.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                           nuevaCita.setFechaCita(fechaAgendada);
                           nuevaCita.setHoraCita(horaCita.getSelectionModel().getSelectedItem().toString());
                           if (citaDao.insertarCita(nuevaCita) > 0){
                               alerta.alertaCorrecto("INFORMATION","Correcto al Agendar","La cita ha sido registrada y creado un expediente!");
                           }else {
                               alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada!");
                           }
                       }else {
                           alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada dado que no se encontro el expediente!");
                       }
                   }else {
                       alerta.alertaError("ERROR","Error al Agendar","La cita no ha sido registrada dado que no se inserto el expediente!");
                   }


               }

           }
        }
    }
}
