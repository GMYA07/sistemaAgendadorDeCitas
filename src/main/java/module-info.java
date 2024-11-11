module www.sistema.agendador.sistemaagendadorcitas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires java.desktop; // Necesario para la reflexión de las propiedades


    opens www.sistema.agendador.sistemaagendadorcitas to javafx.fxml;
    opens www.sistema.agendador.sistemaagendadorcitas.Models to javafx.base; // Abre el paquete Models a javafx.base
    exports www.sistema.agendador.sistemaagendadorcitas;
    exports www.sistema.agendador.sistemaagendadorcitas.Controllers;
    opens www.sistema.agendador.sistemaagendadorcitas.Controllers to javafx.fxml;
}