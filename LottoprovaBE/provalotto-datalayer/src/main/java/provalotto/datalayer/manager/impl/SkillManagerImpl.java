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

import provalotto.bean.bean.SkillBean;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
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
	public void createSkill(final String name, final String description, final Long skillTopicBeanId)
			throws ServiceErrorException {

		Optional<Topic> topicOptional = topicDAO.findById(skillTopicBeanId);
		if (topicOptional.isPresent()) {
			Skill skill = new Skill();
			skill.setName(name);
			skill.setDescription(description);
			skill.setSkillTopic(topicOptional.get());
			skill.setMaker("Christian Marino");
			skill.setDateTime(LocalDateTime.now());
			try {
				skillDAO.save(skill);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
		} else {
			throw new ServiceErrorException("Dati incosistenti");
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
	public List<SkillBean> getAllSkills() {
		List<SkillBean> allSkillBeans = new ArrayList<>();
		SkillBean skillBean;
		Topic topic;
		TopicBean topicBean;
		for (Skill skill : skillDAO.findAllByOrderByName()) {
			skillBean = new SkillBean();
			skillBean.setId(skill.getId());
			skillBean.setName(skill.getName());
			skillBean.setDescription(skill.getDescription());

			topic = skill.getSkillTopic();
			topicBean = new TopicBean();
			topicBean.setId(topic.getId());
			topicBean.setName(topic.getName());
			skillBean.setSkillTopic(topicBean);

			allSkillBeans.add(skillBean);
		}
		return allSkillBeans;
	}

	@Override
	public List<SkillBean> getSkillsByTopic(final TopicBean topicBean) {
		List<SkillBean> skillBeans = new ArrayList<>();
		SkillBean skillBean;
		Optional<Topic> topicOptional = topicDAO.findById(topicBean.getId());
		if (topicOptional.isPresent()) {
			for (Skill skill : skillDAO.findBySkillTopicIdOrderByName(topicOptional.get().getId())) {
				skillBean = new SkillBean();
				skillBean.setId(skill.getId());
				skillBean.setName(skill.getName());
				skillBean.setDescription(skill.getDescription());
				skillBean.setSkillTopic(topicBean);
				skillBeans.add(skillBean);
			}
		}
		return skillBeans;
	}

}