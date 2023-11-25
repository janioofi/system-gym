package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByLogin(String login);
}
