module com.example.recaptcha {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.recaptcha to javafx.fxml;
    exports com.example.recaptcha;
}