package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.TopicManager;
import provalotto.datalayer.utility.EntityBeanMapper;

@Component
public class TopicManagerImpl implements TopicManager {

	public static final Logger log = LoggerFactory.getLogger(TopicManagerImpl.class);

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@Autowired
	private SkillDAO skillDAO;

	@Autowired
	private EntityBeanMapper mapper;

	@Override
	@Transactional(rollbackFor = { Exception.class })
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
	public List<KeyValueBean> getAllTopics() {
		List<KeyValueBean> allBeans = new ArrayList<>();
		try {
			for (Topic topic : topicDAO.findAllByOrderByName()) {
				allBeans.add(mapper.mapTopicKV(topic));
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public List<KeyValueBean> getTopicsByPerson(final Integer personId) throws ServiceErrorException {
		List<KeyValueBean> topicBeans = new ArrayList<>();
		try {
			for (Topic topic : topicDAO.findTopicsByPerson(personId)) {
				topicBeans.add(mapper.mapTopicKV(topic));
			}
			return topicBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<TopicSkillsBean> getTopicsSkillsByPerson(final Integer personId) {
		List<TopicSkillsBean> topicsSkills = new ArrayList<>();
		Topic topic;
		TopicSkillsBean topicSkills;
		List<SkillMarkBean> skillsMarks;
		SkillMarkBean skillMark;
		Integer markDB;
		try {
			for (PersonTopicConnection personTopicConnection : personTopicConnectionDAO.findByIdPersonId(personId)) {
				topic = personTopicConnection.getId().getTopic();

				topicSkills = new TopicSkillsBean();
				topicSkills.setTopicName(topic.getName());
				double marksSum = 0;
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
				topicSkills.setAverage((int) Math.round(marksSum / skillsMarksDB.size()));
				topicSkills.setSkillsMarks(skillsMarks);

				topicsSkills.add(topicSkills);
			}
			return topicsSkills;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();

		}
	}

}