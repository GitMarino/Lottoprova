package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.connection.PersonSkillConnection;
import provalotto.bean.connection.PersonTopicConnection;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.PersonAreaConnectionKey;
import provalotto.bean.key.PersonSkillConnectionKey;
import provalotto.bean.key.PersonTopicConnectionKey;
import provalotto.bean.utility.SearchPeopleObject;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.PersonSkillConnectionDAO;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.PersonManager;

@Component
public class PersonManagerImpl implements PersonManager {

	public static final Logger log = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@Autowired
	private AreaDAO areaDAO;

	@Autowired
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private PersonSkillConnectionDAO personSkillConnectionDAO;

	@Autowired
	private SkillDAO skillDAO;

	@Transactional
	@Override
	public PersonBean createPerson(final PersonBean personBean) throws ServiceErrorException {
		try {
			if (!personDAO.existsBySerial(personBean.getSerial())) {
				Person person = new Person();
				person.setSerial(personBean.getSerial());
				person.setName(personBean.getName());
				person.setSurname(personBean.getSurname());
				person.setMaker("Christian Marino");
				person.setDateTime(LocalDateTime.now());
				personDAO.save(person);
				return personBean;
			}
			throw new ServiceErrorException("Dati inconsistenti");
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Transactional
	@Override
	public void createPersonAreaConnection(final Long personId, final Long areaId) throws ServiceErrorException {
		try {
			Optional<Area> areaOptional = areaDAO.findById(areaId);
			Optional<Person> personOptional = personDAO.findById(personId);
			if (areaOptional.isPresent() && personOptional.isPresent()
					&& !personAreaConnectionDAO.existsByIdPersonIdAndIdAreaId(personId, areaId)) {
				PersonAreaConnectionKey personAreaConnectionKey = new PersonAreaConnectionKey();
				personAreaConnectionKey.setArea(areaOptional.get());
				personAreaConnectionKey.setPerson(personOptional.get());

				PersonAreaConnection personAreaConnection = new PersonAreaConnection();
				personAreaConnection.setId(personAreaConnectionKey);
				personAreaConnection.setMaker("Christian Marino");
				personAreaConnection.setDateTime(LocalDateTime.now());
				personAreaConnectionDAO.save(personAreaConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Transactional
	@Override
	public void createPersonSkillConnection(final Long personId, final Long skillId, final Integer mark)
			throws ServiceErrorException {

		try {
			Person personDB;
			Skill skillDB;

			// save PersonSkillConnection
			Optional<Person> personOptional = personDAO.findById(personId);
			Optional<Skill> skillOptional = skillDAO.findById(skillId);
			if (personOptional.isPresent() && skillOptional.isPresent()
					&& !personSkillConnectionDAO.existsByIdPersonIdAndIdSkillId(personId, skillId)) {
				personDB = personOptional.get();
				skillDB = skillOptional.get();

				PersonSkillConnectionKey personSkillConnectionKey = new PersonSkillConnectionKey();
				personSkillConnectionKey.setPerson(personDB);
				personSkillConnectionKey.setSkill(skillDB);

				PersonSkillConnection personSkillConnection = new PersonSkillConnection();
				personSkillConnection.setId(personSkillConnectionKey);
				personSkillConnection.setMark(mark);
				personSkillConnection.setMaker("Christian Marino");
				personSkillConnection.setDateTime(LocalDateTime.now());
				personSkillConnectionDAO.save(personSkillConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}

			// save PersonTopicConnection
			Topic topic = skillDB.getTopic();
			if (topic != null && personTopicConnectionDAO.existsByIdPersonIdAndIdTopicId(personId, topic.getId())) {
				PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
				personTopicConnectionKey.setPerson(personDB);
				personTopicConnectionKey.setTopic(topic);

				PersonTopicConnection personTopicConnection = new PersonTopicConnection();
				personTopicConnection.setId(personTopicConnectionKey);
				personTopicConnection.setMaker("Christian Marino");
				personTopicConnection.setDateTime(LocalDateTime.now());
				personTopicConnectionDAO.save(personTopicConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}

		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Transactional
	@Override
	public void createPersonTopicConnection(final Long personId, final Long topicId) throws ServiceErrorException {
		try {
			Optional<Person> personOptional = personDAO.findById(personId);
			Optional<Topic> topicOptional = topicDAO.findById(topicId);

			if (personOptional.isPresent() && topicOptional.isPresent()
					&& !personTopicConnectionDAO.existsByIdPersonIdAndIdTopicId(personId, topicId)) {
				PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
				personTopicConnectionKey.setPerson(personOptional.get());
				personTopicConnectionKey.setTopic(topicOptional.get());

				PersonTopicConnection personTopicConnection = new PersonTopicConnection();
				personTopicConnection.setId(personTopicConnectionKey);
				personTopicConnection.setMaker("Christian Marino");
				personTopicConnection.setDateTime(LocalDateTime.now());
				personTopicConnectionDAO.save(personTopicConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public boolean deletePerson(final Long personId) {
		try {
			Optional<Person> personOptional = personDAO.findById(personId);
			if (personOptional.isPresent()) {
				personDAO.delete(personOptional.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<KeyValueBean> getAllPeople() throws ServiceErrorException {
		List<KeyValueBean> allBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Person person : personDAO.findAllByOrderBySurname()) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(person.getId());
				beanKeyValue.setValue(person.getSurname());
				allBeans.add(beanKeyValue);
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public PersonBean getPerson(final Long personId) throws ServiceErrorException {
		PersonBean personBean;
		try {
			Optional<Person> personOptional = personDAO.findById(personId);
			if (personOptional.isPresent()) {
				Person person = personOptional.get();

				personBean = new PersonBean();
				personBean.setId(person.getId());
				personBean.setSerial(person.getSerial());
				personBean.setName(person.getName());
				personBean.setSurname(person.getSurname());

				return personBean;

			}
			throw new ServiceErrorException("Dati inconsistenti");
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<SkillMarkBean> getPersonSkillMarks(final Long personId) throws ServiceErrorException {
		List<SkillMarkBean> skillMarkList = new ArrayList<>();
		SkillMarkBean skillMark;
		try {
			for (PersonSkillConnection personSkillConnection : personSkillConnectionDAO.findByIdPersonId(personId)) {
				skillMark = new SkillMarkBean();
				skillMark.setSkillName(personSkillConnection.getId().getSkill().getName());
				skillMark.setMark(personSkillConnection.getMark());
				skillMarkList.add(skillMark);
			}
			return skillMarkList;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<PersonBean> searchPeopleByBeans(final SearchPeopleObject searchPeopleObject)
			throws ServiceErrorException {
		List<PersonBean> peopleByBeans = new ArrayList<>();
		PersonBean personBean;
		try {
			for (Person person : personDAO.searchPeople(searchPeopleObject)) {
				personBean = new PersonBean();
				personBean.setId(person.getId());
				personBean.setSerial(person.getSerial());
				personBean.setName(person.getName());
				personBean.setSurname(person.getSurname());
				peopleByBeans.add(personBean);
			}
			return peopleByBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

}