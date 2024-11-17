package www.sistema.agendador.sistemaagendadorcitas.src;

import javafx.scene.control.Alert;
import www.sistema.agendador.sistemaagendadorcitas.Models.ProcedenciaModel;
import www.sistema.agendador.sistemaagendadorcitas.Models.citasModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    Alertas alerta = new Alertas();

    public boolean validarLogin(String correo, String password){

        if (correo.isEmpty() || password.isEmpty()){ /*en este if se evalua si uno de los campos esta vacio*/
            alerta.alertaError("ERROR","Error al Iniciar Sesion","Alguno de los campos esta vacio");
            return false;
        }else {
            if (!correo.contains("@")){
                alerta.alertaAtencion("WARNING","Error al Iniciar Sesion","No se ha ingresado un formato de correo!");
                return false;
            }else {
                return true;
            }
        }

    }
    public boolean validarAgendarCita(String dui, String hora, LocalDate fecha){
        if (dui.isEmpty() || hora.isEmpty() || fecha == null){ /*en este if se evalua si uno de los campos esta vacio*/
            alerta.alertaError("ERROR","Error al Agendar","Alguno de los campos esta vacio");
            return false;
        }else {
            if (!validarDui(dui)){
                alerta.alertaError("ERROR","Error al Agendar","el formato del Dui es erroneo");
                return false;
            }else {
                if (!validarHora(hora)){
                    alerta.alertaError("ERROR","Error al Agendar","el formato de la hora es erroneo");
                    return false;
                }else {
                    if (!validarFecha(fecha,2)) {
                        alerta.alertaError("ERROR","Error al Agendar","la fecha debe ser superior a la actual para agendar la cita");
                        return false;
                    }else {
                        return true;
                    }
                }
            }
        }
    }
    public boolean valiarFormFinalizarCita(String descripcionCita){
        if (descripcionCita.isEmpty()){
            alerta.alertaError("ERROR","Error al Finalizar","El campo de descripcion esta vacio");
            return false;
        }else {
            return true;
        }
    }
    public boolean validarFormAgregarDoctor(String duiDoctor, String Telefono, LocalDate fechaNacDoc, String nombresDoctor, String apellidosDoctor, String correoDoctor, String password, ProcedenciaModel procedencia, String Especialidad){
        if (duiDoctor.isEmpty() || Telefono.isEmpty() || fechaNacDoc == null || nombresDoctor.isEmpty() || apellidosDoctor.isEmpty() || correoDoctor.isEmpty() || password.isEmpty() || procedencia == null || Especialidad.isEmpty()){
            alerta.alertaError("ERROR","Error al Agregar","Alguno de los campos esta vacio");
            return false;
        }else {
            if (!validarDui(duiDoctor)){
                alerta.alertaError("ERROR","Error al Agregar","el formato del Dui es erroneo");
                return false;
            }else {
               if (!validarTelefono(Telefono)){
                   alerta.alertaError("ERROR","Error al Agregar","el formato de telefono es erroneo");
                   return false;
               }else {
                   if (!validarFecha(fechaNacDoc,0)){
                       alerta.alertaError("ERROR","Error al Agregar","la fecha ingresada no es permitida dado que necesitas 18 años para trabajar");
                       return false;
                   }else {
                       if (!correoDoctor.contains("@")){
                           alerta.alertaAtencion("WARNING","Error al Agregar","No se ha ingresado un formato de correo!");
                           return false;
                       }else {
                           return true;
                       }
                   }
               }
            }
        }

    }
    public boolean validarFormModificarDoctor(String duiDoctor, String Telefono, LocalDate fechaNacDoc, String nombresDoctor, String apellidosDoctor, String correoDoctor, ProcedenciaModel procedencia, String Especialidad, Integer Estado){
        if (duiDoctor.isEmpty() || Telefono.isEmpty() || fechaNacDoc == null || nombresDoctor.isEmpty() || apellidosDoctor.isEmpty() || correoDoctor.isEmpty()  || procedencia == null || Especialidad.isEmpty()){
            alerta.alertaError("ERROR","Error al Agregar","Alguno de los campos esta vacio");
            return false;
        }else {
            if (!validarDui(duiDoctor)){
                alerta.alertaError("ERROR","Error al Agregar","el formato del Dui es erroneo");
                return false;
            }else {
                if (!validarTelefono(Telefono)){
                    alerta.alertaError("ERROR","Error al Agregar","el formato de telefono es erroneo");
                    return false;
                }else {
                    if (!validarFecha(fechaNacDoc,0)){
                        alerta.alertaError("ERROR","Error al Agregar","la fecha ingresada no es permitida dado que necesitas 18 años para trabajar");
                        return false;
                    }else {
                        if (!correoDoctor.contains("@")){
                            alerta.alertaAtencion("WARNING","Error al Agregar","No se ha ingresado un formato de correo!");
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
            }
        }

    }
    public boolean validarFormAgregarPaciente(String duiPaciente,String Telefono,LocalDate fechaNacPaciente,String nombresPaciente,String apellidosPaciente,String correoPaciente,String password,ProcedenciaModel procedencia,String Descripcion){
        if (duiPaciente.isEmpty() || Telefono.isEmpty() || fechaNacPaciente == null || nombresPaciente.isEmpty() || apellidosPaciente.isEmpty() || correoPaciente.isEmpty() || password.isEmpty() || procedencia == null || Descripcion.isEmpty()){
            alerta.alertaError("ERROR","Error al Agregar","Alguno de los campos esta vacio");
            return false;
        }else {
            if (!validarDui(duiPaciente)){
                alerta.alertaError("ERROR","Error al Agregar","el formato del Dui es erroneo");
                return false;
            }else {
                if (!validarTelefono(Telefono)){
                    alerta.alertaError("ERROR","Error al Agregar","el formato de telefono es erroneo");
                    return false;
                }else {
                    if (!validarFecha(fechaNacPaciente,1)){
                        alerta.alertaError("ERROR","Error al Agregar","la fecha ingresada no es permitida dado que es superior a la actual");
                        return false;
                    }else {
                        if (!correoPaciente.contains("@")){
                            alerta.alertaAtencion("WARNING","Error al Agregar","No se ha ingresado un formato de correo!");
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
            }
        }

    }

    public boolean validarHorarioCita(List<citasModel> citaVerificar, String horaCitaRegistrar, LocalDate fechaCitaRegistrar){
        try {
            boolean existe = false;

            for (citasModel cita : citaVerificar){
                /*esta parte del cod une la hora y fecha para compararla mas adelante en el if*/
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fechaHoraRegistrar = LocalDateTime.parse(fechaCitaRegistrar + " " + horaCitaRegistrar, formatter);

                /*Transforma el formato de la base de datos a la escrita en el input*/
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime horaCita = LocalTime.parse(cita.getHoraCita().substring(0, 5), timeFormatter); /*el subsstring le quita los segundos a la hora q viene de la bdd q necesitamos quitar para poder comparar para la valicacion*/

                LocalDateTime fechaHoraVerificar = LocalDateTime.parse(cita.getFechaCita() + " " + horaCita, formatter);

                if (fechaHoraRegistrar.isEqual(fechaHoraVerificar)) { /*verifica si la hora y la fecha son iguales*/
                    System.out.println("Las fechas y horas son iguales.");
                    existe = true;
                    alerta.alertaAtencion("WARNING","Error al Agendar","Existe una Cita en la fecha y hora estipulada");
                    break;
                }
            }
            return existe;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validarDui(String dui){
        // Expresión regular para validar el formato del DUI
        String DUI_REGEX = "^\\d{8}-\\d$";

        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(DUI_REGEX);
        // Crear un matcher para el texto a evaluar
        Matcher matcher = pattern.matcher(dui);
        // Verificar si hay coincidencias
        return matcher.matches();
    }
    private boolean validarHora(String hora){
        String HORA_REGEX = "^(?:[01]\\d|2[0-3]):[0-5]\\d$";
        Pattern HORA_PATTERN = Pattern.compile(HORA_REGEX);
        return HORA_PATTERN.matcher(hora).matches();
    }
    private boolean validarTelefono(String telefono){
        String regex = "^\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }


    private boolean validarFecha(LocalDate fechaNac,int tipoUser){

        if (fechaNac.equals(LocalDate.now().minusYears(18)) || fechaNac.isBefore(LocalDate.now().minusYears(18)) && tipoUser == 0){ // Verificar si la fecha de nacimiento está dentro del rango permitido osea 18
            return true;
        }else if(fechaNac.isBefore(LocalDate.now()) && tipoUser == 1){ // si la fecha antes a la actual podra registrarlo
            return true;
        }else if (fechaNac.isAfter(LocalDate.now()) && tipoUser == 2){ //si la fecha es superior a la actual si pasara a la cita
            return true;
        }else {
            return false;
        }
    }


}
