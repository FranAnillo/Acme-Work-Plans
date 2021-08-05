package acme.features.manag.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manag;
import acme.entities.workplans.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manag/workplan/")
public class ManagWorkplanController extends AbstractController<Manag, Workplan> {

	@Autowired
	protected ManagWorkplanShowService showService;
	
	@Autowired
	protected ManagWorkplanDeleteService deleteService;
	
	@Autowired
	protected ManagWorkplanCreateService createService;
	
	@Autowired
	protected ManagWorkplanUpdateService updateService;
	
	@Autowired
	protected ManagWorkplanListService listService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
