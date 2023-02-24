package ma.enset.agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class ServeurAgent extends GuiAgent {
    ServerGUI serverGUI ;
    @Override
    protected void setup() {
        serverGUI = (ServerGUI) getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();

                if(message != null){
                    serverGUI.showMessage(message.getContent());
                }else {
                    block();
                }
            }
        });

    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
