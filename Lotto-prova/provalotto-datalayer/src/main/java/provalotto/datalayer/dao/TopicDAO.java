package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Topic;

@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {

	List<Topic> findAllByOrderByName();

}