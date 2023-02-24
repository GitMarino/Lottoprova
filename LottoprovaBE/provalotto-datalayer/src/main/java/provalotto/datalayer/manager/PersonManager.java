package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.PersonBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface PersonManager {

	PersonBean createPerson(PersonBean personBean) throws ServiceErrorException;

	boolean deletePerson(Long personBeanId);

	List<PersonBean> getAllPeople();

}