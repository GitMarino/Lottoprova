package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.KeyValueBean;
import provalotto.bean.bean.TopicBean;
import provalotto.bean.bean.TopicSkillsBean;
import provalotto.datalayer.manager.TopicManager;
import provalotto.ws.response.Answer;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private TopicManager topicManager;

	@PostMapping
	public ResponseEntity<TopicBean> createTopic(final @RequestBody TopicBean topicBean) {
		try {
			return ResponseEntity.ok(topicManager.createTopic(topicBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public Answer<List<KeyValueBean>> getAllTopics() {
		return Answer.ok(topicManager.getAllTopics(), HttpStatus.OK);
	}

	@GetMapping("/person")
	public ResponseEntity<List<KeyValueBean>> getTopicsByPerson(final Long personId) {
		try {
			return ResponseEntity.ok(topicManager.getTopicsByPerson(personId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/skills")
	public Answer<List<TopicSkillsBean>> getTopicsSkillsByPerson(final Long personId) {
		return Answer.ok(topicManager.getTopicsSkillsByPerson(personId), HttpStatus.OK);
	}

}