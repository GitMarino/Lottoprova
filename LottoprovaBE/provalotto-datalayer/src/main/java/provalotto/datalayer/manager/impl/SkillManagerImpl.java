package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public SkillBean createSkill(final SkillBean skillBean) throws ServiceErrorException {
		Skill skill = new Skill();
		skill.setName(skillBean.getName());
		skill.setDescription(skillBean.getDescription());
		Topic topic = topicDAO.findById(skillBean.getSkillTopic().getId()).get();
		if (topic != null) {
			skill.setSkillTopic(topic);
		}
		skill.setMaker("Christian Marino");
		skill.setDateTime(LocalDateTime.now());
		skillDAO.save(skill);
		return skillBean;
	}

	@Override
	public boolean deleteSkill(final Long skillBeanId) {
		try {
			Skill skill = skillDAO.findById(skillBeanId).get();
			if (skill != null) {
				skillDAO.delete(skill);
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
		Topic topic = topicDAO.findById(topicBean.getId()).get();
		if (topic != null) {
			for (Skill skill : skillDAO.findBySkillTopicIdOrderByName(topic.getId())) {
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