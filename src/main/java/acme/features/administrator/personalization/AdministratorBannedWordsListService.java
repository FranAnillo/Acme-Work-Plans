package acme.features.administrator.personalization;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;


@Service
public class AdministratorBannedWordsListService implements AbstractListService<Administrator, Spam>{

	@Autowired
	AdministratorSpamRepository repository;
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		Principal principal;

		principal=request.getPrincipal();
		return principal.hasRole("acme.framework.entities.Administrator");	
		}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "censoredWords");
				
	}

	@Override
	public Collection<Spam> findMany(final Request<Spam> request) {
assert request != null;
		
		Collection <Spam>  result;
		
		result =  this.repository.findSpam();
		
		return result;
	}

	
}
