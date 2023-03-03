package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.bean.AreaBean;
import provalotto.datalayer.manager.impl.ServiceErrorException;

public interface AreaManager {

	AreaBean createArea(AreaBean areaBean) throws ServiceErrorException;

	void createAreaTopicConnection(Long areaId, Long topicId) throws ServiceErrorException;

	boolean deleteArea(Long areaBeanId);

	List<AreaBean> getAllAreas();

}