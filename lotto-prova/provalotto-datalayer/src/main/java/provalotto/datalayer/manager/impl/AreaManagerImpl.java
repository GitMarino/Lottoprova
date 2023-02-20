package provalotto.datalayer.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import provalotto.bean.entity.Area;
import provalotto.datalayer.dao.AreaDAO;
import provalotto.datalayer.manager.AreaManager;

@Component
public class AreaManagerImpl implements AreaManager {

	public static final Logger log = LoggerFactory.getLogger(AreaManagerImpl.class);

	@Autowired
	private AreaDAO areaDAO;

	@Override
	public Area createArea(final Area area) {
		try {
			if (!areaDAO.existsById(area.getId())) {
				return areaDAO.save(area);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean deleteArea(final Area area) {
		try {
			if (areaDAO.existsById(area.getId())) {
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
	public List<Area> getAllAreas() {
		return areaDAO.findAllByOrderByName();
	}

	@Override
	public Area saveArea(final Area area) {
		try {
			return areaDAO.save(area);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}