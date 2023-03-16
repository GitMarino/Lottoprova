package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.connection.AreaTopicConnection;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.AreaTopicConnectionKey;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.AreaTopicConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.TopicDAO;
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

	@Transactional
	@Override
	public void createArea(final String name, final Long personBeanId) throws ServiceErrorException {
		if (!areaDAO.existsByName(name)) {
			Optional<Person> personOptional = personDAO.findById(personBeanId);
			if (personOptional.isPresent()) {
				Area area = new Area();
				area.setName(name);
				area.setManager(personOptional.get());
				area.setMaker("Christian Marino");
				area.setDateTime(LocalDateTime.now());
				try {
					areaDAO.save(area);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					throw new ServiceErrorException(e);
				}
			} else {
				throw new ServiceErrorException("Dati incosistenti");
			}
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
		}
	}

	@Override
	@Transactional
	public void createAreaTopicConnection(final Long areaId, final Long topicId) throws ServiceErrorException {
		Optional<Area> areaOptional = areaDAO.findById(areaId);
		Optional<Topic> topicOptional = topicDAO.findById(topicId);

		if (areaOptional.isPresent() && topicOptional.isPresent()) {
			AreaTopicConnectionKey areaTopicConnectionKey = new AreaTopicConnectionKey();
			areaTopicConnectionKey.setArea(areaOptional.get());
			areaTopicConnectionKey.setTopic(topicOptional.get());

			AreaTopicConnection areaTopicConnection = new AreaTopicConnection();
			areaTopicConnection.setId(areaTopicConnectionKey);
			areaTopicConnection.setMaker("Christian Marino");
			areaTopicConnection.setDateTime(LocalDateTime.now());
			try {
				areaTopicConnectionDAO.save(areaTopicConnection);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceErrorException(e);
			}
		} else {
			throw new ServiceErrorException("Dati inconsistenti");
		}

	}

	@Override
	public boolean deleteArea(final Long areaBeanId) {
		try {
			Optional<Area> areaOptional = areaDAO.findById(areaBeanId);
			if (areaOptional.isPresent()) {
				areaDAO.delete(areaOptional.get());
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<BeanKeyValue> getAllAreas() {
		List<BeanKeyValue> allBeans = new ArrayList<>();
		BeanKeyValue beanKeyValue;
		for (Area area : areaDAO.findAllByOrderByName()) {
			beanKeyValue = new BeanKeyValue();
			beanKeyValue.setId(area.getId());
			beanKeyValue.setValue(area.getName());
			allBeans.add(beanKeyValue);
		}
		return allBeans;
	}

}