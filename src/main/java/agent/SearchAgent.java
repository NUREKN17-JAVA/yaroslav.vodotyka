package agent;

import java.util.Collection;

import db.DaoFactory;
import db.DatabaseException;
import jade.core.AID;
import jade.core.Agent;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started");
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		super.takeDown();
	}

	public void Search(String firstName, String lastName) throws SearchException {
		try{
			Collection users = DaoFactory.GetInstance().GetUserDao().FindUser(firstName, lastName);
			if(users.size() > 0){
				ShowUsers(users);
			}
			else{
				addBehaviour(new SearchRequestBehavior(new AID[]{}, firstName, lastName));
			}
		} catch(DatabaseException e){
			throw new SearchException(e);
		}
	}
	
	public void ShowUsers(Collection users){
		
	}
}
