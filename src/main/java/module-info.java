module www.sistema.agendador.sistemaagendadorcitas {
    requires javafx.controls;
    requires javafx.fxml;


    opens www.sistema.agendador.sistemaagendadorcitas to javafx.fxml;
    exports www.sistema.agendador.sistemaagendadorcitas;
    exports www.sistema.agendador.sistemaagendadorcitas.Controllers;
    opens www.sistema.agendador.sistemaagendadorcitas.Controllers to javafx.fxml;
}