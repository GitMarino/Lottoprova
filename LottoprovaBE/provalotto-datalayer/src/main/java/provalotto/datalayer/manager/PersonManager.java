package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.utility.SearchPeopleObject;
import provalotto.datalayer.exceptions.ServiceErrorException;

public interface PersonManager {

	PersonBean createPerson(PersonBean personBean) throws ServiceErrorException;

	void createPersonAreaConnection(Integer areaId, Integer personId) throws ServiceErrorException;

	void createPersonSkillConnection(Integer personId, Integer topicId, Integer mark) throws ServiceErrorException;

	void createPersonTopicConnection(Integer personId, Integer topicId) throws ServiceErrorException;

	List<KeyValueBean> getAllPeople();

	PersonBean getPerson(Integer personId);

	List<SkillMarkBean> getPersonSkillMarks(Integer personId) throws ServiceErrorException;

	List<PersonBean> searchPeopleByBeans(SearchPeopleObject searchPeopleObject) throws ServiceErrorException;

}