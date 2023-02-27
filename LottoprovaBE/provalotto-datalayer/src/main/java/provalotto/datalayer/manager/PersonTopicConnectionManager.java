package provalotto.datalayer.manager;

import provalotto.bean.bean.PersonTopicConnectionBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface PersonTopicConnectionManager {

	PersonTopicConnectionBean createPersonTopicConnection(PersonTopicConnectionBean personTopicConnectionBean)
			throws ServiceErrorException;
}