package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.entity.Area;

public interface AreaManager {

	Area createArea(Area area);

	boolean deleteArea(Area area);

	List<Area> getAllAreas();

	Area saveArea(Area area);

}