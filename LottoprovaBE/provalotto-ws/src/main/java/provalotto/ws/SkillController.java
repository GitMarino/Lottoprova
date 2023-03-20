package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.datalayer.manager.SkillManager;

@RestController
@RequestMapping("/skill")
public class SkillController {

	@Autowired
	private SkillManager skillManager;

	@PostMapping
	public ResponseEntity<Integer> createSkill(final String name, final String description, final Long topicBeanId) {
		try {
			skillManager.createSkill(name, description, topicBeanId);
			return ResponseEntity.ok(1);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping
	public boolean deleteSkill(final Long skillBeanId) {
		return skillManager.deleteSkill(skillBeanId);
	}

	@GetMapping
	public ResponseEntity<List<BeanKeyValue>> getAllSkills() {
		try {
			return ResponseEntity.ok(skillManager.getAllSkills());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/topic")
	public ResponseEntity<List<BeanKeyValue>> getSkillsByTopic(final Long topicId) {
		try {
			return ResponseEntity.ok(skillManager.getSkillsByTopic(topicId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}