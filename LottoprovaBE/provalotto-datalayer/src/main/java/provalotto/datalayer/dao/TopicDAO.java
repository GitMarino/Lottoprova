package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Topic;
import provalotto.bean.utility.ATSKeyValue;

@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {

	boolean existsByName(String name);

	List<Topic> findAllByOrderByName();

	@Override
	Optional<Topic> findById(Long id);

	@Query("SELECT t " + "FROM topic t join PersonTopicConnection pt on t.id=pt.id.topic.id "
			+ "join person p on pt.id.person=p.id " + "WHERE p.id=?1 " + "ORDER BY t.name")
	List<Topic> findTopicsByPerson(Long personId);

	@Query("SELECT DISTINCT a.id as areaId, a.name as areaName, t.id as topicId, t.name as topicName, s.id as skillId, s.name as skillName "
		 + "FROM topic t left join skill s on t.id=s.topic.id left join AreaTopicConnection at on t.id=at.id.topic.id left join area a on at.id.area.id=a.id")
	List<ATSKeyValue> getMap();

}