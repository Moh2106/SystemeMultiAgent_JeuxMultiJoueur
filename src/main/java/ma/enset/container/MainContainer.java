package ma.enset.container;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

public class MainContainer {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.GUI, "true");

        runtime.createMainContainer(profile);
    }
}
