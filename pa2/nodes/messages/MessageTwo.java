package projects.pa2.nodes.messages;

import java.util.ArrayList;

import sinalgo.nodes.messages.Message;

public class MessageTwo extends Message{

	public ArrayList<Integer> data;
	
	public MessageTwo(ArrayList<Integer> data) {
		this.data = data;
	}
	
	@Override
	public Message clone() {
		return new MessageTwo(this.data);
	}

}
