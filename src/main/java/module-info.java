module br.com.fesa.projectmidas.controllers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens br.com.fesa.projectmidas.controllers to javafx.fxml;
    opens br.com.fesa.projectmidas.model to javafx.base;
    exports br.com.fesa.projectmidas;
}
