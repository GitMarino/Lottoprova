package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface AreaManager {

	void createArea(final String name, final Integer personBeanId, Integer topicBeanId) throws ServiceErrorException;

	void createAreaTopicConnection(Integer areaId, Integer topicId) throws ServiceErrorException;

	List<KeyValueBean> getAllAreas();

	List<KeyValueBean> getAreasByPerson(Integer personId) throws ServiceErrorException;

	List<List<KeyValueBean>> getAreaTopicSkillMap();

}