package provalotto.datalayer.ws;

import java.util.List;

import provalotto.bean.entity.Skill;

public interface SkillWs {

	Skill createSkill(Skill skill);

	boolean deleteSkill(Skill skill);

	List<Skill> getSkillsByTopic(Long topicId);

	Skill saveSkill(Skill skill);

}