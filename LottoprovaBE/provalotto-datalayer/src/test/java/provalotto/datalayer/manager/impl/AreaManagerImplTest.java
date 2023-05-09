package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.TopicDAO;
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
		when(personDAO.findById((long) -1)).thenReturn(personOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lis", (long) -1, (long) 1));
	}

	@Test
	public void createAreaNameExceptionTest() {
		when(areaDAO.existsByName("lottomatica")).thenReturn(true);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lottomatica", (long) 7, (long) 1));
	}

	@Test
	public void createAreaTopicConnectionExceptionTest() {
		Optional<Area> areaOptional = Optional.empty();
		Optional<Topic> topicOptional = Optional.empty();

		when(areaDAO.findById(-1l)).thenReturn(areaOptional);
		when(topicDAO.findById(-1l)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createAreaTopicConnection(-1l, -1l));

	}

	@Test
	public void createAreaTopicExcpetionTest() {
		Person person = new Person();
		person.setId((long) 7);
		person.setSerial((long) 80);
		person.setName("luigi");
		person.setSurname("carullo");
		Optional<Person> personOptional = Optional.of(person);
		Optional<Topic> topicOptional = Optional.empty();

		when(areaDAO.existsByName("lis")).thenReturn(false);
		when(personDAO.findById((long) 7)).thenReturn(personOptional);
		when(topicDAO.findById((long) -1)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> areaManager.createArea("lis", (long) 7, (long) -1));
	}

	@Test
	public void getAllAreaTest() {
		Person person = new Person();
		person.setId((long) 7);
		person.setSerial((long) 80);
		person.setName("luigi");
		person.setSurname("carullo");
		Area area = new Area();
		area.setId(1l);
		area.setName("lis");
		area.setManager(person);
		List<Area> areas = new ArrayList<>();
		areas.add(area);

		when(areaDAO.findAllByOrderByName()).thenReturn(areas);

		List<KeyValueBean> allBeans = areaManager.getAllAreas();

		for (KeyValueBean bean : allBeans) {
			assertNotNull(bean);
			assertEquals(area.getId(), bean.getId());
			assertEquals(area.getName(), bean.getValue());
		}
	}

}