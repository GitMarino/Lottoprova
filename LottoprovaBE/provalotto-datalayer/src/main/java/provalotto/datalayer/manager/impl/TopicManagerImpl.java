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

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.bean.TopicSkillsBean;
import provalotto.bean.connection.PersonTopicConnection;
import provalotto.bean.entity.Topic;
import provalotto.bean.utility.SkillMark;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.TopicManager;

@Component
public class TopicManagerImpl implements TopicManager {

	public static final Logger log = LoggerFactory.getLogger(TopicManagerImpl.class);

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@Autowired
	private SkillDAO skillDAO;

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
			throw new ServiceErrorException("Dati inconsistenti");
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
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
	public List<KeyValueBean> getAllTopics() throws ServiceErrorException {
		List<KeyValueBean> allBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Topic topic : topicDAO.findAllByOrderByName()) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(topic.getId());
				beanKeyValue.setValue(topic.getName());
				allBeans.add(beanKeyValue);
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<KeyValueBean> getTopicsByPerson(final Long personId) throws ServiceErrorException {
		List<KeyValueBean> topicBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Topic topic : topicDAO.findTopicsByPerson(personId)) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(topic.getId());
				beanKeyValue.setValue(topic.getName());
				topicBeans.add(beanKeyValue);
			}
			return topicBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Transactional
	@Override
	public List<TopicSkillsBean> getTopicsSkillsByPerson(final Long personId) {
		List<TopicSkillsBean> topicsSkills = new ArrayList<>();
		Topic topic;
		TopicSkillsBean topicSkills;
		List<SkillMarkBean> skillsMarks;
		SkillMarkBean skillMark;
		Integer markDB;
		for (PersonTopicConnection personTopicConnection : personTopicConnectionDAO.findByIdPersonId(personId)) {
			topic = personTopicConnection.getId().getTopic();

			topicSkills = new TopicSkillsBean();
			topicSkills.setTopicName(topic.getName());

			Integer marksSum = 0;
			skillsMarks = new ArrayList<>();
			List<SkillMark> skillsMarksDB = skillDAO.findSkillsByPersonAndTopic(personId, topic.getId());
			for (SkillMark skillMarkDB : skillsMarksDB) {
				skillMark = new SkillMarkBean();
				skillMark.setSkillName(skillMarkDB.getSkillName());
				markDB = skillMarkDB.getMark();
				skillMark.setMark(markDB);
				marksSum += markDB;
				skillsMarks.add(skillMark);
			}

			topicSkills.setAverage(marksSum / skillsMarksDB.size());
			topicSkills.setSkillsMarks(skillsMarks);

			topicsSkills.add(topicSkills);
		}
		return topicsSkills;
	}

}