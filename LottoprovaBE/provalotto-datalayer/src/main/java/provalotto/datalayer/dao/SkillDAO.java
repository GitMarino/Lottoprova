package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Skill;
import provalotto.bean.utility.SkillMark;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Long> {

	boolean existsByName(String name);

	List<Skill> findAllByOrderByName();

	Optional<Skill> findById(Integer id);

	List<Skill> findByTopicIdOrderByName(Integer topicId);

	@Query("SELECT s.name as skillName, ps.mark as mark "
			+ "FROM Skill s join PersonSkillConnection ps on s.id=ps.id.skill.id "
			+ "WHERE ps.id.person.id=?1 AND s.topic.id=?2 " + "ORDER BY s.name")
	List<SkillMark> findSkillsByPersonAndTopic(Integer personId, Integer topicId);

}