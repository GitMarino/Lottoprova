package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.manager.AreaManager;
import provalotto.ws.response.Answer;

@RestController
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaManager areaManager;

	@PostMapping
	public ResponseEntity<Integer> createArea(final String name, final Integer personBeanId,
			final Integer topicBeanId) {
		try {
			areaManager.createArea(name, personBeanId, topicBeanId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}/topic")
	public ResponseEntity<Integer> createAreaTopicConnection(@PathVariable("id") final Integer areaId,
			final Integer topicId) {
		try {
			areaManager.createAreaTopicConnection(areaId, topicId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public Answer<List<KeyValueBean>> getAllAreas() {
		return Answer.ok(areaManager.getAllAreas(), HttpStatus.OK);
	}

	@GetMapping("/person")
	public ResponseEntity<List<KeyValueBean>> getAreasByPerson(final Integer personId) {
		try {
			return ResponseEntity.ok(areaManager.getAreasByPerson(personId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/topic/skill")
	public Answer<List<List<KeyValueBean>>> getAreaTopicSkillMap() {
		return Answer.ok(areaManager.getAreaTopicSkillMap(), HttpStatus.OK);
	}

}