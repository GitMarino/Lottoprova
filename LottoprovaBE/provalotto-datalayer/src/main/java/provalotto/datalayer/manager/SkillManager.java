package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.SkillBean;
import provalotto.bean.bean.TopicBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface SkillManager {

	void createSkill(String name, String description, Long skillTopicBeanId) throws ServiceErrorException;

	boolean deleteSkill(Long skillBeanId);

	List<SkillBean> getAllSkills();

	List<SkillBean> getSkillsByTopic(TopicBean topicBean);

}