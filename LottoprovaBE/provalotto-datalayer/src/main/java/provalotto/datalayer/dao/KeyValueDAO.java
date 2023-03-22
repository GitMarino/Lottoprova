package provalotto.datalayer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.utility.ATSKeyValue;

@Repository
public interface KeyValueDAO extends JpaRepository<KeyValueBean, Long> {

	@Query("SELECT DISTINCT a.id as areaid, a.name as areaname, t.id as topicid, t.name as , s.id, s.name "
		 + "FROM area a left join AreaTopicConnection at on a.id=at.id.area right join topic t on at.id.topic=t.id right join skill s on s.topic.id=t.id")
	List<ATSKeyValue> getMap();
}