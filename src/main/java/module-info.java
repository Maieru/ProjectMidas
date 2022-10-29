module br.com.fesa.projectmidas.controllers {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.fesa.projectmidas.controllers to javafx.fxml;
    exports br.com.fesa.projectmidas;
}
