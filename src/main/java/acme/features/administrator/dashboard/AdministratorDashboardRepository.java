package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.Workload;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("select  count(j) from Task j where j.finish = 1")
	Integer numberOfFinishTask();
	
	@Query("select  count(j) from Task j where j.finish = 0")
	Integer numberOfNotFinishTask();
	
	@Query("select count(j) from Task j where j.publica = 1")
	Integer numberOfPublicTask(); 
	
	@Query("select count(j) from Task j where j.publica = 0")
	Integer numberOfPrivateTask();
	
//	@Query("select min (j.workload.time) from Task j")
//	Integer minWorkload();
//
//	@Query("select max (j.workload.time) from Task j")
//	Integer maxWorkload();
//	
//	@Query("select avg (j.workload.time) from Task j")
//	Double averegeWorkload();
//	
//	@Query("select stddev(j.workload.time) from Task j")
//	Double deviationWorkload();
	
	@Query("select j from Task j")
	Collection<Task> findTasks();
	
	@Query("select j.workload from Task j")
	Collection<Workload> findWorkloads();
}