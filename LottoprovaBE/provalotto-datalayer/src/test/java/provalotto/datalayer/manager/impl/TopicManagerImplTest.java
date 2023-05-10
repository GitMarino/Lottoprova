package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.entity.Topic;
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
	public void getAllTopicsTest() {
		Topic topic = new Topic();
		topic.setId(1l);
		topic.setName("java");
		List<Topic> topics = new ArrayList<>();
		topics.add(topic);

		when(topicDAO.findAllByOrderByName()).thenReturn(topics);

		List<KeyValueBean> allBeans = topicManager.getAllTopics();

		for (KeyValueBean bean : allBeans) {
			assertNotNull(bean);
			assertEquals(topic.getId(), bean.getId());
			assertEquals(topic.getName(), bean.getValue());
		}
	}

	@Test
	public void getTopicsSkillsByPersonDataBaseException() {
		when(personTopicConnectionDAO.findByIdPersonId(3l)).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> topicManager.getTopicsSkillsByPerson(3l));

	}
}