package www.sistema.agendador.sistemaagendadorcitas.Models;

import java.util.Date;

public class PacienteModel {
    private String idPaciente;
    private String duiPaciente;
    private String nombresPaciente;
    private String apellidosPaciente;
    private Date fechaNacPaciente;
    private String telefonoPaciente;
    private String correoPaciente;
    private String passwordPaciente;
    private String procedenciaPaciente;
    private String descripcionPaciente;
    private Integer estadoPaciente;

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDuiPaciente() {
        return duiPaciente;
    }

    public void setDuiPaciente(String duiPaciente) {
        this.duiPaciente = duiPaciente;
    }

    public String getNombresPaciente() {
        return nombresPaciente;
    }

    public void setNombresPaciente(String nombresPaciente) {
        this.nombresPaciente = nombresPaciente;
    }

    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }

    public Date getFechaNacPaciente() {
        return fechaNacPaciente;
    }

    public void setFechaNacPaciente(Date fechaNacPaciente) {
        this.fechaNacPaciente = fechaNacPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

    public String getPasswordPaciente() {
        return passwordPaciente;
    }

    public void setPasswordPaciente(String passwordPaciente) {
        this.passwordPaciente = passwordPaciente;
    }

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
    }

    public String getProcedenciaPaciente() {
        return procedenciaPaciente;
    }

    public void setProcedenciaPaciente(String procedenciaPaciente) {
        this.procedenciaPaciente = procedenciaPaciente;
    }

    public String getDescripcionPaciente() {
        return descripcionPaciente;
    }

    public void setDescripcionPaciente(String descripcionPaciente) {
        this.descripcionPaciente = descripcionPaciente;
    }

    public Integer getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(Integer estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }
}
