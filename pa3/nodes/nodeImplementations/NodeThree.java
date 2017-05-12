package projects.pa3.nodes.nodeImplementations;

import java.awt.Color;
import java.awt.Graphics;

import projects.pa3.nodes.messages.MessageThree;
import projects.pa3.nodes.messages.WinnerMsg;
import sinalgo.configuration.WrongConfigurationException;
import sinalgo.gui.transformation.PositionTransformation;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;;

public class NodeThree extends Node{

	public boolean stopSimulation = false;
	
	private int phase;
	private int round;
	private int val;
	private boolean awake;
	private boolean winner;
	private boolean neighbor;
	
	private int independentSet;
	
	@Override
	public void handleMessages(Inbox inbox) {
		while (inbox.hasNext()) {
			Message msg = inbox.next();
			
			if (msg instanceof MessageThree) {
				MessageThree recv = (MessageThree)msg;
				if (this.round == 2) {
					if (recv.data > this.val) {
						this.winner = false;
					}
				}				
			}
			
			if (msg instanceof WinnerMsg) {
				if (this.round == 3) {
					this.neighbor = true;
				}
			}
		}
	}

	@Override
	public void preStep() {
			
	}

	@Override
	public void init() {
		this.independentSet = 0;
		this.phase = 1;
		this.round = 1;
		this.val = ID;
		this.awake = true;
		this.winner = false;
		this.neighbor = false;
	}

	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStep() {
		if (this.awake) {
			switch (this.round) {
				case 1:
					broadcast(new MessageThree(this.val));
					this.winner = true;
					System.out.println("Node(" + ID + ")  [PHASE] [" + this.phase + "]");
					break;
				case 2:
					if (this.winner) {
						broadcast(new WinnerMsg());
						this.independentSet = 1;
						setColor(Color.GREEN);
					}
					break;
				case 3:
					if (this.winner || this.neighbor) {
						this.awake = false;
						
						this.stopSimulation = true;
					}
					this.phase++;
					break;
				default:
					System.out.println("[ERROR] incorrect value");
					break;
			}
			
			this.round = (this.round % 3) + 1;
		}
	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g, PositionTransformation pt, boolean highlight) {		
		String text = " ID: "+ Integer.toString(this.ID)
						+ " inSet: " + Integer.toString(this.independentSet);
		
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, text, 16, Color.YELLOW);
	}

}
