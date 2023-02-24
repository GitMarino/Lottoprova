package provalotto.datalayer.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.entity.Skill;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Long> {

	List<Skill> findAllByOrderByName();

	@Override
	Optional<Skill> findById(Long id);

	List<Skill> findBySkillTopicIdOrderByName(long skillTopicId);

}