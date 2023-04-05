package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.connection.PersonTopicConnection;
import provalotto.bean.key.PersonTopicConnectionKey;

@Repository
public interface PersonTopicConnectionDAO extends JpaRepository<PersonTopicConnection, PersonTopicConnectionKey> {

	boolean existsByIdPersonIdAndIdTopicId(Long personId, Long topicId);

	List<PersonTopicConnection> findByIdPersonId(Long personId);

}