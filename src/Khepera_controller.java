import jade.core.Agent;

public class Khepera_controller extends Agent {
    protected void setup() {
	System.out.println("Controller creation!");

	this.addBehaviour(new ControllerBehaviour());
    }
}
