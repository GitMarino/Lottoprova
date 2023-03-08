package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import provalotto.bean.bean.SearchPeopleObject;
import provalotto.bean.entity.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

	List<Person> findAllByOrderBySurname();

	@Override
	Optional<Person> findById(Long id);

	@Query("SELECT DISTINCT p "
		 + "FROM person p "
			+ "left join PersonAreaConnection pa on p.id=pa.id.person.id left join area a on pa.id.area.id=a.id "
			+ "left join PersonTopicConnection pt on p.id=pt.id.person.id left join topic t on pt.id.topic.id=t.id "
			+ "left join PersonSkillConnection ps on p.id=ps.id.person.id left join skill s on ps.id.skill.id=s.id "
		 + "WHERE (:#{#model.areaId} IS NULL OR a.id=:#{#model.areaId}) "
			+ "AND (:#{#model.skillId} IS NULL OR s.id=:#{#model.skillId}) "
			+ "AND (:#{#model.topicId} IS NULL OR t.id=:#{#model.topicId}) "
		 + "ORDER BY p.surname")
	List<Person> searchPeople(@Param("model") SearchPeopleObject searchPeopleObject);

}