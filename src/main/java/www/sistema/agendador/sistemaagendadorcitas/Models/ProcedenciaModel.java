package www.sistema.agendador.sistemaagendadorcitas.Models;

import java.util.List;

public class ProcedenciaModel {

    private String idProcedencia;
    private String procedencia;

    /*constructorVacio*/
    public ProcedenciaModel(){};
    /*constructor*/
    public ProcedenciaModel(String idProcedencia, String procedencia){
        this.idProcedencia = idProcedencia;
        this.procedencia = procedencia;
    }
    @Override
    public String toString() {
        return procedencia;
    }

    public String getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(String idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }
}
