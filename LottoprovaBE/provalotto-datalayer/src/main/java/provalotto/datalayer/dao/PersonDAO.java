package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

	List<Person> findAllByOrderBySurname();

	@Override
	Optional<Person> findById(Long id);

	// @Query("SELECT p FROM PersonAreaConnection r join Person p on
	// p.id=r.id.person.id join Area a on r.id.area.id=a.id")
	@Query("SELECT p "
			+ "FROM area a right join PersonAreaConnection pa on a.id=pa.id.area.id right join person p on pa.id.person.id=p.id "
			+ "left join PersonTopicConnection pt on p.id=pt.id.person.id left join topic t on pt.id.topic.id=t.id "
			+ "WHERE (?1 IS NULL OR a.id=?1) " + "AND (?2 IS NULL OR t.id=?2)")
	List<Person> search(Long areaId, Long topicId);

}