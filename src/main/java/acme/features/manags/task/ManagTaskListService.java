package acme.features.manags.task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ManagTaskListService implements AbstractListService<Manag, Task> {
	
	@Autowired
	private ManagTaskRepository repository;

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
		
		request.unbind(entity, model, "title", "start","description", "end", "workload");
	}

	@Override
	public List<Task> findMany(final Request<Task> request) {
		assert request != null;		
		
		final List<Task> result=new ArrayList<>();
		final int workerId=request.getPrincipal().getActiveRoleId();

		for (final Task task:this.repository.findMany()) {
			if (task.getManag().equals(this.repository.findOneManagbyUserAccountById(workerId))) {
				result.add(task);
			}
		}
		
		return result.stream().sorted(Comparator.comparingInt(Task::getTime)).collect(Collectors.toList());
	}


}

