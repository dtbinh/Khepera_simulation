import jade.core.Agent;

public class Khepera_sensor extends Agent {
    protected void setup() {
	System.out.println("Sensor creation!");

	this.addBehaviour(new SensorBehaviour());
    }
}
