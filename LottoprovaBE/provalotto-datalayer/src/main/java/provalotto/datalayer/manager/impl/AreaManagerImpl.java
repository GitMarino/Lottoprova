package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.connection.AreaTopicConnection;
import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.AreaTopicConnectionKey;
import provalotto.bean.key.PersonAreaConnectionKey;
import provalotto.bean.utility.ATSKeyValue;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.AreaTopicConnectionDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.AreaManager;

@Component
public class AreaManagerImpl implements AreaManager {

	public static final Logger log = LoggerFactory.getLogger(AreaManagerImpl.class);

	@Autowired
	private AreaDAO areaDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private AreaTopicConnectionDAO areaTopicConnectionDAO;

	@Autowired
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void createArea(final String name, final Integer personBeanId, final Integer topicBeanId)
			throws ServiceErrorException {
		try {
			Area areaDB;
			Person personDB;

			// save area
			if (!areaDAO.existsByName(name)) {
				Optional<Person> personOptional = personDAO.findById(personBeanId);
				if (personOptional.isPresent()) {
					personDB = personOptional.get();

					Area area = new Area();
					area.setName(name);
					area.setManager(personDB);
					area.setMaker("Christian Marino");
					area.setDateTime(LocalDateTime.now());
					areaDB = areaDAO.save(area);
				} else {
					throw new ServiceErrorException("Dati incosistenti");
				}
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}

			// save PersonAreaConnection
			PersonAreaConnectionKey personAreaConnectionKey = new PersonAreaConnectionKey();
			personAreaConnectionKey.setArea(areaDB);
			personAreaConnectionKey.setPerson(personDB);
			PersonAreaConnection personAreaConnection = new PersonAreaConnection();
			personAreaConnection.setId(personAreaConnectionKey);
			personAreaConnection.setMaker("Christian Marino");
			personAreaConnection.setDateTime(LocalDateTime.now());
			personAreaConnectionDAO.save(personAreaConnection);

			// save AreaTopicConnection
			Optional<Topic> topicOptional = topicDAO.findById(topicBeanId);
			if (topicOptional.isPresent()) {
				AreaTopicConnectionKey areaTopicConnectionKey = new AreaTopicConnectionKey();
				areaTopicConnectionKey.setArea(areaDB);
				areaTopicConnectionKey.setTopic(topicOptional.get());

				AreaTopicConnection areaTopicConnection = new AreaTopicConnection();
				areaTopicConnection.setId(areaTopicConnectionKey);
				areaTopicConnection.setMaker("Christian Marino");
				areaTopicConnection.setDateTime(LocalDateTime.now());
				areaTopicConnectionDAO.save(areaTopicConnection);

			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}

		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void createAreaTopicConnection(final Integer areaId, final Integer topicId) throws ServiceErrorException {
		try {
			Optional<Area> areaOptional = areaDAO.findById(areaId);
			Optional<Topic> topicOptional = topicDAO.findById(topicId);

			if (areaOptional.isPresent() && topicOptional.isPresent()
					&& !areaTopicConnectionDAO.existsByIdAreaIdAndIdTopicId(areaId, topicId)) {
				AreaTopicConnectionKey areaTopicConnectionKey = new AreaTopicConnectionKey();
				areaTopicConnectionKey.setArea(areaOptional.get());
				areaTopicConnectionKey.setTopic(topicOptional.get());

				AreaTopicConnection areaTopicConnection = new AreaTopicConnection();
				areaTopicConnection.setId(areaTopicConnectionKey);
				areaTopicConnection.setMaker("Christian Marino");
				areaTopicConnection.setDateTime(LocalDateTime.now());
				areaTopicConnectionDAO.save(areaTopicConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}
		} catch (ServiceErrorException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<KeyValueBean> getAllAreas() {
		List<KeyValueBean> allBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Area area : areaDAO.findAllByOrderByName()) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(area.getId());
				beanKeyValue.setValue(area.getName());
				allBeans.add(beanKeyValue);
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public List<KeyValueBean> getAreasByPerson(final Integer personId) throws ServiceErrorException {
		List<KeyValueBean> areaBeans = new ArrayList<>();
		KeyValueBean beanKeyValue;
		try {
			for (Area area : areaDAO.findAreasByPerson(personId)) {
				beanKeyValue = new KeyValueBean();
				beanKeyValue.setId(area.getId());
				beanKeyValue.setValue(area.getName());
				areaBeans.add(beanKeyValue);
			}
			return areaBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<List<KeyValueBean>> getAreaTopicSkillMap() {
		try {
			List<List<KeyValueBean>> ATSmap = new ArrayList<>();
			List<KeyValueBean> ATSrow;
			KeyValueBean keyValueBean;
			for (ATSKeyValue ATSinterfaceRow : topicDAO.getMap()) {
				ATSrow = new ArrayList<>();

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getAreaId());
				keyValueBean.setValue(ATSinterfaceRow.getAreaName());
				ATSrow.add(keyValueBean);

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getTopicId());
				keyValueBean.setValue(ATSinterfaceRow.getTopicName());
				ATSrow.add(keyValueBean);

				keyValueBean = new KeyValueBean();
				keyValueBean.setId(ATSinterfaceRow.getSkillId());
				keyValueBean.setValue(ATSinterfaceRow.getSkillName());
				ATSrow.add(keyValueBean);

				ATSmap.add(ATSrow);
			}
			return ATSmap;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

}