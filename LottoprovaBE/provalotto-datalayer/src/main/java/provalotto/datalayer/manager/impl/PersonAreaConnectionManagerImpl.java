package provalotto.datalayer.manager.impl;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.PersonAreaConnectionBean;
import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.key.PersonAreaConnectionKey;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.manager.PersonAreaConnectionManager;

@Component
public class PersonAreaConnectionManagerImpl implements PersonAreaConnectionManager {

	public static final Logger log = LoggerFactory.getLogger(PersonAreaConnectionManagerImpl.class);

	@Autowired
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@Autowired
	private AreaDAO areaDAO;

	@Autowired
	private PersonDAO personDAO;

	@Override
	public PersonAreaConnectionBean createPersonAreaConnection(final PersonAreaConnectionBean personAreaConnectionBean)
			throws ServiceErrorException {
		try {
			Area area = areaDAO.findById(personAreaConnectionBean.getAreaId()).get();
			Person person = personDAO.findById(personAreaConnectionBean.getPersonId()).get();

			PersonAreaConnectionKey personAreaConnectionKey = new PersonAreaConnectionKey();
			// NullPointerException entra nel catch?
			personAreaConnectionKey.setArea(area);
			personAreaConnectionKey.setPerson(person);

			PersonAreaConnection personAreaConnection = new PersonAreaConnection();
			personAreaConnection.setId(personAreaConnectionKey);
			personAreaConnection.setMaker("Christian Marino");
			personAreaConnection.setDateTime(LocalDateTime.now());
			personAreaConnectionDAO.save(personAreaConnection);
			return personAreaConnectionBean;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}
}