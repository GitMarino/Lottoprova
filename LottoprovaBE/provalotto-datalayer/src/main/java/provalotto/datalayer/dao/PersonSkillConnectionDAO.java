package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.connection.PersonSkillConnection;
import provalotto.bean.key.PersonSkillConnectionKey;

@Repository
public interface PersonSkillConnectionDAO extends JpaRepository<PersonSkillConnection, PersonSkillConnectionKey> {

	List<PersonSkillConnection> findByIdPersonIdAndIdSkillId(Long personId, Long skillId);
}