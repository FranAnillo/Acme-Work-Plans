package acme.features.administrator.personalization;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {

    @Query("select p.censoredWords from Spam p")
    List<String> findCensoredWords();
    
    @Query("select p from Spam p")
    List<Spam> findSpam();

    @Query("select p from Spam p where p.id= ?1")
    Spam findOneCensoredWords(int taskId);

}
