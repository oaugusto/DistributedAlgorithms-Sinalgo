package projects.pa2.nodes.timers;

import projects.pa2.nodes.nodeImplementations.NodeTwo;
import sinalgo.nodes.timers.Timer;

public class TimerTwo extends Timer{

public NodeTwo node; 
	
	public TimerTwo(NodeTwo node) {
		this.node = node;
	}
	
	@Override
	public void fire() {
		this.node.stopSimulation = true;
	}

}
