package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.TopicBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface TopicManager {

	TopicBean createTopic(TopicBean topicBean) throws ServiceErrorException;

	boolean deleteTopic(Long topicBeanId);

	List<BeanKeyValue> getAllTopics() throws ServiceErrorException;

	List<BeanKeyValue> getTopicsByPerson(Long personId) throws ServiceErrorException;

}