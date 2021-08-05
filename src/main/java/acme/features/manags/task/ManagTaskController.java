
package acme.features.manags.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manag/task/")
public class ManagTaskController extends AbstractController<Manag, Task> {

	// Internal state 

	@Autowired
	protected ManagTaskCreateService	createService;

	@Autowired
	private ManagTaskListService		listService;

	@Autowired
	private ManagTaskShowService		showService;

	@Autowired
	private ManagTaskUpdateService		updateService;

	@Autowired
	private ManagTaskDeleteService		deleteService;

	// Constructor


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
