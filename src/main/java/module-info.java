module tp1 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jade;

    opens ma.enset to javafx.fxml;
    opens ma.enset.agent to javafx.fxml, javafx.graphics, jade;
    opens ma.enset.simpleCommunication to javafx.fxml, javafx.graphics, jade;

    exports ma.enset to javafx.fxml;
    exports ma.enset.agent to javafx.fxml, javafx.graphics, jade;
    exports ma.enset.simpleCommunication to javafx.fxml, javafx.graphics, jade;
}