package projects.pa1.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import projects.defaultProject.nodes.timers.MessageTimer;
import projects.pa1.nodes.messages.DisseminationMessage;
import projects.pa1.nodes.messages.MessageOne;
import projects.pa1.nodes.timers.TimerOne;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class NodeOne extends Node{
	
	public boolean stopSimulation = false;
	
	private ArrayList<Integer> ids; 
	
	private boolean newMsg;
	
	MessageTimer initMsg;
	private int totalNodes;
	public int netDiameter;
	
	@Override
	public void handleMessages(Inbox inbox) {
		
		while (inbox.hasNext()) {
			Message msg = inbox.next();
			
			if (msg instanceof MessageOne) {
				MessageOne recv  = (MessageOne)msg;
				
				for (Integer i: recv.data) {
					if (!ids.contains(i)) {
						newMsg = true;
						ids.add(i);
						totalNodes++;
					}	
				}
				
			}
			
			if (msg instanceof DisseminationMessage) {
				DisseminationMessage recv  = (DisseminationMessage)msg;
				
				if (recv.data > this.netDiameter) {
					this.netDiameter = recv.data;
					broadcast(new DisseminationMessage(this.netDiameter));
				}
			}
		}
	}

	@Override
	public void preStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		this.totalNodes = 1;
		this.netDiameter = -1;
		this.ids = new ArrayList<Integer>();
		this.ids.add(ID);
		this.newMsg = true;
		this.stopSimulation = false;
		
		
	}

	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStep() {
		// TODO Auto-generated method stub
		if (!stopSimulation) {
			if (newMsg) {
				this.netDiameter++;
				broadcast(new MessageOne(ids));
				newMsg = false;
			} else {
				broadcast(new DisseminationMessage(this.netDiameter));
				TimerOne finishSimulation = new TimerOne(this);
				finishSimulation.startRelative(this.totalNodes, this);
			}
		}
	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {		
		String text = " ID: "+ Integer.toString(this.ID) + " N: " 
					+ Integer.toString(this.totalNodes) + " D: " 
					+ Integer.toString(this.netDiameter);
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, text, 16, Color.YELLOW);
	}

}
