package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.ServiceErrorException;

@ExtendWith(MockitoExtension.class)
public class AreaManagerImplTest {

	@Mock
	private AreaDAO areaDAO;

	@Mock
	private PersonDAO personDAO;

	@Mock
	private TopicDAO topicDAO;

	@Mock
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@InjectMocks
	private AreaManagerImpl areaManager;

	@Test
	public void createAreaManagerExceptionTest() {
		Optional<Person> personOptional = Optional.empty();

		when(areaDAO.existsByName("lis")).thenReturn(false);
		when(personDAO.findById(-1)).thenReturn(personOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lis", -1, 1));
	}

	@Test
	public void createAreaNameExceptionTest() {
		when(areaDAO.existsByName("lottomatica")).thenReturn(true);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lottomatica", 7, 1));
	}

	@Test
	public void createAreaTopicConnectionExceptionTest() {
		Optional<Area> areaOptional = Optional.empty();
		Optional<Topic> topicOptional = Optional.empty();

		when(areaDAO.findById(-1)).thenReturn(areaOptional);
		when(topicDAO.findById(-1)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createAreaTopicConnection(-1, -1));

	}

	@Test
	public void createAreaTopicExcpetionTest() {
		Person person = new Person();
		person.setId(7);
		person.setSerial(80);
		person.setName("luigi");
		person.setSurname("carullo");
		Optional<Person> personOptional = Optional.of(person);
		Optional<Topic> topicOptional = Optional.empty();

		when(areaDAO.existsByName("lis")).thenReturn(false);
		when(personDAO.findById(7)).thenReturn(personOptional);
		when(topicDAO.findById(-1)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lis", 7, -1));
	}

	@Test
	public void getAllAreasDataBaseExceptionTest() {
		when(areaDAO.findAllByOrderByName()).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> areaManager.getAllAreas());

	}

	@Test
	public void getAreaTopicSkillMapDataBaseExceptionTest() {
		when(topicDAO.getMap()).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> areaManager.getAreaTopicSkillMap());

	}

}