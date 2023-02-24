package ma.enset.agent;


import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientAgent extends GuiAgent {
    private ClientGUI clientGUI;

    @Override
    protected void setup() {
        clientGUI = (ClientGUI) getArguments()[0];
        clientGUI.setClientAgent(this);
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("ServeurAgent", AID.ISLOCALNAME));
        message.setContent(guiEvent.getParameter(0).toString());
        send(message);
    }


}
