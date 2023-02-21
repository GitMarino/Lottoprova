package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.entity.Topic;

public interface TopicManager {

	Topic createTopic(Topic topic);

	boolean deleteTopic(Topic topic);

	List<Topic> getAllTopics();

	Topic saveTopic(Topic topic);

}