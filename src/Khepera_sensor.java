import jade.core.Agent;

public class Khepera_sensor extends Agent {
    protected void setup() {
	System.out.println("Sensor creation!");

	World world = new World();
	this.addBehaviour(new SensorBehaviour(world));
	this.addBehaviour(new ActuatorBehaviour(world));
    }
}
