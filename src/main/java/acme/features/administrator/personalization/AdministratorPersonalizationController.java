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

	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);

	}
}
