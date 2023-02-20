package provalotto.datalayer.ws;

import java.util.List;

import provalotto.bean.entity.Area;

public interface AreaWs {

	Area createArea(Area area);

	boolean deleteArea(Area area);

	List<Area> getAllAreas();

	Area saveArea(Area area);

}