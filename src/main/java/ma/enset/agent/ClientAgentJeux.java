package ma.enset.agent;


import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class ClientAgentJeux extends GuiAgent {
    private ClientGUIJeux clientGUI;

    @Override
    protected void setup() {
        clientGUI = (ClientGUIJeux) getArguments()[0];
        clientGUI.setClientAgent(this);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null){
                    clientGUI.showMessageClient(message.getContent());
                }
            }
        });
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("ServeurAgentJeux", AID.ISLOCALNAME));
        message.setContent(guiEvent.getParameter(0).toString());
        send(message);
    }


}
