module com.example.recaptcha {
    requires javafx.controls;
	requires javafx.graphics;
    requires javafx.fxml;
	requires javafx.media;
	
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.recaptcha to javafx.graphics, javafx.fxml;
    exports com.example.recaptcha;
}