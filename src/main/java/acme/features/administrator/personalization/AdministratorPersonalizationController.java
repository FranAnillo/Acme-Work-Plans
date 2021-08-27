package acme.features.administrator.personalization;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.personalization.Personalization;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/personalization/")
public class AdministratorPersonalizationController extends AbstractController<Administrator, Personalization>{
	
	@Autowired
    protected AdministratorPersonalizationCreateService createService;
	
	@Autowired
	protected AdministratorBannedWordsListService listService;
	
	@Autowired
	protected AdministratorPersonalizationDeleteService deleteService;
	
	@Autowired
	protected AdministratorPersonalizationShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
