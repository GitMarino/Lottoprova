package provalotto.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.PersonAreaConnectionBean;
import provalotto.datalayer.manager.PersonAreaConnectionManager;

@RestController
@RequestMapping("/personAreaConnection")
public class PersonAreaConnectionController {

	@Autowired
	private PersonAreaConnectionManager personAreaConnectionManager;

	@PostMapping
	public ResponseEntity<PersonAreaConnectionBean> createPersonAreaConnection(
			@RequestBody final PersonAreaConnectionBean personAreaConnectionBean) {
		try {
			return ResponseEntity.ok(personAreaConnectionManager.createPersonAreaConnection(personAreaConnectionBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}