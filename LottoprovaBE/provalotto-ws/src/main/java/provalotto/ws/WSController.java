package provalotto.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws")
public class WSController {

	@GetMapping(value = "/isBackendAlive")
	public boolean isBackendAlive() {
		return true;
	}

}