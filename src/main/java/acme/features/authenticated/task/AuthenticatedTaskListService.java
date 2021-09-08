package acme.features.authenticated.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;


@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task>{

	@Autowired
	AuthenticatedTaskRepository repository;
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "start","end","workload", "publica");
		
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
assert request != null;
		
		final Collection <Task>  result= this.repository.findTask();
		List <Task>  res = new ArrayList<>();
		
		for(final Task t:result) {
			if (t.isFinished()) res.add(t);
		}
		res = res.stream().sorted(Comparator.comparingInt(Task::getTime)).collect(Collectors.toList());
		
		return res;
	}

}
