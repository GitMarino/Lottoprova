package provalotto.datalayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.key.PersonAreaConnectionKey;

@Repository
public interface PersonAreaConnectionDAO extends JpaRepository<PersonAreaConnection, PersonAreaConnectionKey> {

	boolean existsByIdPersonIdAndIdAreaId(Long personId, Long areaId);
}