package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.utility.SearchPeopleObject;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface PersonManager {

	PersonBean createPerson(PersonBean personBean) throws ServiceErrorException;

	void createPersonAreaConnection(Long areaId, Long personId) throws ServiceErrorException;

	void createPersonSkillConnection(Long personId, Long topicId, Integer mark) throws ServiceErrorException;

	void createPersonTopicConnection(Long personId, Long topicId) throws ServiceErrorException;

	List<KeyValueBean> getAllPeople();

	PersonBean getPerson(Long personId);

	List<SkillMarkBean> getPersonSkillMarks(Long personId) throws ServiceErrorException;

	List<PersonBean> searchPeopleByBeans(SearchPeopleObject searchPeopleObject) throws ServiceErrorException;

}