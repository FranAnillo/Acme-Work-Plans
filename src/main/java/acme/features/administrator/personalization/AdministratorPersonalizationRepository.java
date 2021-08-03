package acme.features.administrator.personalization;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorPersonalizationRepository extends AbstractRepository {

    @Query("select p.censoredWords from Personalization p")
    List<String> findCensoredWords();

}
