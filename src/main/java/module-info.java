module cypherfy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires java.desktop;

    opens cypherfy to javafx.fxml;
    exports cypherfy;
    exports backend;
}