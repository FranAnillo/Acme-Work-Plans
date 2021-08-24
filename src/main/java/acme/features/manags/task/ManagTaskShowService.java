
package acme.features.manags.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagTaskShowService implements AbstractShowService<Manag, Task> {

	@Autowired
	protected ManagTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		boolean result;
		int taskId;
		Task task;
		Manag manag;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		principal=request.getPrincipal();
		manag=task.getManag();
		
		result =task.getPublica() || !task.getPublica() && manag.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start", "end", "link", "workload","publica");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

}
