module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;
                        
    opens controller to javafx.fxml;
    opens model to org.hibernate.orm.core;

    exports model;
    exports main;
    opens controller.user to javafx.fxml;
}