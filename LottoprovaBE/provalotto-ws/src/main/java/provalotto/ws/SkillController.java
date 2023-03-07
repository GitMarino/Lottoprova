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
	public ResponseEntity<Integer> createSkill(final String name, final String description,
			final Long skillTopicBeanId) {
		try {
			skillManager.createSkill(name, description, skillTopicBeanId);
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
	public List<BeanKeyValue> getAllSkills() {
		return skillManager.getAllSkills();
	}

}