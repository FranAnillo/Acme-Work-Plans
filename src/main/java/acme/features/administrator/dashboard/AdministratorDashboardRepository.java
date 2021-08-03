package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select 1.0 * count(j) from Task j where j.finish = 1")
	Double numberOfFinishTask();
	
	@Query("select 1.0 * count(j) from Task j where j.finish = 0")
	Double numberOfNotFinishTask();
	
	@Query("select 1.0 * count(j) from Task j where j.publica = 1")
	Double numberOfPublicTask(); 
	
	@Query("select 1.0 * count(j) from Task j where j.publica = 0")
	Double numberOfPrivateTask();
	
	@Query("select min (j.workload) from Task j")
	Integer minWorkload();

	@Query("select max (j.workload) from Task j")
	Integer maxWorkload();
	
	@Query("select avg (j.workload) from Task j")
	Double averegeWorkload();
	
	@Query("select stddev(j.workload) from Task j")
	Double deviationWorkload();
	
	@Query("select j from Task j")
	Collection<Task> findTasks();
}