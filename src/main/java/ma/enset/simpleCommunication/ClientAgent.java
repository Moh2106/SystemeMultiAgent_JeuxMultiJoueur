package ma.enset.simpleCommunication;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("ServeurAgent", AID.ISLOCALNAME));
        message.setContent("Message pour une demande");
        send(message);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message1 = receive();
                if (message1 != null){
                    System.out.println(message1.getSender().getName() + " " + message1.getContent());
                }else{
                    block();
                }

            }
        });

    }
}
