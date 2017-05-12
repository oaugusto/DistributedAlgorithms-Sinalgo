package projects.pa1.nodes.timers;

import projects.pa1.nodes.nodeImplementations.NodeOne;
import sinalgo.nodes.timers.Timer;

public class TimerOne extends Timer{

	public NodeOne node; 
	
	public TimerOne(NodeOne node) {
		this.node = node;
	}
	
	@Override
	public void fire() {
		this.node.stopSimulation = true;
	}

}
