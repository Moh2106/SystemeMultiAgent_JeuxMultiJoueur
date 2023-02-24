package ma.enset.simpleCommunication;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if(message != null){
                    System.out.println(message.getSender().getName() + " " + message.getContent());
                    ACLMessage messageReponse = new ACLMessage(ACLMessage.INFORM);
                    messageReponse.addReceiver(message.getSender());
                    messageReponse.setContent("Le message a été bien recu");
                    send(messageReponse);
                }
                else block();
            }
        });
    }
}
