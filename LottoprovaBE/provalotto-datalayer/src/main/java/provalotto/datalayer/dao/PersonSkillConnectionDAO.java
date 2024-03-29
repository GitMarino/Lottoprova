package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.connection.PersonSkillConnection;
import provalotto.bean.key.PersonSkillConnectionKey;

@Repository
public interface PersonSkillConnectionDAO extends JpaRepository<PersonSkillConnection, PersonSkillConnectionKey> {

	/*
	 * @Query("SELECT DISTINCT ps " + "FROM PersonSkillConnection ps " +
	 * "left join Skill s on ps.id.skill.id=s.id " +
	 * "left join Topic t on s.skillTopic=t.id " + "WHERE ps.id.person.id=?1 AND " +
	 * "( (:#{#model.skillId} IS NULL AND (:#{#model.topicId} IS NULL OR t.id=:#{#model.topicId})) "
	 * + "OR s.id=:#{#model.skillId} )" ) List<PersonSkillConnection>
	 * searchPersonSkillConnection(Long personId,
	 *
	 * @Param("model") SearchPeopleObject searchPeopleObject);
	 */

	boolean existsByIdPersonIdAndIdSkillId(Integer personId, Integer skillId);

	List<PersonSkillConnection> findByIdPersonId(Integer personId);

}