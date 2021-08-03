package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplans.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
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

		request.unbind(entity, model, "title", "start", "end","publica");
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;
		Workplan result;
		Manager manager;
		manager=this.repository.findOneManagerbyUserAccountById(request.getPrincipal().getActiveRoleId());
		
		result = new Workplan();
//		result.setPublica(false);
		result.setManager(manager);
		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
//		if(!errors.hasErrors("task")) {
//			errors.state(request, entity.getTask().isEmpty(), "task", "manager.workplan.form.error.task");
//		}
//		
//		if(!errors.hasErrors("start")) {
//			errors.state(request, this.checkDateStart(entity.getStart(), entity.getTask()), "start", "manager.workplan.form.error.start");
//		}
//		
//		if(!errors.hasErrors("end")) {
//			errors.state(request, this.checkDateEnd(entity.getEnd(), entity.getTask()), "end", "manager.workplan.form.error.end");
//		}
		
	}

	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
//		Double a=0.;
//		for (int i=0;i<entity.getTask().size();i++) {
//			a+=entity.getTask().get(i).getWorkload();
//		}
//		entity.setWorkload(a);
//		entity.setPublica(false);
		this.repository.save(entity);
		
	}
	
//	public boolean checkDateStart(final Date date, final List<Task> tasks) {
//		final List<Date> lista=tasks.stream().map(Task::getStart).collect(Collectors.toList());
//		for (int i=0;i<lista.size();i++) {
//			if(lista.get(i).before(date)) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	public boolean checkDateEnd(final Date date, final List<Task> tasks) {
//		final List<Date> lista=tasks.stream().map(Task::getEnd).collect(Collectors.toList());
//		for (int i=0;i<lista.size();i++) {
//			if(lista.get(i).after(date)) {
//				return false;
//			}
//		}
//		return true;
//	}
}
