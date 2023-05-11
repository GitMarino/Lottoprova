package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.PersonSkillConnectionDAO;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.InconsistentDataException;
import provalotto.datalayer.exceptions.ServiceErrorException;

@ExtendWith(MockitoExtension.class)
public class PersonManagerImplTest {

	@Mock
	private AreaDAO areaDAO;

	@Mock
	private PersonDAO personDAO;

	@Mock
	private SkillDAO skillDAO;

	@Mock
	private TopicDAO topicDAO;

	@Mock
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@Mock
	private PersonSkillConnectionDAO personSkillConnectionDAO;

	@Mock
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@InjectMocks
	private PersonManagerImpl personManager;

	@Test
	public void createPersonAreaConnectionExceptionTest() {
		Optional<Area> areaOptional = Optional.empty();
		Optional<Person> personOptional = Optional.empty();

		when(areaDAO.findById(-1)).thenReturn(areaOptional);
		when(personDAO.findById(-1)).thenReturn(personOptional);

		assertThrows(ServiceErrorException.class, () -> personManager.createPersonAreaConnection(-1, -1));
	}

	@Test
	public void createPersonExceptionTest() {
		PersonBean personBean = new PersonBean();
		personBean.setSerial(237);
		personBean.setName("christian");
		personBean.setSurname("marino");

		when(personDAO.existsBySerial(237)).thenReturn(true);

		assertThrows(ServiceErrorException.class, () -> personManager.createPerson(personBean));
	}

	@Test
	public void createPersonSkillConnectionExceptionTest() {
		Optional<Person> personOptional = Optional.empty();
		Optional<Skill> skillOptional = Optional.empty();

		when(personDAO.findById(-1)).thenReturn(personOptional);
		when(skillDAO.findById(-1)).thenReturn(skillOptional);

		assertThrows(ServiceErrorException.class, () -> personManager.createPersonSkillConnection(-1, -1, 5));
	}

	@Test
	public void createPersonSkillConnectionTopicExceptionTest() {
		Person person = new Person();
		person.setId(3);
		person.setSerial(237);
		person.setName("christian");
		person.setSurname("marino");
		Optional<Person> personOptional = Optional.of(person);

		Skill skill = new Skill();
		skill.setId(2);
		skill.setName("spring");
		skill.setDescription("framework");
		skill.setTopic(null);
		Optional<Skill> skillOptional = Optional.of(skill);

		when(personDAO.findById(3)).thenReturn(personOptional);
		when(skillDAO.findById(2)).thenReturn(skillOptional);
		when(personSkillConnectionDAO.existsByIdPersonIdAndIdSkillId(3, 2)).thenReturn(false);

		assertThrows(ServiceErrorException.class, () -> personManager.createPersonSkillConnection(3, 2, 3));

	}

	@Test
	public void createPersonTopicConnectionExceptionTest() {
		Optional<Person> personOptional = Optional.empty();
		Optional<Topic> topicOptional = Optional.empty();

		when(personDAO.findById(-1)).thenReturn(personOptional);
		when(topicDAO.findById(-1)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> personManager.createPersonTopicConnection(-1, -1));

	}

	@Test
	public void getAllPeopleDataBaseExceptionTest() {
		when(personDAO.findAllByOrderBySurname()).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> personManager.getAllPeople());

	}

	@Test
	public void getAllPeopleTest() {
		Person person = new Person();
		person.setId(3);
		person.setSerial(237);
		person.setName("christian");
		person.setSurname("marino");
		List<Person> people = new ArrayList<>();
		people.add(person);

		when(personDAO.findAllByOrderBySurname()).thenReturn(people);

		List<KeyValueBean> allBeans = personManager.getAllPeople();

		for (KeyValueBean bean : allBeans) {
			assertNotNull(bean);
			assertEquals(person.getId(), bean.getId());
			assertEquals(person.getSurname(), bean.getValue());
		}
	}

	@Test
	public void getPersonDataBaseExceptionTest() {
		when(personDAO.findById(3)).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> personManager.getPerson(3));

	}

	@Test
	public void getPersonInconsistentDataExceptionTest() {
		Optional<Person> personOptional = Optional.empty();

		when(personDAO.findById(-1)).thenReturn(personOptional);

		assertThrows(InconsistentDataException.class, () -> personManager.getPerson(-1));
	}

	@Test
	public void getPersonTest() {
		Person person = new Person();
		person.setId(3);
		person.setSerial(237);
		person.setName("christian");
		person.setSurname("marino");
		Optional<Person> personOptional = Optional.of(person);

		when(personDAO.findById(3)).thenReturn(personOptional);

		PersonBean personBean = personManager.getPerson(3);

		assertNotNull(personBean);
		assertEquals(person.getId(), personBean.getId());
		assertEquals(person.getSerial(), personBean.getSerial());
		assertEquals(person.getName(), personBean.getName());
		assertEquals(person.getSurname(), personBean.getSurname());
	}

}