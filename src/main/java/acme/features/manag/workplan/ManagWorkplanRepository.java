package acme.features.manag.workplan;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manag;
import acme.entities.workplans.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagWorkplanRepository extends AbstractRepository {
	
	@Query("select w from Workplan w where w.manag.userAccount.id = ?1")
	Workplan findWorkplansByUserAccountById(int id);
	
	@Query("select m from Manag m where m.id = ?1")
	Manag findOneManagbyUserAccountById(int id);
	
	@Query("select w from Workplan w where w.id= ?1")
	Workplan findOneWorkplanById(int workplanId);
	
	@Query("select w from Workplan w")
	Collection<Workplan> findMany();
}
