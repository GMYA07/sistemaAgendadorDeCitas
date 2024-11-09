package www.sistema.agendador.sistemaagendadorcitas.Models;

import java.util.Date;

public class DoctorModel {
    private String idDoctor;
    private String duiDoctor;
    private String nombresDoctor;
    private String apellidosDoctor;
    private Date fechaNacDoctor;
    private String telefonoDoctor;
    private String correoDoctor;
    private String passwordDoctor;
    private String procedenciaDoctor;
    private String especialidadDoctor;
    private Integer estadoDoctor;


    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getDuiDoctor() {
        return duiDoctor;
    }

    public void setDuiDoctor(String duiDoctor) {
        this.duiDoctor = duiDoctor;
    }

    public String getNombresDoctor() {
        return nombresDoctor;
    }

    public void setNombresDoctor(String nombresDoctor) {
        this.nombresDoctor = nombresDoctor;
    }

    public String getApellidosDoctor() {
        return apellidosDoctor;
    }

    public void setApellidosDoctor(String apellidosDoctor) {
        this.apellidosDoctor = apellidosDoctor;
    }

    public Date getFechaNacDoctor() {
        return fechaNacDoctor;
    }

    public void setFechaNacDoctor(Date fechaNacDoctor) {
        this.fechaNacDoctor = fechaNacDoctor;
    }

    public String getTelefonoDoctor() {
        return telefonoDoctor;
    }

    public void setTelefonoDoctor(String telefonoDoctor) {
        this.telefonoDoctor = telefonoDoctor;
    }

    public String getCorreoDoctor() {
        return correoDoctor;
    }

    public void setCorreoDoctor(String correoDoctor) {
        this.correoDoctor = correoDoctor;
    }

    public String getPasswordDoctor() {
        return passwordDoctor;
    }

    public void setPasswordDoctor(String passwordDoctor) {
        this.passwordDoctor = passwordDoctor;
    }

    public String getProcedenciaDoctor() {
        return procedenciaDoctor;
    }

    public void setProcedenciaDoctor(String procedenciaDoctor) {
        this.procedenciaDoctor = procedenciaDoctor;
    }

    public String getEspecialidadDoctor() {
        return especialidadDoctor;
    }

    public void setEspecialidadDoctor(String especialidadDoctor) {
        this.especialidadDoctor = especialidadDoctor;
    }

    public Integer getEstadoDoctor() {
        return estadoDoctor;
    }

    public void setEstadoDoctor(Integer estadoDoctor) {
        this.estadoDoctor = estadoDoctor;
    }
}
