module main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens plop to javafx.fxml;
    exports plop;
    requires java.datatransfer;
    requires java.desktop;

}