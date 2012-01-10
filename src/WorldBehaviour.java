/*
 * TODO:
 * - create a AID[50][50] or string[50][50] in the constructor
 * - initialize the position of khepera agents
 * - receive request (
 *	type_request : get_position, move, distance
 *	args : ...
 * - answer : int, bool, (int, int)
 */

import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class WorldBehaviour extends CyclicBehaviour {
    /**
     * The position of the agent in the World.
     */
    private long pos;

    public WorldBehaviour() {
	super();

	/* We initialize the position at 50.
	 * The goal of the simulation is to move the agent to the
	 * position 0.
	 */
	this.pos = 50;

	/* draw interface */
    }

    public void action() {
	/* receive a message */
	ACLMessage msg = this.myAgent.receive();
	if (msg != null) {
	    long move = 0;
	    String action = "";

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
	    action = (String)json_content.get("action");

	    if(action == "move") {
		move = (Long)json_content.get("move");
	    } else if(action == "get_pos") {
		this.sendPos();
	    }

	    this.move(move);
	}
	else {
	    block();
	}
    }

    private void move(long move) {
	this.pos += move;

	/* redraw */
    }

    private void sendPos() {
	/* send message */
	ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.addReceiver(new AID("Khepera_sensor", AID.ISLOCALNAME)); // TODO

	/* json formating */
	JSONObject json_msg = new JSONObject();
	json_msg.put("pos", this.pos);

	msg.setContent(json_msg.toString());
	myAgent.send(msg);
    }
}
