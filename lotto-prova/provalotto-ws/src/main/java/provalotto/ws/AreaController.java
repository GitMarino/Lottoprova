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

import provalotto.bean.entity.Area;
import provalotto.datalayer.manager.AreaManager;
import provalotto.datalayer.ws.AreaWs;

@RestController
@RequestMapping("/area")
public class AreaController implements AreaWs {

	@Autowired
	private AreaManager areaManager;

	@Override
	@PostMapping
	public Area createArea(final @RequestBody Area area) {
		return areaManager.createArea(area);
	}

	@Override
	@DeleteMapping
	public boolean deleteArea(final @RequestBody Area area) {
		return areaManager.deleteArea(area);
	}

	@Override
	@GetMapping("/areas")
	public List<Area> getAllAreas() {
		return areaManager.getAllAreas();
	}

	@Override
	@PutMapping
	public Area saveArea(final @RequestBody Area area) {
		return areaManager.saveArea(area);
	}

}