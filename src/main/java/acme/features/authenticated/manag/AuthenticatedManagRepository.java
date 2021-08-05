package acme.features.authenticated.manag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manag;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedManagRepository extends AbstractRepository {

    @Query("select p from Manag p where p.userAccount.id = ?1")
    Manag findOneManagByUserAccountId(int id);

    @Query("select ua from UserAccount ua where ua.id = ?1")
    UserAccount findOneUserAccountById(int id);

}
