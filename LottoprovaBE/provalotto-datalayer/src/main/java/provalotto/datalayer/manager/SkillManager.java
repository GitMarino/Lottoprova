package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface SkillManager {

	void createSkill(String name, String description, Long topicBeanId) throws ServiceErrorException;

	List<KeyValueBean> getAllSkills() throws ServiceErrorException;

	List<KeyValueBean> getSkillsByTopic(Long personId) throws ServiceErrorException;

}