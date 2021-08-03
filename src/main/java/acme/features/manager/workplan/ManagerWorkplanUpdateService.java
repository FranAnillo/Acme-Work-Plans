package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplans.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan>{

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;
		boolean result;
		int taskId;		
		Manager manager;
		Principal principal;
		Workplan workplan;
		
		taskId = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(taskId);
		principal = request.getPrincipal();
		manager= workplan.getManager();
		result = manager.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity,model,"title","start","end","workload","publica","task");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;		
		
		Workplan result;
		int id;
		
		id = request.getModel().getInteger("id");
		result=this.repository.findOneWorkplanById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
