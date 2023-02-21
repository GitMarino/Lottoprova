package provalotto.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.datalayer.ws.WebService;

@RestController
@RequestMapping("/ws")
public class WSController implements WebService {

	@Override
	@GetMapping(value = "/isBackendAlive")
	public boolean isBackendAlive() {
		return true;
	}

}