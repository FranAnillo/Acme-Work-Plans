package acme.features.manags.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manag;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagTaskRepository extends AbstractRepository {

@Query("select p from Provider p where p.userAccount.id = ?1")
Manag findOneProviderByUserAccountId(int id);

@Query("select m from Manag m where m.id = ?1")
Manag findOneManagbyUserAccountById(int id);

@Query("select p from Task p where p.id= ?1")
Task findOneTaskById(int taskId);

@Query("select t from Task t")
Collection<Task> findMany();
}