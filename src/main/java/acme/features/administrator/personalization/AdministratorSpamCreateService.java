package acme.features.administrator.personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamCreateService implements AbstractCreateService<Administrator, Spam>{

	@Autowired
	protected AdministratorSpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		Principal principal;

		principal=request.getPrincipal();
		
		return principal.hasRole("acme.framework.entities.Administrator");	
		}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		request.unbind(entity, model, "censoredWords");
		
	}

	@Override
	public Spam instantiate(final Request<Spam> request) {
		assert request != null;
		Spam result;
		
		result = new Spam();

		return result;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("censoredWords")) {
            errors.state(request, !this.repository.findCensoredWords().contains(entity.getCensoredWords()), "censoredWords", "administrator.personalization.form.error.censoredWords");
        }
	}

	@Override
	public void create(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	

}
