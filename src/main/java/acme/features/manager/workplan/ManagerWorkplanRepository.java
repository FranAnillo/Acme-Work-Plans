package acme.features.manager.workplan;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.workplans.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository {
	
	@Query("select w from Workplan w where w.manager.userAccount.id = ?1")
	Workplan findWorkplansByUserAccountById(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerbyUserAccountById(int id);
	
	@Query("select w from Workplan w where w.id= ?1")
	Workplan findOneWorkplanById(int workplanId);
	
	@Query("select w from Workplan w")
	Collection<Workplan> findMany();
}
