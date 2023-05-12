package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Person;
import provalotto.bean.utility.SearchPeopleObject;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {

	boolean existsBySerial(Integer serial);

	List<Person> findAllByOrderBySurname();

	@Override
	Optional<Person> findById(Integer id);

	@Query("SELECT DISTINCT p " + "FROM Person p "
			+ "left join PersonAreaConnection pa on p.id=pa.id.person.id left join Area a on pa.id.area.id=a.id "
			+ "left join PersonTopicConnection pt on p.id=pt.id.person.id left join Topic t on pt.id.topic.id=t.id "
			+ "left join PersonSkillConnection ps on p.id=ps.id.person.id left join Skill s on ps.id.skill.id=s.id "
			+ "WHERE (:#{#model.areaId} IS NULL OR a.id=:#{#model.areaId}) "
			+ "AND (:#{#model.skillId} IS NULL OR s.id=:#{#model.skillId}) "
			+ "AND (:#{#model.topicId} IS NULL OR t.id=:#{#model.topicId}) " + "ORDER BY p.surname")
	List<Person> searchPeople(@Param("model") SearchPeopleObject searchPeopleObject);
}