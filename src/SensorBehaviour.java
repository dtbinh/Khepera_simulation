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
import java.util.Map;

import java.lang.Math;
import java.util.Random;

public class SensorBehaviour extends CyclicBehaviour {
    /* last measured value */
    private int pv;
    /* difference between the two last measured values */
    private int delta_pv;
    /* time interval between two measure */
    private int timestamp;
    /* TODO */
    private int status_word;

    public void action() {
	int current_pv;

	/* measure the current value */
	// RANDOM
	current_pv = (int)(Math.random() * 100); // replace by a real value 

	/* update internal variables */
	this.delta_pv = Math.abs(this.pv - current_pv);

	/* send message */
	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.addReceiver(new AID("Khepera_controller", AID.ISLOCALNAME)); // TODO

	/* json formating */
	JSONObject json_msg = new JSONObject();
	json_msg.put("pv", current_pv);
	json_msg.put("delta_pv", this.delta_pv);
	json_msg.put("timestamp", this.timestamp);
	json_msg.put("status_word", this.status_word);

	msg.setContent(json_msg.toString());
	myAgent.send(msg);

	/* save the current pv */
	this.pv = current_pv;

	/* */
	try {
	    Thread.sleep(3000);
	} catch(InterruptedException e) {
	    System.out.println("osef");
	}
    }
}
