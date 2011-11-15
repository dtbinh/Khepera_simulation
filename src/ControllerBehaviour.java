/*
 * TODO: 
 * - determine which action to execute
 * - send message to actuator
 * - optimisation: reuse the same json_parser, don't recreate it
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ControllerBehaviour extends CyclicBehaviour {
    public void action() {
	/* receive the message */
	ACLMessage msg = this.myAgent.receive();
	if (msg != null) {
	    /* parse the json message */

	    // to optimize performance we can avoid this object
	    // creation and reuse an existing one.
	    JSONParser parser = new JSONParser();
	    Object parsed_content = null;

	    try {
		parsed_content = (JSONObject)parser.parse(msg.getContent());
	    } catch(ParseException e) {
		System.out.println("Controller:error:" + e);
	    }

	    JSONObject json_content = (JSONObject)parsed_content;

	    /* access and process the received values from the sensor */
	    System.out.println("Controller: pv = " + json_content.get("pv"));
	}
	else {
	    block();
	}
    }
}
