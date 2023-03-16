package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface SkillManager {

	void createSkill(String name, String description, Long topicBeanId) throws ServiceErrorException;

	boolean deleteSkill(Long skillBeanId);

	List<BeanKeyValue> getAllSkills();

}