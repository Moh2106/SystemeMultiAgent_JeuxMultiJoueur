package ma.enset.agent;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerGUIJeux extends Application {
    private ObservableList<String> data = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();
        BorderPane root = new BorderPane();
        ListView<String> list = new ListView<>(data);
        root.setCenter(list);
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("SERVEUR");
        stage.setScene(scene);
        stage.show();
    }

    private void startContainer() throws StaleProxyException {
       Runtime runtime = Runtime.instance();
       ProfileImpl profile = new ProfileImpl();
       profile.setParameter(Profile.GUI, "true");
       AgentContainer container = runtime.createAgentContainer(profile);
       AgentController agent =container.createNewAgent("ServeurAgentJeux", "ma.enset.agent.ServeurAgentJeux", new Object[]{this});
       agent.start();
    }

    public void showMessage(String message){
        data.add(message);
    }
}
