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

import provalotto.bean.entity.Topic;
import provalotto.datalayer.manager.TopicManager;
import provalotto.datalayer.ws.TopicWs;

@RestController
@RequestMapping("/topic")
public class TopicController implements TopicWs {

	@Autowired
	private TopicManager topicManager;

	@Override
	@PostMapping
	public Topic createTopic(final @RequestBody Topic topic) {
		return topicManager.createTopic(topic);
	}

	@Override
	@DeleteMapping
	public boolean deleteTopic(final @RequestBody Topic topic) {
		return topicManager.deleteTopic(topic);
	}

	@Override
	@GetMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicManager.getAllTopics();
	}

	@Override
	@PutMapping
	public Topic saveTopic(final @RequestBody Topic topic) {
		return topicManager.saveTopic(topic);
	}
}