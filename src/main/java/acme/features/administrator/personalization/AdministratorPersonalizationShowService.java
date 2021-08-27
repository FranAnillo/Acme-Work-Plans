
package acme.features.administrator.personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.personalization.Personalization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorPersonalizationShowService implements AbstractShowService<Administrator, Personalization> {

	@Autowired
	protected AdministratorPersonalizationRepository repository;


	@Override
	public boolean authorise(final Request<Personalization> request) {
		assert request != null;
		
		Principal principal;

		principal=request.getPrincipal();
		
		return principal.hasRole("acme.framework.entities.Administrator");	
	}


	@Override
	public Personalization findOne(final Request<Personalization> request) {
		assert request != null;

		Personalization result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCensoredWords(id);

		return result;
	}

	@Override
	public void unbind(final Request<Personalization> request, final Personalization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "censoredWords");
		model.setAttribute("taskId", entity.getId());		
	}

}
