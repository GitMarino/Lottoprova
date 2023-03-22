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
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.SkillManager;

@Component
public class SkillManagerImpl implements SkillManager {

	public static final Logger log = LoggerFactory.getLogger(SkillManagerImpl.class);

	@Autowired
	private SkillDAO skillDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Transactional
	@Override
	public void createSkill(final String name, final String description, final Long topicBeanId)
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
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public boolean deleteSkill(final Long skillBeanId) {
		try {
			Optional<Skill> skillOptional = skillDAO.findById(skillBeanId);
			if (skillOptional.isPresent()) {
				skillDAO.delete(skillOptional.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<KeyValueBean> getAllSkills() throws ServiceErrorException {
		List<KeyValueBean> allBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Skill skill : skillDAO.findAllByOrderByName()) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(skill.getId());
				beanKeyValue.setValue(skill.getName());
				allBeans.add(beanKeyValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
		return allBeans;
	}

	@Override
	public List<KeyValueBean> getSkillsByTopic(final Long topicId) throws ServiceErrorException {
		List<KeyValueBean> skillBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Skill skill : skillDAO.findByTopicIdOrderByName(topicId)) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(skill.getId());
				beanKeyValue.setValue(skill.getName());
				skillBeans.add(beanKeyValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
		return skillBeans;
	}

}