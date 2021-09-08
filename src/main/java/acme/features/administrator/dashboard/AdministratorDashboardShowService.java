package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.Workload;
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
			"averageExecutionPeriods", "maximumExecutionPeriods" ,"minimumExecutionPeriods" , "deviationExecutionPeriods");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		Dashboard result;
		
		final Collection<Task> tasks = this.repository.findTasks();
		final Collection<Workload> workloads= this.repository.findWorkloads();
		final List<Integer> workloadList = workloads.stream().map(Workload::getTime).sorted().collect(Collectors.toList());
		final Integer numberOfPublicTask;
		final Integer numberOfPrivateTask;
		Integer numberOfFinishTask = 0;
		final Integer numberOfNotFinishTask;
		Workload minWorkload;
		Workload maxWorkload;
		final Float averageWorkload;
		final Double deviationWorkload;
		Double averageExecutionPeriods;
		Double maximumExecutionPeriods;
		Double minimumExecutionPeriods = 0.0;
		Double deviationExecutionPeriods = 0.0;
		
		numberOfPublicTask = this.repository.numberOfPublicTask();
		numberOfPrivateTask = this.repository.numberOfPrivateTask();
		final Collection <Task>  ts= this.repository.findTasks();
		for(final Task t:ts) {
			if (t.isFinished()) numberOfFinishTask++;
		}
		
		numberOfNotFinishTask =  this.repository.findTasks().size()-numberOfFinishTask;
				
		averageExecutionPeriods = 0.0;
		maximumExecutionPeriods = 0.0;
		
		final Workload zero= new Workload();
		zero.setHours(0);
		zero.setMinutes(0);

		minWorkload=zero;
		maxWorkload=zero;
		
		if(!workloads.isEmpty()) minWorkload=AdministratorDashboardShowService.minutesToWorkload(workloadList.get(0));
		if(!workloads.isEmpty()) maxWorkload=AdministratorDashboardShowService.minutesToWorkload(workloadList.get(workloadList.size()-1));
		
		averageWorkload=AdministratorDashboardShowService.minutesToHourDouble(workloads.stream().collect(Collectors.averagingInt(Workload::getTime)));
		if(!tasks.isEmpty()) {
		for (final Task t: tasks) {
			final Double duracion = t.getExecutionPeriod();
			averageExecutionPeriods = averageExecutionPeriods + duracion;
		}
		averageExecutionPeriods = averageExecutionPeriods / tasks.size();
		
		for (final Task t: tasks) {
			final Double duracion = t.getExecutionPeriod();
			//Calculamos el maximo en los periodos de ejecución
			if (duracion>maximumExecutionPeriods) {
				maximumExecutionPeriods=1.0*duracion;
			}
		}
		//Partimos del maximo y vamos decreciendo para encontrar el minimo
		minimumExecutionPeriods = maximumExecutionPeriods;
		for (final Task t: tasks) {
			final Double duracion =t.getExecutionPeriod();
			//Calculamos el maximo en los periodos de ejecución
			if (duracion<minimumExecutionPeriods) {
				minimumExecutionPeriods=1.0*duracion;
			}
		}
		
		final List<Double> executionPeriodsList = new ArrayList<Double>();
		for (final Task t: tasks) {
			final Double duracion = t.getExecutionPeriod();
			executionPeriodsList.add(duracion);
		}
		
		deviationExecutionPeriods = AdministratorDashboardShowService.calculateDeviationDouble(executionPeriodsList);
		}
		deviationWorkload=AdministratorDashboardShowService.calculateDeviationInteger(workloadList);
		
		result = new Dashboard();
		
		result.setNumberOfPublicTask(numberOfPublicTask);
		result.setNumberOfPrivateTask(numberOfPrivateTask);
		
		result.setNumberOfFinishTask(numberOfFinishTask);
		result.setNumberOfNotFinishTask(numberOfNotFinishTask);
		
		result.setMinimumWorkload(minWorkload);
		result.setMaximumWorkload(maxWorkload);
		result.setAverageWorkload(averageWorkload);
		result.setDeviationWorkload(deviationWorkload);
		
		result.setAverageExecutionPeriods(averageExecutionPeriods);
		result.setMaximumExecutionPeriods(maximumExecutionPeriods);
		result.setMinimumExecutionPeriods(minimumExecutionPeriods);
		result.setDeviationExecutionPeriods(deviationExecutionPeriods);
		
		return result;
		
	}
	
	
	private static Double calculateDeviationDouble(final List<Double> lista) {
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

	private static Double calculateDeviationInteger(final List<Integer> lista) {
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
		Double t=Math.sqrt(deviation/(lista.size()-1));
		Double h=0.0;
		while (t>=60) {
			h++;
			t=t-60;
		}
		h= h+(t/100);
		return h;
	}
	
	private static Workload minutesToWorkload(Integer min) {
		final Workload res= new Workload();
		int h=0;
		while (min>=60) {
			h++;
			min=min-60;
		}
		res.setHours(h);
		res.setMinutes(min);
		return res;
	}

	private static Float minutesToHourDouble(Double min) {
		float h=0;
		while (min>=60) {
			h++;
			min=min-60;
		}
		h=(float) (h+(min/100));
		return h;
	}
}
