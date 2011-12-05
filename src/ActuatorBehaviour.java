/**
 * @author Tewfik Sadaoui (tewfik.sadaoui@gmail.com)
 * @date 2011-12-05
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ActuatorBehaviour extends CyclicBehaviour {
    public ActuatorBehaviour () {
	super();
    }

    public void action() {
	/* receive message*/
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
		System.out.println("Actuator:error:" + e);
	    }

	    JSONObject json_content = (JSONObject)parsed_content;

	    /* access and process the received values from the sensor */
	    System.out.println("Actuator: u = " + json_content.get("u")
			       + ", timestamp = " + json_content.get("timestamp")
			       );
	}
	else {
	    block();
	}
     }
}
