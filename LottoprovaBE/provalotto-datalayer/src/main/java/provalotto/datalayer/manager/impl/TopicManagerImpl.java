package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.manager.TopicManager;

@Component
public class TopicManagerImpl implements TopicManager {

	public static final Logger log = LoggerFactory.getLogger(TopicManagerImpl.class);

	@Autowired
	private TopicDAO topicDAO;

	@Transactional
	@Override
	public TopicBean createTopic(final TopicBean topicBean) throws ServiceErrorException {
		try {
			if (!topicDAO.existsByName(topicBean.getName())) {
				Topic topic = new Topic();
				topic.setName(topicBean.getName());
				topic.setMaker("Christian Marino");
				topic.setDateTime(LocalDateTime.now());
				topicDAO.save(topic);

				return topicBean;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}

		throw new ServiceErrorException("Dati inconsistenti");
	}

	@Override
	public boolean deleteTopic(final Long topicBeanId) {
		try {
			Optional<Topic> topicOptional = topicDAO.findById(topicBeanId);
			if (topicOptional.isPresent()) {
				topicDAO.delete(topicOptional.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<BeanKeyValue> getAllTopics() throws ServiceErrorException {
		List<BeanKeyValue> allBeans = new ArrayList<>();
		BeanKeyValue beanKeyValue;
		try {
			for (Topic topic : topicDAO.findAllByOrderByName()) {
				beanKeyValue = new BeanKeyValue();
				beanKeyValue.setId(topic.getId());
				beanKeyValue.setValue(topic.getName());
				allBeans.add(beanKeyValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
		return allBeans;
	}

	@Override
	public List<BeanKeyValue> getTopicsByPerson(final Long personId) throws ServiceErrorException {
		List<BeanKeyValue> topicBeans = new ArrayList<>();
		BeanKeyValue beanKeyValue;
		try {
			for (Topic topic : topicDAO.findTopicsByPerson(personId)) {
				beanKeyValue = new BeanKeyValue();
				beanKeyValue.setId(topic.getId());
				beanKeyValue.setValue(topic.getName());
				topicBeans.add(beanKeyValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
		return topicBeans;
	}

}