package projects.pa3.nodes.timers;

import projects.pa3.nodes.nodeImplementations.NodeThree;
import sinalgo.nodes.timers.Timer;

public class TimerThree extends Timer{

public NodeThree node; 
	
	public TimerThree(NodeThree node) {
		this.node = node;
	}
	
	@Override
	public void fire() {
		this.node.stopSimulation = true;
	}

}
