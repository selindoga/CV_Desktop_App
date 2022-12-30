module com.example.cv_desktop_app {
    requires javafx.controls;
    requires javafx.fxml;

    /*
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;


     */
    opens com.example.cv_desktop_app to javafx.fxml;
    exports com.example.cv_desktop_app;
}
