package acme.features.manags.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.features.administrator.personalization.AdministratorSpamRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.filter.Filter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagTaskCreateService implements AbstractCreateService<Manag, Task> {

	// Internal state

	@Autowired
	protected ManagTaskRepository						repository;

		@Autowired
		protected AdministratorThresholdRepository			thresholdRepository;

	@Autowired
	protected AdministratorSpamRepository	personalizationRepository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description","start","end", "link","workload", "publica");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manag manag;
		manag = this.repository.findOneManagbyUserAccountById(request.getPrincipal().getActiveRoleId());

		result = new Task();
		result.setPublica(false);
		result.setManag(manag);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("end") && !errors.hasErrors("start")) {
			errors.state(request, entity.getEnd().after(entity.getStart()), "end", "manag.task.error.end");
		}
		
		if (!errors.hasErrors("start")) {
			Date moment;
	        moment = new Date(System.currentTimeMillis() - 1);
			errors.state(request, entity.getStart().after(moment), "start", "manag.task.error.start");
		}
		
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getMinutes()<=59, "workload", "default.error.workload");
		}
		
		if (!errors.hasErrors("start")&&!errors.hasErrors("end")&&!errors.hasErrors("workload")) {
			errors.state(request, Filter.calculate(entity.getStart(), entity.getEnd(), entity.getWorkload()), "workload", "acme.validation.decimal-max",Filter.calculate(entity.getStart(),  entity.getEnd()));
		}
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getTime()>0, "workload", "default.error.workload.zero");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, Filter.filterString(entity.getDescription(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "description", "manag.task.form.error.description");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, Filter.filterString(entity.getTitle(),this.personalizationRepository.findCensoredWords(), this.thresholdRepository.findThresholdById()), "title", "manag.task.form.error.title");
		}
	

	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		entity.setPublica(false);
		this.repository.save(entity);
	}
	
}

	

