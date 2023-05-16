package provalotto.datalayer.manager.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import provalotto.bean.bean.FileBean;
import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.connection.PersonSkillConnection;
import provalotto.bean.connection.PersonTopicConnection;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.PersonAreaConnectionKey;
import provalotto.bean.key.PersonSkillConnectionKey;
import provalotto.bean.key.PersonTopicConnectionKey;
import provalotto.bean.utility.SearchPeopleObject;
import provalotto.bean.utility.SkillMark;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.dao.PersonAreaConnectionDAO;
import provalotto.datalayer.dao.PersonDAO;
import provalotto.datalayer.dao.PersonSkillConnectionDAO;
import provalotto.datalayer.dao.PersonTopicConnectionDAO;
import provalotto.datalayer.dao.SkillDAO;
import provalotto.datalayer.dao.TopicDAO;
import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.InconsistentDataException;
import provalotto.datalayer.exceptions.ServiceErrorException;
import provalotto.datalayer.manager.PersonManager;
import provalotto.datalayer.utility.EntityBeanMapper;

@Component
public class PersonManagerImpl implements PersonManager {

	public static final Logger log = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private PersonAreaConnectionDAO personAreaConnectionDAO;

	@Autowired
	private AreaDAO areaDAO;

	@Autowired
	private PersonTopicConnectionDAO personTopicConnectionDAO;

	@Autowired
	private TopicDAO topicDAO;

	@Autowired
	private PersonSkillConnectionDAO personSkillConnectionDAO;

	@Autowired
	private SkillDAO skillDAO;

	@Autowired
	private EntityBeanMapper mapper;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public PersonBean createPerson(final PersonBean personBean) throws ServiceErrorException {
		try {
			if (!personDAO.existsBySerial(personBean.getSerial())) {
				Person person = mapper.mapPersonBean(personBean);
				person.setMaker("Christian Marino");
				person.setDateTime(LocalDateTime.now());
				personDAO.save(person);
				return personBean;
			}
			throw new ServiceErrorException("Dati inconsistenti");
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
	public void createPersonAreaConnection(final Integer personId, final Integer areaId) throws ServiceErrorException {
		try {
			Optional<Area> areaOptional = areaDAO.findById(areaId);
			Optional<Person> personOptional = personDAO.findById(personId);
			if (areaOptional.isPresent() && personOptional.isPresent()
					&& !personAreaConnectionDAO.existsByIdPersonIdAndIdAreaId(personId, areaId)) {
				PersonAreaConnectionKey personAreaConnectionKey = new PersonAreaConnectionKey();
				personAreaConnectionKey.setArea(areaOptional.get());
				personAreaConnectionKey.setPerson(personOptional.get());

				PersonAreaConnection personAreaConnection = new PersonAreaConnection();
				personAreaConnection.setId(personAreaConnectionKey);
				personAreaConnection.setMaker("Christian Marino");
				personAreaConnection.setDateTime(LocalDateTime.now());
				personAreaConnectionDAO.save(personAreaConnection);
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
	public void createPersonSkillConnection(final Integer personId, final Integer skillId, final Integer mark)
			throws ServiceErrorException {

		try {
			Person personDB;
			Skill skillDB;

			// save PersonSkillConnection
			Optional<Person> personOptional = personDAO.findById(personId);
			Optional<Skill> skillOptional = skillDAO.findById(skillId);
			if (personOptional.isPresent() && skillOptional.isPresent()
					&& !personSkillConnectionDAO.existsByIdPersonIdAndIdSkillId(personId, skillId)) {
				personDB = personOptional.get();
				skillDB = skillOptional.get();

				PersonSkillConnectionKey personSkillConnectionKey = new PersonSkillConnectionKey();
				personSkillConnectionKey.setPerson(personDB);
				personSkillConnectionKey.setSkill(skillDB);

				PersonSkillConnection personSkillConnection = new PersonSkillConnection();
				personSkillConnection.setId(personSkillConnectionKey);
				personSkillConnection.setMark(mark);
				personSkillConnection.setMaker("Christian Marino");
				personSkillConnection.setDateTime(LocalDateTime.now());
				personSkillConnectionDAO.save(personSkillConnection);
			} else {
				throw new ServiceErrorException("Dati inconsistenti");
			}

			// save PersonTopicConnection
			Topic topic = skillDB.getTopic();
			if (topic != null) {
				if (!personTopicConnectionDAO.existsByIdPersonIdAndIdTopicId(personId, topic.getId())) {
					PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
					personTopicConnectionKey.setPerson(personDB);
					personTopicConnectionKey.setTopic(topic);

					PersonTopicConnection personTopicConnection = new PersonTopicConnection();
					personTopicConnection.setId(personTopicConnectionKey);
					personTopicConnection.setMaker("Christian Marino");
					personTopicConnection.setDateTime(LocalDateTime.now());
					personTopicConnectionDAO.save(personTopicConnection);
				}
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
	public void createPersonTopicConnection(final Integer personId, final Integer topicId)
			throws ServiceErrorException {
		try {
			Optional<Person> personOptional = personDAO.findById(personId);
			Optional<Topic> topicOptional = topicDAO.findById(topicId);

			if (personOptional.isPresent() && topicOptional.isPresent()
					&& !personTopicConnectionDAO.existsByIdPersonIdAndIdTopicId(personId, topicId)) {
				PersonTopicConnectionKey personTopicConnectionKey = new PersonTopicConnectionKey();
				personTopicConnectionKey.setPerson(personOptional.get());
				personTopicConnectionKey.setTopic(topicOptional.get());

				PersonTopicConnection personTopicConnection = new PersonTopicConnection();
				personTopicConnection.setId(personTopicConnectionKey);
				personTopicConnection.setMaker("Christian Marino");
				personTopicConnection.setDateTime(LocalDateTime.now());
				personTopicConnectionDAO.save(personTopicConnection);
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
	public List<KeyValueBean> getAllPeople() {
		List<KeyValueBean> allBeans = new ArrayList<>();
		try {
			for (Person person : personDAO.findAllByOrderBySurname()) {
				allBeans.add(mapper.mapPersonKV(person));
			}
			return allBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public FileBean getCV(final Integer personId) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("curriculum vitae");

			Optional<Person> personOptional = personDAO.findById(personId);
			if (personOptional.isPresent()) {
				int rowNum = 0;

				Person person = personOptional.get();

				Row row = sheet.createRow(rowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(person.getSerial().toString());
				cell = row.createCell(1);
				cell.setCellValue(person.getName());
				cell = row.createCell(2);
				cell.setCellValue(person.getSurname());

				for (PersonTopicConnection personTopicConnection : personTopicConnectionDAO
						.findByIdPersonId(personId)) {
					rowNum++;
					row = sheet.createRow(rowNum);
					Topic topic = personTopicConnection.getId().getTopic();

					int cellNum = 0;
					cell = row.createCell(cellNum);
					cell.setCellValue(topic.getName());
					for (SkillMark skill : skillDAO.findSkillsByPersonAndTopic(personId, topic.getId())) {
						cellNum++;
						cell = row.createCell(cellNum);
						cell.setCellValue(skill.getSkillName());
					}
				}
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				workbook.write(byteArrayOutputStream);
				/*
				 * FileOutputStream out = new
				 * FileOutputStream("C:\\Users\\christian.marino\\Documents\\pippo.xlsx");
				 * out.write(byteArrayOutputStream.toByteArray()); out.flush(); out.close();
				 */
				byte[] byteArray = byteArrayOutputStream.toByteArray();

				FileBean fileBean = new FileBean();
				fileBean.setName(person.getName() + person.getSurname() + "CV");
				fileBean.setMetaype("application/vnd.ms-excel");
				fileBean.setSize((long) byteArray.length);
				fileBean.setContent(byteArray);
				return fileBean;

			} else {
				throw new InconsistentDataException();
			}

		} catch (InconsistentDataException ide) {
			log.error(ide.getMessage(), ide);
			throw ide;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public PersonBean getPerson(final Integer personId) {
		try {
			Optional<Person> personOptional = personDAO.findById(personId);
			if (personOptional.isPresent()) {
				return mapper.mapPerson(personOptional.get());
			}
			throw new InconsistentDataException();
		} catch (InconsistentDataException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new DataBaseException();
		}
	}

	@Override
	public List<SkillMarkBean> getPersonSkillMarks(final Integer personId) throws ServiceErrorException {
		List<SkillMarkBean> skillMarkList = new ArrayList<>();
		SkillMarkBean skillMark;
		try {
			for (PersonSkillConnection personSkillConnection : personSkillConnectionDAO.findByIdPersonId(personId)) {
				skillMark = new SkillMarkBean();
				skillMark.setSkillName(personSkillConnection.getId().getSkill().getName());
				skillMark.setMark(personSkillConnection.getMark());
				skillMarkList.add(skillMark);
			}
			return skillMarkList;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public List<PersonBean> searchPeopleByBeans(final SearchPeopleObject searchPeopleObject)
			throws ServiceErrorException {
		List<PersonBean> peopleByBeans = new ArrayList<>();
		try {
			for (Person person : personDAO.searchPeople(searchPeopleObject)) {
				peopleByBeans.add(mapper.mapPerson(person));
			}
			return peopleByBeans;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

}