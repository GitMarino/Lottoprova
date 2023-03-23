package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface AreaManager {

	void createArea(final String name, final Long personBeanId, Long topicBeanId) throws ServiceErrorException;

	void createAreaTopicConnection(Long areaId, Long topicId) throws ServiceErrorException;

	boolean deleteArea(Long areaBeanId);

	List<KeyValueBean> getAllAreas() throws ServiceErrorException;

	List<KeyValueBean> getAreasByPerson(Long personId) throws ServiceErrorException;

	List<List<KeyValueBean>> getAreaTopicSkillMap();

}