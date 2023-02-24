package ma.enset.agent;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClientGUI extends Application {
    ObservableList<String> data = FXCollections.observableArrayList();
    private ClientAgent clientAgent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();
        BorderPane root = new BorderPane();
        ListView<String> list = new ListView<>(data);
        TextField textField = new TextField();
        Button button = new Button("Send");
        Label labelMessage = new Label("Message");
        HBox hBox = new HBox(labelMessage, textField, button);
        root.setBottom(hBox);
        root.setCenter(list);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();

        button.setOnAction(actionEvent -> {
            String message = textField.getText();

            GuiEvent guiEvent = new GuiEvent(this, 1);
            guiEvent.addParameter(message);
            clientAgent.onGuiEvent(guiEvent);

            data.add(message);
            textField.setText("");
        });

    }

    public void startContainer() throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.GUI, "true");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController agent = agentContainer.createNewAgent("ClientAgent", "ma.enset.agent.ClientAgent", new Object[]{this});
        agent.start();
    }

    public void setClientAgent(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }
}
