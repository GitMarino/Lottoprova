package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface AreaManager {

	void createArea(final String name, final Long personBeanId) throws ServiceErrorException;

	void createAreaTopicConnection(Long areaId, Long topicId) throws ServiceErrorException;

	boolean deleteArea(Long areaBeanId);

	List<BeanKeyValue> getAllAreas() throws ServiceErrorException;

	List<BeanKeyValue> getAreasByPerson(Long personId) throws ServiceErrorException;

}