
package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.features.administrator.personalization.AdministratorPersonalizationRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state 

	@Autowired
	protected AnonymousShoutRepository					repository;

	@Autowired
	protected AdministratorPersonalizationRepository	personalizationRepository;

	@Autowired
	protected AdministratorThresholdRepository			thresholdRepository;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result = new Shout();
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("text")) {
			errors.state(request, this.filterString(entity.getText()), "text", "anonymous.shout.form.error.text");
		}
		if (!errors.hasErrors("author")) {
			errors.state(request, this.filterString(entity.getAuthor()), "author", "anonymous.shout.form.error.author");
		}
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

	public boolean filterString(final String s) {
		final String j = s.replace(" ", ";");
		final int number = j.split(";").length;
		final String[] palabras = j.split(";");
		float numberBannedWords = 0;
		final List<String> censoredWords = this.personalizationRepository.findCensoredWords();
		for (int i = 0; censoredWords.size() > i; i++) {
			for (int k = 0; palabras.length > k; k++) {
				if (palabras[k].equalsIgnoreCase(censoredWords.get(i))) {
					numberBannedWords = numberBannedWords + 1;
				}
			}
		}
		if ((numberBannedWords * 100 / number) >= this.thresholdRepository.findThresholdById())
			return false;

		return true;
	}

}
