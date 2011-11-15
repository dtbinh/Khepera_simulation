/*
 * TODO: 
 * - parse json_content
 * - determine which action to execute
 * - send message to actuator
 *
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ControllerBehaviour extends CyclicBehaviour {
    public void action() {
	ACLMessage msg = this.myAgent.receive();
	if (msg != null) {
	    String json_content = msg.getContent();

	    System.out.println("Controller: " + json_content);
	}
	else {
	    block();
	}
    }
}
