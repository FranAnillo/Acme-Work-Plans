package acme.features.manag.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.workplans.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagWorkplanCreateService implements AbstractCreateService<Manag, Workplan> {

	@Autowired
	protected ManagWorkplanRepository repository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end","publica");
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;
		Workplan result;
		Manag manag;
		manag=this.repository.findOneManagbyUserAccountById(request.getPrincipal().getActiveRoleId());
		
		result = new Workplan();
		result.setManag(manag);
		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}
