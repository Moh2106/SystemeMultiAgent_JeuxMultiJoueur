package ma.enset.container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SimpleContainer1 {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer container = runtime.createAgentContainer(profile);
        AgentController agent = container.createNewAgent("ClientAgent", "ma.enset.simpleCommunication.ClientAgent", new Object[]{});
        //AgentController agent1 = container.createNewAgent("ClientAgent1", "ma.enset.agent.ClientAgent", new Object[]{});
        agent.start();
    }
}
