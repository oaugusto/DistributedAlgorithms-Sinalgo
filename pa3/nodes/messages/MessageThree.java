package projects.pa3.nodes.messages;

import sinalgo.nodes.messages.Message;

public class MessageThree extends Message{

	public int data;
	
	public MessageThree(int data) {
		this.data = data;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return new MessageThree(this.data);
	}

}
