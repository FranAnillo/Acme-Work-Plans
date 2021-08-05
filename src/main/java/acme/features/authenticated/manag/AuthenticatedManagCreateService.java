
package acme.features.authenticated.manag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedManagCreateService implements AbstractCreateService<Authenticated, Manag> {

	@Autowired
	protected AuthenticatedManagRepository repository;


	@Override
	public boolean authorise(final Request<Manag> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Manag> request, final Manag entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<Manag> request, final Manag entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Manag> request, final Manag entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

	}

	@Override
	public Manag instantiate(final Request<Manag> request) {
		assert request != null;

		Manag result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Manag();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<Manag> request, final Manag entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
	
	@Override
    public void onSuccess(final Request<Manag> request, final Response<Manag> response) {
        assert request != null;
        assert response != null;

        if (request.isMethod(HttpMethod.POST)) {
            PrincipalHelper.handleUpdate();
        }
    }

}
