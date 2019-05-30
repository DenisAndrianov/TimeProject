package services.repositories;

import models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User readByLogin (String login);

    boolean existsByLogin(String login);

    User findByLoginAndPass(String login, String pass);

    User readById(Integer id);
}
