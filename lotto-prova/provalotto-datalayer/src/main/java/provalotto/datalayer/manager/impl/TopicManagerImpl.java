package provalotto.datalayer.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.manager.TopicManager;

@Component
public class TopicManagerImpl implements TopicManager {

	public static final Logger log = LoggerFactory.getLogger(TopicManagerImpl.class);

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public Topic createTopic(final Topic topic) {
		try {
			if (!topicDAO.existsById(topic.getId())) {
				return topicDAO.save(topic);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean deleteTopic(final Topic topic) {
		try {
			if (topicDAO.existsById(topic.getId())) {
				topicDAO.delete(topic);
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<Topic> getAllTopics() {
		return topicDAO.findAllByOrderByName();
	}

	@Override
	public Topic saveTopic(final Topic topic) {
		try {
			return topicDAO.save(topic);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}