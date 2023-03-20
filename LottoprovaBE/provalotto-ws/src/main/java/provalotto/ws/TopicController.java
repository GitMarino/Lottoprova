package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.BeanKeyValue;
import provalotto.bean.bean.TopicBean;
import provalotto.datalayer.manager.TopicManager;

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

	@DeleteMapping
	public boolean deleteTopic(final Long topicBeanId) {
		return topicManager.deleteTopic(topicBeanId);
	}

	@GetMapping
	public ResponseEntity<List<BeanKeyValue>> getAllTopics() {
		try {
			return ResponseEntity.ok(topicManager.getAllTopics());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/person")
	public ResponseEntity<List<BeanKeyValue>> getTopicsByPerson(final Long personId) {
		try {
			return ResponseEntity.ok(topicManager.getTopicsByPerson(personId));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}