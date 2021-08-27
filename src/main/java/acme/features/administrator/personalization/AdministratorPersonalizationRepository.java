package acme.features.administrator.personalization;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.personalization.Personalization;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorPersonalizationRepository extends AbstractRepository {

    @Query("select p.censoredWords from Personalization p")
    List<String> findCensoredWords();
    
    @Query("select p from Personalization p")
    List<Personalization> findPersonalization();

    @Query("select p from Personalization p where p.id= ?1")
    Personalization findOneCensoredWords(int taskId);

}
