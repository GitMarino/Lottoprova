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

	@Transactional
	@Override
	public void createPersonAreaConnection(final Long areaId, final Long personId) throws ServiceErrorException {

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
			throw new ServiceErrorException("Dati incosistenti");
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
			throw new ServiceErrorException("Dati incosistenti");
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
			throw new ServiceErrorException("Dati incosistenti");
		}
	}

	@Override
	public boolean deletePerson(final Long personBeanId) {
		try {
			Optional<Person> personOptional = personDAO.findById(personBeanId);
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
	public List<PersonBean> getAllPeople() {
		List<PersonBean> allPersonBeans = new ArrayList<>();
		PersonBean personBean;
		for (Person person : personDAO.findAllByOrderBySurname()) {
			personBean = new PersonBean();
			personBean.setId(person.getId());
			personBean.setUsername(person.getUsername());
			personBean.setName(person.getName());
			personBean.setSurname(person.getSurname());
			allPersonBeans.add(personBean);
		}
		return allPersonBeans;
	}

	@Override
	public List<PersonBean> getPeopleByBeans(final SearchPeopleObject searchPeopleObject) {
		List<PersonBean> peopleByAreaAndTopic = new ArrayList<>();
		PersonBean personBean;
		SkillMark skillMark;
		List<SkillMark> skillMarkList;
		for (Person person : personDAO.search(searchPeopleObject)) {
			personBean = new PersonBean();
			personBean.setId(person.getId());
			personBean.setUsername(person.getUsername());
			personBean.setName(person.getName());
			personBean.setSurname(person.getSurname());
			skillMarkList = new ArrayList<>();
			for (PersonSkillConnection personSkillConnection : personSkillConnectionDAO
					.findByIdPersonIdAndIdSkillId(person.getId(), searchPeopleObject.getSkillId())) {
				skillMark = new SkillMark();
				skillMark.setSkillName(personSkillConnection.getId().getSkill().getName());
				skillMark.setMark(personSkillConnection.getMark());
				skillMarkList.add(skillMark);
			}
			personBean.setSkillMarkList(skillMarkList);
			peopleByAreaAndTopic.add(personBean);
		}
		return peopleByAreaAndTopic;
	}

}