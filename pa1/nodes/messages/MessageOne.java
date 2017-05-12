package projects.pa1.nodes.messages;

import java.util.ArrayList;

import sinalgo.nodes.messages.Message;

public class MessageOne extends Message{

	public ArrayList<Integer> data;
	
	public MessageOne(ArrayList<Integer> data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return new MessageOne(new ArrayList<>(data));
	}

}
