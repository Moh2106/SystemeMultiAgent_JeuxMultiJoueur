package ma.enset.agent;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class ServeurAgentJeux extends GuiAgent {
    ServerGUIJeux serverGUI ;
    @Override
    protected void setup() {
        serverGUI = (ServerGUIJeux) getArguments()[0];
        List<String> listJoueur = new ArrayList<>();
        List<AID> listClient = new ArrayList<>();

        int nombre_magique = (int) (Math.random()*100);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();

                if(message != null){
//                    recuperation de l'identifiant du nom l'emetteur
                    String nomJoueur = message.getSender().getName().split("@")[0];
                    AID identifiant = message.getSender();


                    if (! (listClient.contains(identifiant))){
                        listJoueur.add(nomJoueur);
                        listClient.add(identifiant);
                    }


//                  afficher le contenu du message et le nom du client dans l'interface graphique du serveur
                    serverGUI.showMessage(message.getContent() + " " + nomJoueur);

//                    Convertir le message recu en int
                    int convertiMessage = Integer.parseInt(message.getContent());
                    ACLMessage response = new ACLMessage(ACLMessage.INFORM);

                    if(convertiMessage < nombre_magique){
                        response.addReceiver(message.getSender());
                        response.setContent("Plus");
                        send(response);
                    } else if (convertiMessage > nombre_magique) {
                        response.addReceiver(message.getSender());
                        response.setContent("Moins");
                        send(response);
                    } else {
                        response.addReceiver(message.getSender());
                        AID joueurGagne = message.getSender();
                        response.setContent("Bravo vous avez trouvé le nombre magique");
                        send(response);


                        for (AID aid: listClient) {

                            if (!(aid.equals(joueurGagne))){
                                System.out.println("Client perdant");
                                System.out.println(aid);
                                response.addReceiver(aid);
                                response.setContent("Le joueur "+ nomJoueur + " a trouvé le nombre maqique qui est : " + nombre_magique + "\n" + "Fin du Jeux");
                                send(response);
                            }
                            else continue;
                        }
                    }

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
