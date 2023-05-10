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
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
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

	@Test
	public void getAllSkillsDataBaseExceptionTest() {
		when(skillDAO.findAllByOrderByName()).thenThrow(JDBCConnectionException.class);

		assertThrows(DataBaseException.class, () -> skillManager.getAllSkills());

	}

	@Test
	public void getAllSkillsTest() {
		Skill skill = new Skill();
		skill.setId(2l);
		skill.setName("spring");
		skill.setDescription("framework");
		skill.setTopic(null);
		List<Skill> skills = new ArrayList<>();
		skills.add(skill);

		when(skillDAO.findAllByOrderByName()).thenReturn(skills);

		List<KeyValueBean> allBeans = skillManager.getAllSkills();

		for (KeyValueBean bean : allBeans) {
			assertNotNull(bean);
			assertEquals(skill.getId(), bean.getId());
			assertEquals(skill.getName(), bean.getValue());
		}
	}

}