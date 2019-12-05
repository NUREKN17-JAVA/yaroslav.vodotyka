package agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import nure.cs.vodotyka.usermanagment.User;
import db.DaoFactory;
import db.DatabaseException;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RequestServer extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if(message != null){
			if(message.getPerformative() == ACLMessage.REQUEST){
				myAgent.send(CreateReply(message));
			}
			else{
				Collection users = ParseMessage(message);
				((SearchAgent)myAgent).ShowUsers(users);
			}
		}
		else{
			block();
		}
	}

	private Collection ParseMessage(ACLMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

	private ACLMessage CreateReply(ACLMessage message) {
		ACLMessage reply = message.createReply();
		
		String content = message.getContent();
		StringTokenizer tokenizer = new StringTokenizer(content, ",");
		if(tokenizer.countTokens() == 2){
			String firstName = tokenizer.nextToken();
			String lastName = tokenizer.nextToken();
			Collection users = null;
			try{
				users = DaoFactory.GetInstance().GetUserDao().FindUser(firstName, lastName);
			}
			catch(DatabaseException e){
				users = new ArrayList(0);
			}
			
			StringBuffer buffer = new StringBuffer();
			for(Iterator it = users.iterator(); it.hasNext();){
				User user = (User)it.next();
				buffer.append(user.getId()).append(",");
				buffer.append(user.getFirstName()).append(",");
				buffer.append(user.getLastName()).append(";");
			}
			reply.setContent(buffer.toString());
			
		}
		return reply;
	}

}
