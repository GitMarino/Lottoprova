package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.SkillManager;
import provalotto.datalayer.utility.EntityBeanMapper;

@Component
public class SkillManagerImpl implements SkillManager {

	public static final Logger log = LoggerFactory.getLogger(SkillManagerImpl.class);

	@Autowired
	private SkillDAO skillDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private EntityBeanMapper mapper;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void createSkill(final String name, final String description, final Integer topicBeanId)
			throws ServiceErrorException {
		try {
			if (!skillDAO.existsByName(name)) {
				Optional<Topic> topicOptional = topicDAO.findById(topicBeanId);
				if (topicOptional.isPresent()) {
					Skill skill = new Skill();
					skill.setName(name);
					skill.setDescription(description);
					skill.setTopic(topicOptional.get());
					skill.setMaker("Christian Marino");
					skill.setDateTime(LocalDateTime.now());
					skillDAO.save(skill);
				} else {
					throw new ServiceErrorException("Dati incosistenti");
				}
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<KeyValueBean> getAllSkills() {
		List<KeyValueBean> allBeans = new ArrayList<>();
		try {
			for (Skill skill : skillDAO.findAllByOrderByName()) {
				allBeans.add(mapper.mapSkillKV(skill));
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public List<KeyValueBean> getSkillsByTopic(final Integer topicId) throws ServiceErrorException {
		List<KeyValueBean> skillBeans = new ArrayList<>();
		try {
			for (Skill skill : skillDAO.findByTopicIdOrderByName(topicId)) {
				skillBeans.add(mapper.mapSkillKV(skill));
			}
			return skillBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

}