package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Topic;

@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {

	boolean existsByName(String name);

	List<Topic> findAllByOrderByName();

	@Override
	Optional<Topic> findById(Long id);

}