package provalotto.datalayer.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.bean.AreaBean;
import provalotto.bean.entity.Area;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.manager.AreaManager;

@Component
public class AreaManagerImpl implements AreaManager {

	public static final Logger log = LoggerFactory.getLogger(AreaManagerImpl.class);

	@Autowired
	private AreaDAO areaDAO;

	@Override
	public AreaBean createArea(final AreaBean areaBean) throws ServiceErrorException {
		try {
			Area area = new Area();
			area.setName(areaBean.getName());
			area.setAreaManager(areaBean.getAreaManager());
			area.setMaker("Christian Marino");
			area.setDateTime(LocalDateTime.now());
			areaDAO.save(area);
			return areaBean;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceErrorException(e);
		}
	}

	@Override
	public boolean deleteArea(final AreaBean areaBean) {
		try {
			Area area = areaDAO.findById(areaBean.getId()).get();
			if (area != null) {
				areaDAO.delete(area);
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public List<AreaBean> getAllAreas() {
		List<AreaBean> allAreaBeans = new ArrayList<>();
		AreaBean areaBean;
		for (Area area : areaDAO.findAllByOrderByName()) {
			areaBean = new AreaBean();
			areaBean.setId(area.getId());
			areaBean.setName(area.getName());
			areaBean.setAreaManager(area.getAreaManager());
			allAreaBeans.add(areaBean);
		}
		return allAreaBeans;
	}

}