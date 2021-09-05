
package acme.features.administrator.personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamShowService implements AbstractShowService<Administrator, Spam> {

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
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		Spam result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCensoredWords(id);

		return result;
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "censoredWords");
		model.setAttribute("taskId", entity.getId());		
	}

}
