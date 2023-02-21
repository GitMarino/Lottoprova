package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.entity.Skill;
import provalotto.datalayer.manager.SkillManager;
import provalotto.datalayer.ws.SkillWs;

@RestController
@RequestMapping("/skill")
public class SkillController implements SkillWs {

	@Autowired
	private SkillManager skillManager;

	@Override
	@PostMapping
	public Skill createSkill(final @RequestBody Skill skill) {
		return skillManager.createSkill(skill);
	}

	@Override
	@DeleteMapping
	public boolean deleteSkill(final @RequestBody Skill skill) {
		return skillManager.deleteSkill(skill);
	}

	@Override
	@GetMapping("/skills")
	public List<Skill> getSkillsByTopic(final Long topicId) {
		return skillManager.getSkillsByTopic(topicId);
	}

	@Override
	@PutMapping
	public Skill saveSkill(final @RequestBody Skill skill) {
		return skillManager.saveSkill(skill);
	}

}