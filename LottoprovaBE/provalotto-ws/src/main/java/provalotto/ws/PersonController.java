package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.FileBean;
import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.PersonBean;
import provalotto.bean.bean.SkillMarkBean;
import provalotto.bean.utility.SearchPeopleObject;
import provalotto.datalayer.manager.PersonManager;
import provalotto.ws.response.Answer;

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
	public ResponseEntity<Integer> createPersonAreaConnection(@PathVariable("id") final Integer personId,
			final Integer areaId) {
		try {
			personManager.createPersonAreaConnection(personId, areaId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/skill")
	public ResponseEntity<Integer> createPersonSkillConnection(@PathVariable("id") final Integer personId,
			final Integer skillId, final Integer mark) {
		try {
			personManager.createPersonSkillConnection(personId, skillId, mark);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/topic")
	public ResponseEntity<Integer> createPersonTopicConnection(@PathVariable("id") final Integer personId,
			final Integer topicId) {
		try {
			personManager.createPersonTopicConnection(personId, topicId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public Answer<List<KeyValueBean>> getAllPeople() {
		return Answer.ok(personManager.getAllPeople(), HttpStatus.OK);
	}

	@GetMapping("/cv/{id}")
	public Answer<FileBean> getCV(@PathVariable("id") final Integer personId) {
		return Answer.ok(personManager.getCV(personId), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Answer<PersonBean> getPerson(@PathVariable("id") final Integer personId) {
		return Answer.ok(personManager.getPerson(personId), HttpStatus.OK);
		// return ResponseEntity.ok(personManager.getPerson(personId));
		// return new ResponseEntity<>(personManager.getPerson(personId),
		// HttpStatus.OK);
	}

	@GetMapping("/{id}/skill")
	public ResponseEntity<List<SkillMarkBean>> getPersonSkillMarks(@PathVariable("id") final Integer personId) {
		try {
			return ResponseEntity.ok(personManager.getPersonSkillMarks(personId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<PersonBean>> searchPeopleByBeans(final @RequestParam(required = false) Integer areaId,
			final @RequestParam(required = false) Integer skillId,
			final @RequestParam(required = false) Integer topicId) {
		SearchPeopleObject searchPeopleObject = new SearchPeopleObject();
		searchPeopleObject.setAreaId(areaId);
		searchPeopleObject.setSkillId(skillId);
		searchPeopleObject.setTopicId(topicId);
		try {
			return ResponseEntity.ok(personManager.searchPeopleByBeans(searchPeopleObject));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}