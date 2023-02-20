package provalotto.datalayer.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.entity.Skill;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.manager.SkillManager;

@Component
public class SkillManagerImpl implements SkillManager {

	public static final Logger log = LoggerFactory.getLogger(SkillManagerImpl.class);

	@Autowired
	private SkillDAO skillDAO;

	@Override
	public Skill createSkill(final Skill skill) {
		try {
			if (!skillDAO.existsById(skill.getId())) {
				return skillDAO.save(skill);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean deleteSkill(final Skill skill) {
		try {
			if (skillDAO.existsById(skill.getId())) {
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
	public List<Skill> getSkillsByTopic(final Long topicId) {
		return skillDAO.findByTopicIdOrderByName(topicId);
	}

	@Override
	public Skill saveSkill(final Skill skill) {
		try {
			return skillDAO.save(skill);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}