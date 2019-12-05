package agent;

import java.util.Collection;

import db.DaoFactory;
import db.DatabaseException;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started");
		
		DFAgentDescription description = new DFAgentDescription();
		description.setName(getAID());
		ServiceDescription serviceDescr = new ServiceDescription();
		serviceDescr.setName("JADE-searching");
		serviceDescr.setType("searching");
		description.addServices(serviceDescr);
		try{
			DFService.register(this, description);
		}
		catch(FIPAException e){
			e.printStackTrace();
		}
		addBehaviour(new RequestServer());
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		try{
			DFService.deregister(this);
		} catch(FIPAException e){
			e.printStackTrace();
		}
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
