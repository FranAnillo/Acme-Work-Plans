package acme.features.manags.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.features.administrator.personalization.AdministratorPersonalizationRepository;
import acme.features.administrator.threshold.AdministratorThresholdRepository;
import acme.filter.Filter;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagTaskCreateService implements AbstractCreateService<Manag, Task> {

	// Internal state

	@Autowired
	protected ManagTaskRepository						repository;

		@Autowired
		protected AdministratorThresholdRepository			thresholdRepository;

	@Autowired
	protected AdministratorPersonalizationRepository	personalizationRepository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description","start","end", "link","workload", "publica");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manag manag;
		manag = this.repository.findOneManagbyUserAccountById(request.getPrincipal().getActiveRoleId());

		result = new Task();
		result.setTitle("Task 1");
		result.setDescription("Description of the taks 2");
		result.setLink("http://example.org");
		result.setPublica(false);
		result.setManag(manag);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("end")) {
			errors.state(request, entity.getEnd().after(entity.getStart()), "end", "manag.task.error.end");
		}
		
		if((!errors.hasErrors("workload"))) {
			errors.state(request, entity.getWorkload().getMinutes()<=59, "workload", "default.error.workload");
		}
		
		if (!errors.hasErrors("start")&&!errors.hasErrors("end")&&!errors.hasErrors("workload")) {
			errors.state(request, Filter.calculate(entity.getStart(), entity.getEnd(), entity.getWorkload()), "workload", "acme.validation.decimal-max",Filter.calculate(entity.getStart(),  entity.getEnd()));
		}

		if (!errors.hasErrors("description")) {
			errors.state(request, this.filterString(entity.getDescription()), "description", "manag.task.form.error.description");
		}

		if (!errors.hasErrors("title")) {
			errors.state(request, this.filterString(entity.getTitle()), "title", "manag.task.form.error.title");
		}
	

	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		entity.setPublica(false);
		this.repository.save(entity);
	}
	public boolean filterString(final String s) {
        final String j = s.replace("\s", ";");
        final String[] palabras = j.split(";");
        float numberBannedWords = 0;
        float numberOfWords = 0;
        final List<String> censoredWords = this.personalizationRepository.findCensoredWords();

        for (int x = 0; palabras.length > x; x++) {
            if (!palabras[x].isEmpty()) {
                numberOfWords++;
            }
        }

        for (int i = 0; censoredWords.size() > i; i++) {
            for (int k = 0; palabras.length > k; k++) {

                final int numberOfCensoredString = censoredWords.get(i).replace(" ", ";").split(";").length;
                String bannedString = palabras[k];

                if (bannedString == null || bannedString.isEmpty())
                    break;
                for (int w = 1; numberOfCensoredString > (w+i); w++) {

                	
                    while (k + w < palabras.length && palabras[k + w].isEmpty()) {
                        w++;
                    }
                    if (k + w >= palabras.length)
                        break;
                    bannedString = bannedString + ";" + palabras[k + w];
                }
                if (palabras[k].equalsIgnoreCase(bannedString)) {
                    numberBannedWords = numberBannedWords + numberOfCensoredString;
                }

            }
        }

        if ((numberBannedWords * 100 / numberOfWords) >= this.thresholdRepository.findThresholdById()) {

            return false;
        }
        return true;

    }
    }
		

	

