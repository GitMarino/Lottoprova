package provalotto.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.AreaBean;
import provalotto.datalayer.manager.AreaManager;

@RestController
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaManager areaManager;

	@PostMapping
	public ResponseEntity<AreaBean> createArea(final @RequestBody AreaBean areaBean) {
		try {
			return ResponseEntity.ok(areaManager.createArea(areaBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping
	public boolean deleteArea(final Long areaBeanId) {
		return areaManager.deleteArea(areaBeanId);
	}

	@GetMapping
	public List<AreaBean> getAllAreas() {
		return areaManager.getAllAreas();
	}

}