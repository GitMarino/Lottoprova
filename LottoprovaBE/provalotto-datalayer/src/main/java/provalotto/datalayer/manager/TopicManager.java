package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.bean.TopicSkillsBean;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface TopicManager {

	TopicBean createTopic(TopicBean topicBean) throws ServiceErrorException;

	List<KeyValueBean> getAllTopics() throws ServiceErrorException;

	List<KeyValueBean> getTopicsByPerson(Long personId) throws ServiceErrorException;

	List<TopicSkillsBean> getTopicsSkillsByPerson(Long personId);
}