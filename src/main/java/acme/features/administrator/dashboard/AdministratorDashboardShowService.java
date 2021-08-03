package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard>{

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "numberOfPublicTask", "numberOfPrivateTask", "numberOfFinishTask",
			"numberOfNotFinishTask", "minimumWorkload", "maximumWorkload", "averageWorkload", "deviationWorkload",
			"averageExecutionPeriods", "maximumExecutionPeriods" ,"minimumExecutionPeriods" , "deviationExcutionPeriods");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		Dashboard result;
		
		final Collection<Task> tasks = this.repository.findTasks();
		
		final Double numberOfPublicTask;
		final Double numberOfPrivateTask;
		final Double numberOfFinishTask;
		final Double numberOfNotFinishTask;
		final Integer minimumWorkload;
		final Integer maximumWorkload;
		final Double averageWorkload;
		final Double deviationWorkload;
		Double averageExecutionPeriods;
		Double maximumExecutionPeriods;
		Double minimumExecutionPeriods;
		Double deviationExcutionPeriods;
		
		numberOfPublicTask = this.repository.numberOfPublicTask();
		numberOfPrivateTask = this.repository.numberOfPrivateTask();
		numberOfFinishTask = this.repository.numberOfFinishTask();
		numberOfNotFinishTask = this.repository.numberOfNotFinishTask();
		minimumWorkload = this.repository.minWorkload();
		maximumWorkload = this.repository.maxWorkload();
		averageWorkload = this.repository.averegeWorkload();
		deviationWorkload = this.repository.deviationWorkload();
		averageExecutionPeriods = 0.0;
		maximumExecutionPeriods = 0.0;
		
		for (final Task t: tasks) {
			final Double duracion = ((t.getEnd().getTime() / 60000.00) - (t.getStart().getTime() / 60000.00));
			averageExecutionPeriods = averageExecutionPeriods + duracion;
		}
		averageExecutionPeriods = averageExecutionPeriods / tasks.size();
		
		for (final Task t: tasks) {
			final Double duracion = ((t.getEnd().getTime() / 60000.00) - (t.getStart().getTime() / 60000.00));
			//Calculamos el maximo en los Workloads
			if (duracion>maximumExecutionPeriods) {
				maximumExecutionPeriods=1.0*duracion;
			}
		}
		//Partimos del maximo y vamos decreciendo para encontrar el minimo
		minimumExecutionPeriods = maximumExecutionPeriods;
		for (final Task t: tasks) {
			final Double duracion = ((t.getEnd().getTime() / 60000.00) - (t.getStart().getTime() / 60000.00));
			//Calculamos el maximo en los Workloads
			if (duracion<minimumExecutionPeriods) {
				minimumExecutionPeriods=1.0*duracion;
			}
		}
		
		final List<Double> workloadList = new ArrayList<Double>();
		for (final Task t: tasks) {
			final Double duracion = ((t.getEnd().getTime() / 60000.00) - (t.getStart().getTime() / 60000.00));
			workloadList.add(duracion);
		}
		deviationExcutionPeriods = AdministratorDashboardShowService.calculateDeviation(workloadList);
			
		result = new Dashboard();
		result.setNumberOfPublicTask(numberOfPublicTask);
		result.setNumberOfPrivateTask(numberOfPrivateTask);
		result.setNumberOfFinishTask(numberOfFinishTask);
		result.setNumberOfNotFinishTask(numberOfNotFinishTask);
		result.setMinimumWorkload(minimumWorkload);
		result.setMaximumWorkload(maximumWorkload);
		result.setAverageWorkload(averageWorkload);
		result.setDeviationWorkload(deviationWorkload);
		result.setAverageExecutionPeriods(averageExecutionPeriods);
		result.setMaximumExecutionPeriods(maximumExecutionPeriods);
		result.setMinimumExecutionPeriods(minimumExecutionPeriods);
		result.setDeviationExcutionPeriods(deviationExcutionPeriods);
		
		return result;
		
	}
	
	
	private static Double calculateDeviation(final List<Double> lista) {
		Double sum = 0.0;
		for(int i = 0; i<lista.size();i++) {
			sum = sum + lista.get(i);
		}
		//Calculamos la media
		final Double average = sum / lista.size();
		Double deviation = 0.0;
		//Calculamos  la desviacion 
		for(int i = 0; i<lista.size();i++) {
			deviation = deviation + Math.pow(lista.get(i) - average, 2);
		}
		return Math.sqrt(deviation/(lista.size()-1));
		
	}


	
	

}
