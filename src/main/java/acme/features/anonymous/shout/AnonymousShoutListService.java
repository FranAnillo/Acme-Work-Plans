package acme.features.anonymous.shout;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state

	@Autowired
	protected AnonymousShoutRepository repository;

	// AbstractListService<Administrator, Shout> interface 

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment", "info");
	}

	@Override
	public List<Shout> findMany(final Request<Shout> request) {
		assert request != null;

		//Task 007
		final List<Shout> lista = new ArrayList<Shout>();

		for(final Shout shout : this.repository.findMany()) {
			if(shout.getMoment().after(Date.valueOf(LocalDate.now().minusMonths(1)))) {
				lista.add(shout);
			}
		}
		lista.sort(Comparator.comparing(Shout::getMoment));
		return lista;
	
	}
	
}
