package projects.pa1.nodes.messages;

import sinalgo.nodes.messages.Message;

public class DisseminationMessage extends Message{

	public int data;
	
	public DisseminationMessage(int data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return this;
	}

}
