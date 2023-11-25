package br.janioofi.system_gym.repository;

import br.janioofi.system_gym.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    UserModel findByLogin(String login);
}
