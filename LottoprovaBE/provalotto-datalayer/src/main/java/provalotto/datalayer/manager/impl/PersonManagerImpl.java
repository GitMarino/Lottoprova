package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.PersonBean;
import provalotto.bean.entity.Person;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.manager.PersonManager;

@Component
public class PersonManagerImpl implements PersonManager {

	public static final Logger log = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonDAO personDAO;

	@Override
	public PersonBean createPerson(final PersonBean personBean) throws ServiceErrorException {
		try {
			Person person = new Person();
			person.setUsername(personBean.getUsername());
			person.setName(personBean.getName());
			person.setSurname(personBean.getSurname());
			person.setMaker("Christian Marino");
			person.setDateTime(LocalDateTime.now());
			personDAO.save(person);
			return personBean;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public boolean deletePerson(final Long personBeanId) {
		try {
			Person person = personDAO.findById(personBeanId).get();
			if (person != null) {
				personDAO.delete(person);
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<PersonBean> getAllPeople() {
		List<PersonBean> allPersonBeans = new ArrayList<>();
		PersonBean personBean;
		for (Person person : personDAO.findAllByOrderBySurname()) {
			personBean = new PersonBean();
			personBean.setId(person.getId());
			personBean.setUsername(personBean.getUsername());
			personBean.setName(personBean.getName());
			personBean.setSurname(person.getSurname());
			allPersonBeans.add(personBean);
		}
		return allPersonBeans;
	}

}