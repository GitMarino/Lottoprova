package provalotto.datalayer.manager.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.ServiceErrorException;

@ExtendWith(MockitoExtension.class)
public class SkillManagerImplTest {

	@Mock
	private SkillDAO skillDAO;

	@Mock
	private TopicDAO topicDAO;

	@InjectMocks
	private SkillManagerImpl skillManager;

	@Test
	public void createSkillNameExceptionTest() {
		when(skillDAO.existsByName("spring")).thenReturn(true);

		assertThrows(ServiceErrorException.class, () -> skillManager.createSkill("spring", "framework", (long) 1));

	}

	@Test
	public void createSkillTopicException() {
		Optional<Topic> topicOptional = Optional.empty();

		when(topicDAO.findById((long) -1)).thenReturn(topicOptional);

		assertThrows(ServiceErrorException.class, () -> skillManager.createSkill("spring", "framework", (long) -1));
	}

}