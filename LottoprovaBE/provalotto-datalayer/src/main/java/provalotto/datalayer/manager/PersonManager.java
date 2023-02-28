package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SearchPeopleObject;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface PersonManager {

	PersonBean createPerson(PersonBean personBean) throws ServiceErrorException;

	void createPersonAreaConnection(Long areaId, Long personId) throws ServiceErrorException;

	void createPersonSkillConnection(Long personId, Long topicId, Integer mark) throws ServiceErrorException;

	void createPersonTopicConnection(Long personId, Long topicId) throws ServiceErrorException;

	boolean deletePerson(Long personBeanId);

	List<PersonBean> getAllPeople();

	List<PersonBean> getPeopleByBeans(SearchPeopleObject searchPeopleObject);
}