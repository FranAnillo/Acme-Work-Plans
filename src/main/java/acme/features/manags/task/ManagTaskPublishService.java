/*
 * ManagTaskPublishService.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manags.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagTaskPublishService implements AbstractUpdateService<Manag, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagTaskRepository repository;

	// AbstractListService<Manag, Task> interface ---------------------------


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
		manag = task.getManag();
		principal = request.getPrincipal();
		result = manag.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("start")) {
			calendar = new GregorianCalendar();
			 //calendar.add(Calendar.WEEK_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getStart().after(minimumDeadline), "start", "manag.task.form.error.too-close");
		}
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

		request.unbind(entity, model, "title", "description", "start", "end", "link", "workload","publica");

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

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		entity.setPublica(true);
		this.repository.save(entity);
	}

}


