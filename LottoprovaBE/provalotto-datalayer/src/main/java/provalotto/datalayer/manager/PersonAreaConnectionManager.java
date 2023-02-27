package provalotto.datalayer.manager;

import provalotto.bean.bean.PersonAreaConnectionBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface PersonAreaConnectionManager {

	PersonAreaConnectionBean createPersonAreaConnection(PersonAreaConnectionBean personAreaConnectionBean)
			throws ServiceErrorException;
}