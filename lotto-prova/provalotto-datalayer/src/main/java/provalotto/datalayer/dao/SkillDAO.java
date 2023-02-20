package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Skill;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Long> {

	List<Skill> findByTopicIdOrderByName(long topicId);

}