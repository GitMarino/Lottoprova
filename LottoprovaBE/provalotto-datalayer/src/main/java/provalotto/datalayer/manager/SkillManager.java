package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface SkillManager {

	void createSkill(String name, String description, Integer topicBeanId) throws ServiceErrorException;

	List<KeyValueBean> getAllSkills();

	List<KeyValueBean> getSkillsByTopic(Integer personId) throws ServiceErrorException;

}