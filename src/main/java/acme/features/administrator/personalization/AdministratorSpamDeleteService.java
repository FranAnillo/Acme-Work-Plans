
package acme.features.administrator.personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSpamDeleteService implements AbstractDeleteService<Administrator, Spam> {

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

		request.unbind(entity, model, "title", "description", "start", "end");
		request.unbind(entity, model, "link", "publica", "finish", "workload");
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		Spam result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCensoredWords(id);
		return result;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<acme.entities.spam.Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}
	
}
