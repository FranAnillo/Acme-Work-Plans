package acme.features.authenticated.manag;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manag;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/manag/")
public class AuthenticatedManagController extends AbstractController<Authenticated, Manag> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected AuthenticatedManagCreateService    createService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addBasicCommand(BasicCommand.CREATE, this.createService);
    }

}