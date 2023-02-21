package provalotto.datalayer.ws;

import java.util.List;

import provalotto.bean.entity.Topic;

public interface TopicWs {

	Topic createTopic(Topic topic);

	boolean deleteTopic(Topic topic);

	List<Topic> getAllTopics();

	Topic saveTopic(Topic topic);

}