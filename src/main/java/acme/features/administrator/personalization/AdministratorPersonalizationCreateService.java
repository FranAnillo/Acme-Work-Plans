package acme.features.administrator.personalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.personalization.Personalization;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorPersonalizationCreateService implements AbstractCreateService<Administrator, Personalization>{

	@Autowired
	protected AdministratorPersonalizationRepository repository;
	
	@Override
	public boolean authorise(final Request<Personalization> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Personalization> request, final Personalization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Personalization> request, final Personalization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		request.unbind(entity, model, "censoredWords");
		
	}

	@Override
	public Personalization instantiate(final Request<Personalization> request) {
		assert request != null;
		Personalization result;
		
		result = new Personalization();

		return result;
	}

	@Override
	public void validate(final Request<Personalization> request, final Personalization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("censoredWords")) {
            errors.state(request, !this.repository.findCensoredWords().contains(entity.getCensoredWords()), "censoredWords", "administrator.personalization.form.error.censoredWords");
        }
	}

	@Override
	public void create(final Request<Personalization> request, final Personalization entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	

}
