package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SearchPeopleObject;
import provalotto.datalayer.manager.PersonManager;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonManager personManager;

	@PostMapping
	public ResponseEntity<PersonBean> createPerson(final @RequestBody PersonBean personBean) {
		try {
			return ResponseEntity.ok(personManager.createPerson(personBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/area")
	public ResponseEntity<Integer> createPersonAreaConnection(@PathVariable("id") final Long personId,
			final Long areaId) {
		try {
			personManager.createPersonAreaConnection(personId, areaId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/skill")
	public ResponseEntity<Integer> createPersonSkillConnection(@PathVariable("id") final Long personId,
			final Long skillId, final Integer mark) {
		try {
			personManager.createPersonSkillConnection(personId, skillId, mark);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/topic")
	public ResponseEntity<Integer> createPersonTopicConnection(@PathVariable("id") final Long personId,
			final Long topicId) {
		try {
			personManager.createPersonTopicConnection(personId, topicId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping
	public boolean deletePerson(final Long personBeanId) {
		return personManager.deletePerson(personBeanId);
	}

	@GetMapping
	public List<BeanKeyValue> getAllPeople() {
		return personManager.getAllPeople();
	}

	@GetMapping("/search")
	public List<PersonBean> getPeopleByBeans(final @RequestParam(required = false) Long areaId,
			final @RequestParam(required = false) Long skillId, final @RequestParam(required = false) Long topicId) {
		SearchPeopleObject searchPeopleObject = new SearchPeopleObject();
		searchPeopleObject.setAreaId(areaId);
		searchPeopleObject.setSkillId(skillId);
		searchPeopleObject.setTopicId(topicId);
		return personManager.getPeopleByBeans(searchPeopleObject);
	}

}