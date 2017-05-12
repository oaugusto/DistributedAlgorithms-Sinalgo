package projects.pa2.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;

import projects.pa2.nodes.messages.DisseminationMsg;
import projects.pa2.nodes.messages.MessageTwo;
import projects.pa2.nodes.timers.TimerTwo;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.edges.Edge;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class NodeTwo extends Node{
	
	public boolean stopSimulation = false;
	
	private int round;

	private double coefLocal;
	private double coefGlobal;
		
	private double connectedTriplets;
	private double triangles;
	
	private double totalConnectedTriplets;
	private double totalTriangles;
	
	private ArrayList<Integer> neighbors; 
	
	private boolean newMsg;
	
	private Hashtable<Integer, DisseminationMsg> msgReceived = new Hashtable<>();
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message msg = inbox.next();
			
			if (msg instanceof MessageTwo) {
				MessageTwo recv = (MessageTwo)msg;
				for (Integer n : recv.data) {
					if (neighbors.contains(n)){
						this.triangles++;
					}
				}
			}
			
			if (msg instanceof DisseminationMsg) {
				DisseminationMsg rcv = (DisseminationMsg)msg;
				Integer newID = new Integer(rcv.id);
				if (!msgReceived.containsKey(newID)) {
					msgReceived.put(newID, rcv);
					this.totalConnectedTriplets += rcv.connectedTriplets;
					this.totalTriangles += rcv.triangles;
					broadcast(rcv);
					
					this.newMsg = true;
				}
			}
		}
	}

	@Override
	public void preStep() {
		this.newMsg = false;
	}

	@Override
	public void init() {
		this.stopSimulation = false;
		this.triangles = 0;
		this.connectedTriplets = 0;
		this.totalConnectedTriplets = 0;
		this.totalTriangles = 0;
		this.neighbors = new ArrayList<>();
		this.coefLocal = 0;
		this.coefGlobal = 0;
		this.round = 1;	
	}

	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStep() {
		switch (this.round) {
		case 1:
			//add all neighbors to array
			for (Edge e : outgoingConnections) {
				neighbors.add(e.endNode.ID);
			}					
			
			//calculate the number of possible triangles
			int k = this.neighbors.size();
			this.connectedTriplets = ((k - 1) * (k));
			
			broadcast(new MessageTwo(this.neighbors));
			break;
			
		case 2:
			System.out.println(this.triangles);
			if (this.connectedTriplets != 0) {
				this.coefLocal = this.triangles / this.connectedTriplets;
			} else {
				this.coefLocal = 0;
			}
			broadcast(new DisseminationMsg(this.ID, this.triangles, this.connectedTriplets));
			break;
			
		default:
			//System.out.println("NODE (" + ID + ")");
			if (this.totalConnectedTriplets != 0) {
				this.coefGlobal = this.totalTriangles / this.totalConnectedTriplets;
			} else {
				this.coefGlobal = 0;
			}
			if (!this.newMsg) {
				TimerTwo finishSimulation = new TimerTwo(this);
				finishSimulation.startRelative(this.msgReceived.size(), this);
			}
			break;
		}
		
		this.round++;
	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {		
		String text = " L: " + String.format("%.2f",this.coefLocal)
							+ " G: " + String.format("%.2f",this.coefGlobal);
		//" ID: "+ Integer.toString(this.ID)
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, text, 16, Color.YELLOW);
	}

}