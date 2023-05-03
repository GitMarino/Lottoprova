package provalotto.datalayer.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndPassword(String username, String password);

}