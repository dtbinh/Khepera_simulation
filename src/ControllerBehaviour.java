/*
 * TODO: 
 * - determine which action to execute
 * - send message to actuator
 * - optimisation: reuse the same json_parser, don't recreate it
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class ControllerBehaviour extends CyclicBehaviour {
    /**
     * Last pv sent by the sensor.
     */
    private Long pv1;
    private Long pv2;

    /**
     * Last timestamp sent by the sensor.
     */
    private Long timestamp;

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
	    this.pv1 = (Long)json_content.get("pv1");
	    this.pv2 = (Long)json_content.get("pv2");
	    this.timestamp = (Long)json_content.get("timestamp");
	    System.out.println("Controller: pv1 = " + this.pv1
			       + ", pv2 = " + this.pv2
			       + ", timestamp = " + this.timestamp
			       );

	    /* send order to actuor */
	    long order = this.determineOrder();
	    this.sendOrder(order, this.timestamp);
	}
	else {
	    block();
	}
    }

    private long determineOrder() {
	long order = 0;

	if(this.pv1 > 0) {
	    order = -1;
	} else if(this.pv1 < 0) {
	    order = 1;
	}

	return order;
    }

    private void sendOrder(long order, Long timestamp) {
	/* send message */
	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.addReceiver(new AID("Khepera_sensor", AID.ISLOCALNAME)); // TODO

	/* json formating */
	JSONObject json_msg = new JSONObject();
	json_msg.put("u", order);
	json_msg.put("timestamp", this.timestamp);

	msg.setContent(json_msg.toString());
	myAgent.send(msg);
    }
}
