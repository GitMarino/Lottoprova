package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.bean.TopicBean;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.ServiceErrorException;

@ExtendWith(MockitoExtension.class)
public class TopicManagerImplTest {

	@Mock
	private TopicDAO topicDAO;

	@Mock
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@InjectMocks
	private TopicManagerImpl topicManager;

	@Test
	public void createTopicExceptionTest() {
		TopicBean topicBean = new TopicBean();
		topicBean.setName("java");

		when(topicDAO.existsByName(topicBean.getName())).thenReturn(true);

		assertThrows(ServiceErrorException.class, () -> topicManager.createTopic(topicBean));
	}

	@Test
	public void getAllTopicsDataBaseExceptionTest() {
		when(topicDAO.findAllByOrderByName()).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> topicManager.getAllTopics());

	}

	@Test
	public void getTopicsSkillsByPersonDataBaseException() {
		when(personTopicConnectionDAO.findByIdPersonId(3)).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> topicManager.getTopicsSkillsByPerson(3));

	}
}