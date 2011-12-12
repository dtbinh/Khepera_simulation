/*
 * TODO:
 * - create a AID[50][50] or string[50][50] in the constructor
 * - initialize the position of khepera agents
 * - receive request (
 *	type_request : get_position, move, distance
 *	args : ...
 * - answer : int, bool, (int, int)
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class WorldBehaviour extends CyclicBehaviour {
    public void action() {
	/* receive a message */
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

	    /* access and process the received values */

	    // this.pv1 = (Long)json_content.get("pv1");
	    // this.pv2 = (Long)json_content.get("pv2");
	    // this.timestamp = (Long)json_content.get("timestamp");
	    // System.out.println("Controller: pv1 = " + this.pv1
	    // 		       + ", pv2 = " + this.pv1
	    // 		       + ", timestamp = " + this.timestamp
	    // 		       );

	    /* answer */

	    // int order = this.determineOrder();
	    // this.sendOrder(order, this.timestamp);
	}
	else {
	    block();
	}
    }

}
