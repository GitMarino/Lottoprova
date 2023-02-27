package provalotto.datalayer.manager.impl;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.PersonTopicConnectionBean;
import provalotto.bean.connection.PersonTopicConnection;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.PersonTopicConnectionKey;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.manager.PersonTopicConnectionManager;

@Component
public class PersonTopicConnectionManagerImpl implements PersonTopicConnectionManager {

	public static final Logger log = LoggerFactory.getLogger(PersonAreaConnectionManagerImpl.class);

	@Autowired
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Override
	public PersonTopicConnectionBean createPersonTopicConnection(
			final PersonTopicConnectionBean personTopicConnectionBean) throws ServiceErrorException {
		try {
			Person person = personDAO.findById(personTopicConnectionBean.getPersonId()).get();
			Topic topic = topicDAO.findById(personTopicConnectionBean.getTopicId()).get();

			PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
			personTopicConnectionKey.setPerson(person);
			personTopicConnectionKey.setTopic(topic);

			PersonTopicConnection personTopicConnection = new PersonTopicConnection();
			// NullPointerException entra nel catch?
			personTopicConnection.setId(personTopicConnectionKey);
			personTopicConnection.setMaker("Christian Marino");
			personTopicConnection.setDateTime(LocalDateTime.now());
			personTopicConnectionDAO.save(personTopicConnection);
			return personTopicConnectionBean;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

}