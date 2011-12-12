import jade.core.Agent;

public class World extends Agent {
    protected void setup() {
	System.out.println("World creation!");

	this.addBehaviour(new WorldBehaviour());
    }
}
