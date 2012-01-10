/**
 * @author Tewfik Sadaoui (tewfik.sadaoui@gmail.com)
 * @date 2011-12-05
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ActuatorBehaviour extends CyclicBehaviour {
    private int lastMove;
    private World world;

    public ActuatorBehaviour(World world) {
	super();

	this.world = world;
	this.lastMove = 0;
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

	    /* access and process the received values from the controller */
	    try {
		long u = (Long)json_content.get("u");
		System.out.println("Actuator: u = " + u
				   + ", timestamp = " + json_content.get("timestamp")
				   );

		/* actualize the state of the world */
		this.world.move(u);
	    } catch(java.lang.NullPointerException e) {
		System.out.println("no value");
	    }
	}
	else {
	    block();
	}
     }
}
