package provalotto.datalayer.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.entity.Person;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.manager.PersonManager;

@Component
public class PersonManagerImpl implements PersonManager {

	public static final Logger log = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonDAO personDAO;

	@Override
	public Person createPerson(final Person person) {
		try {
			if (!personDAO.existsById(person.getId())) {
				return personDAO.save(person);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean deletePerson(final Person person) {
		try {
			if (personDAO.existsById(person.getId())) {
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
	public List<Person> getAllPeople() {
		return personDAO.findAllByOrderBySurname();
	}

	@Override
	public Person savePerson(final Person person) {
		try {
			return personDAO.save(person);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}