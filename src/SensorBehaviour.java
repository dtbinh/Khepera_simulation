/*
 * TODO:
 * - use TickerBehaviour instead of CyclicBehaviour
 * - calculate the value
 * - choose receiver
*/

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Map;

import java.lang.Math;
import java.util.Random;

public class SensorBehaviour extends CyclicBehaviour {
    /* world representation */
    private World world;

    /* last measured value */
    private long pv1;
    private long pv2;
    /* difference between the two last measured values */
    private long delta_pv1;
    private long delta_pv2;
    /* time interval between two measure (in seconds) */
    private long timestamp;
    /* TODO */
    private long status_word;

    public SensorBehaviour(World world) {
	super();
	this.timestamp = 1;	// TODO: define it in a separate config file

	this.world = world;
    }

    public void action() {
	long current_pv1;
	long current_pv2;

	/* measure the current value */
	// RANDOM
	Random generator = new Random();
	current_pv1 = this.world.getPos();
	//current_pv1 = generator.nextInt(100); // replace by a real value 
	current_pv2 = generator.nextInt(100); // replace by a real value 

	/* update internal variables */
	this.delta_pv1 = Math.abs(this.pv1 - current_pv1);
	this.delta_pv2 = Math.abs(this.pv2 - current_pv2);

	/* send message */
	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.addReceiver(new AID("Khepera_controller", AID.ISLOCALNAME)); // TODO

	/* json formating */
	JSONObject json_msg = new JSONObject();
	json_msg.put("pv1", current_pv1);
	json_msg.put("pv2", current_pv2);
	json_msg.put("delta_pv1", this.delta_pv1);
	json_msg.put("delta_pv2", this.delta_pv2);
	json_msg.put("timestamp", this.timestamp);
	json_msg.put("status_word", this.status_word);

	msg.setContent(json_msg.toString());
	myAgent.send(msg);

	/* save the current pv */
	this.pv1 = current_pv1;
	this.pv2 = current_pv2;

	/* */
	try {
	    Thread.sleep(timestamp * 1000);
	} catch(InterruptedException e) {
	    System.out.println("osef");
	}
    }


    private void move(long move) {
	this.world.move(move);

	/* redraw */
    }
}
