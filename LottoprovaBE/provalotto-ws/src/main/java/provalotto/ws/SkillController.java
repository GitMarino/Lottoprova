package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.KeyValueBean;
import provalotto.datalayer.manager.SkillManager;
import provalotto.ws.response.Answer;

@RestController
@RequestMapping("/skill")
public class SkillController {

	@Autowired
	private SkillManager skillManager;

	@PostMapping
	public ResponseEntity<Integer> createSkill(final String name, final String description, final Integer topicBeanId) {
		try {
			skillManager.createSkill(name, description, topicBeanId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public Answer<List<KeyValueBean>> getAllSkills() {
		return Answer.ok(skillManager.getAllSkills(), HttpStatus.OK);
	}

	@GetMapping("/topic")
	public ResponseEntity<List<KeyValueBean>> getSkillsByTopic(final Integer topicId) {
		try {
			return ResponseEntity.ok(skillManager.getSkillsByTopic(topicId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}