package acme.features.administrator.personalization;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.personalization.Personalization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;


@Service
public class AdministratorBannedWordsListService implements AbstractListService<Administrator, Personalization>{

	@Autowired
	AdministratorPersonalizationRepository repository;
	@Override
	public boolean authorise(final Request<Personalization> request) {
		assert request != null;

		Principal principal;

		principal=request.getPrincipal();
		return principal.hasRole("acme.framework.entities.Administrator");	
		}

	@Override
	public void unbind(final Request<Personalization> request, final Personalization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "censoredWords");
				
	}

	@Override
	public Collection<Personalization> findMany(final Request<Personalization> request) {
assert request != null;
		
		Collection <Personalization>  result;
		
		result =  this.repository.findPersonalization();
		
		return result;
	}

	
}
