package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

	List<Person> findAllByOrderBySurname();

	@Override
	Optional<Person> findById(Long id);

}