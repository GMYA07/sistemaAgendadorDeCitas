package www.sistema.agendador.sistemaagendadorcitas.src;

public class SessionManager {

    private static SessionManager instance;
    private String idDoctor; // O cualquier otro dato de usuario que necesites

    private SessionManager() { }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }
}
