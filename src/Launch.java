import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Launch {
    public static void main(String[] args) {
	jade.core.Runtime runtime = jade.core.Runtime.instance();
	Profile profile = null;

	try {
	    profile = new ProfileImpl("config.txt");
	    AgentContainer container = runtime.createMainContainer(profile);
	    AgentController ac = container.createNewAgent(
								 "Khepera_sensor",
								 "Khepera_sensor",
								 null
								 );
	    ac.start();

	    ac = container.createNewAgent(
								 "Khepera_controller",
								 "Khepera_controller",
								 null
								 );
	    ac.start();
	} catch(Exception ex) {	// TODO(tewfik): catcher plus precisement
	    System.out.println(ex.getMessage());
	}
    }
}
