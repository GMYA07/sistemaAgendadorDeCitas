package www.sistema.agendador.sistemaagendadorcitas.Models;

import java.sql.Time;
import java.util.Date;

public class citasModel {
    private String idCita;
    private String idExpedienteCita;
    private String idDoctor_Cita;
    private Date fechaCita;
    private String horaCita;
    private String descripcionCita;
    private Integer estadoCita;

    public String getIdExpedienteCita() {
        return idExpedienteCita;
    }

    public void setIdExpedienteCita(String idExpedienteCita) {
        this.idExpedienteCita = idExpedienteCita;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getDescripcionCita() {
        return descripcionCita;
    }

    public void setDescripcionCita(String descripcionCita) {
        this.descripcionCita = descripcionCita;
    }

    public Integer getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(Integer estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getIdDoctor_Cita() {
        return idDoctor_Cita;
    }

    public void setIdDoctor_Cita(String idDoctor_Cita) {
        this.idDoctor_Cita = idDoctor_Cita;
    }
}
