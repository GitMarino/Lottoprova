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

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SearchPeopleObject;
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
import provalotto.bean.utility.SkillMark;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.PersonSkillConnectionDAO;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
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
		if (!personDAO.existsByUsername(personBean.getUsername())) {
			Person person = new Person();
			person.setUsername(personBean.getUsername());
			person.setName(personBean.getName());
			person.setSurname(personBean.getSurname());
			person.setMaker("Christian Marino");
			person.setDateTime(LocalDateTime.now());
			try {
				personDAO.save(person);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
			return personBean;
		}
		throw new ServiceErrorException("Dati inconsistenti");
	}

	@Transactional
	@Override
	public void createPersonAreaConnection(final Long personId, final Long areaId) throws ServiceErrorException {

		Optional<Area> areaOptional = areaDAO.findById(areaId);
		Optional<Person> personOptional = personDAO.findById(personId);
		if (areaOptional.isPresent() && personOptional.isPresent()) {
			PersonAreaConnectionKey personAreaConnectionKey = new PersonAreaConnectionKey();
			personAreaConnectionKey.setArea(areaOptional.get());
			personAreaConnectionKey.setPerson(personOptional.get());

			PersonAreaConnection personAreaConnection = new PersonAreaConnection();
			personAreaConnection.setId(personAreaConnectionKey);
			personAreaConnection.setMaker("Christian Marino");
			personAreaConnection.setDateTime(LocalDateTime.now());
			try {
				personAreaConnectionDAO.save(personAreaConnection);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
		}

	}

	@Transactional
	@Override
	public void createPersonSkillConnection(final Long personId, final Long skillId, final Integer mark)
			throws ServiceErrorException {

		Optional<Person> personOptional = personDAO.findById(personId);
		Optional<Skill> skillOptional = skillDAO.findById(skillId);
		if (personOptional.isPresent() && skillOptional.isPresent()) {
			PersonSkillConnectionKey personSkillConnectionKey = new PersonSkillConnectionKey();
			personSkillConnectionKey.setPerson(personOptional.get());
			personSkillConnectionKey.setSkill(skillOptional.get());

			PersonSkillConnection personSkillConnection = new PersonSkillConnection();
			personSkillConnection.setId(personSkillConnectionKey);
			personSkillConnection.setMark(mark);
			personSkillConnection.setMaker("Christian Marino");
			personSkillConnection.setDateTime(LocalDateTime.now());
			try {
				personSkillConnectionDAO.save(personSkillConnection);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
		}
	}

	@Transactional
	@Override
	public void createPersonTopicConnection(final Long personId, final Long topicId) throws ServiceErrorException {

		Optional<Person> personOptional = personDAO.findById(personId);
		Optional<Topic> topicOptional = topicDAO.findById(topicId);

		if (personOptional.isPresent() && topicOptional.isPresent()) {
			PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
			personTopicConnectionKey.setPerson(personOptional.get());
			personTopicConnectionKey.setTopic(topicOptional.get());

			PersonTopicConnection personTopicConnection = new PersonTopicConnection();
			personTopicConnection.setId(personTopicConnectionKey);
			personTopicConnection.setMaker("Christian Marino");
			personTopicConnection.setDateTime(LocalDateTime.now());
			try {
				personTopicConnectionDAO.save(personTopicConnection);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
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
	public List<BeanKeyValue> getAllPeople() {
		List<BeanKeyValue> allBeans = new ArrayList<>();
		BeanKeyValue beanKeyValue;
		for (Person person : personDAO.findAllByOrderBySurname()) {
			beanKeyValue = new BeanKeyValue();
			beanKeyValue.setId(person.getId());
			beanKeyValue.setValue(person.getUsername());
			allBeans.add(beanKeyValue);
		}
		return allBeans;
	}

	@Override
	public PersonBean getPerson(final Long personId) throws ServiceErrorException {
		PersonBean personBean;
		Optional<Person> personOptional = personDAO.findById(personId);
		if (personOptional.isPresent()) {
			Person person = personOptional.get();

			personBean = new PersonBean();
			personBean.setId(person.getId());
			personBean.setUsername(person.getUsername());
			personBean.setName(person.getName());
			personBean.setSurname(person.getSurname());

			return personBean;
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
		}
	}

	@Override
	public List<SkillMark> getPersonSkillMarks(final Long personId) {
		List<SkillMark> skillMarkList = new ArrayList<>();
		SkillMark skillMark;
		for (PersonSkillConnection personSkillConnection : personSkillConnectionDAO.findByIdPersonId(personId)) {
			skillMark = new SkillMark();
			skillMark.setSkillName(personSkillConnection.getId().getSkill().getName());
			skillMark.setMark(personSkillConnection.getMark());
			skillMarkList.add(skillMark);
		}
		return skillMarkList;
	}

	@Override
	public List<PersonBean> searchPeopleByBeans(final SearchPeopleObject searchPeopleObject) {
		List<PersonBean> peopleByBeans = new ArrayList<>();
		PersonBean personBean;
		for (Person person : personDAO.searchPeople(searchPeopleObject)) {
			personBean = new PersonBean();
			personBean.setId(person.getId());
			personBean.setUsername(person.getUsername());
			personBean.setName(person.getName());
			personBean.setSurname(person.getSurname());
			peopleByBeans.add(personBean);
		}
		return peopleByBeans;
	}

}