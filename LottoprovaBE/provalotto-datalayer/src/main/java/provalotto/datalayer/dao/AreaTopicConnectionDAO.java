package provalotto.datalayer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import provalotto.bean.connection.AreaTopicConnection;
import provalotto.bean.key.AreaTopicConnectionKey;

@Repository
public interface AreaTopicConnectionDAO extends JpaRepository<AreaTopicConnection, AreaTopicConnectionKey> {

}