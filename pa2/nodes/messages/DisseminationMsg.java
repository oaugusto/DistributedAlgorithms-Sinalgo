package projects.pa2.nodes.messages;

import sinalgo.nodes.messages.Message;

public class DisseminationMsg extends Message{

	public int id ;
	public double triangles;
	public double connectedTriplets;
	
	public DisseminationMsg(int id, double triangles, double connectedTriplets) {
		this.id = id;
		this.triangles = triangles;
		this.connectedTriplets = connectedTriplets;
	}
	
	@Override
	public Message clone() {
		return new DisseminationMsg(this.id, this.triangles, this.connectedTriplets);
	}

}
