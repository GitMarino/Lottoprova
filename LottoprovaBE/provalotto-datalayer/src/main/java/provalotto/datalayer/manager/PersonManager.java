package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SearchPeopleObject;
import provalotto.bean.utility.SkillMark;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface PersonManager {

	PersonBean createPerson(PersonBean personBean) throws ServiceErrorException;

	void createPersonAreaConnection(Long areaId, Long personId) throws ServiceErrorException;

	void createPersonSkillConnection(Long personId, Long topicId, Integer mark) throws ServiceErrorException;

	void createPersonTopicConnection(Long personId, Long topicId) throws ServiceErrorException;

	boolean deletePerson(Long personId);

	List<BeanKeyValue> getAllPeople();

	PersonBean getPerson(Long personId) throws ServiceErrorException;;

	List<SkillMark> getPersonSkillMarks(Long personId);

	List<PersonBean> searchPeopleByBeans(SearchPeopleObject searchPeopleObject);
}