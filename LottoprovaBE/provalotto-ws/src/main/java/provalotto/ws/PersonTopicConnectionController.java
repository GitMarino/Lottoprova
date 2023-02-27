package provalotto.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import provalotto.bean.bean.PersonTopicConnectionBean;
import provalotto.datalayer.manager.PersonTopicConnectionManager;

@RestController
@RequestMapping("/personTopicConnection")
public class PersonTopicConnectionController {
	
	@Autowired
	private PersonTopicConnectionManager personTopicConnectionManager;

	@PostMapping
	public ResponseEntity<PersonTopicConnectionBean> createPersonTopicConnection(
			@RequestBody final PersonTopicConnectionBean personTopicConnectionBean) {
		try {
			return ResponseEntity.ok(personTopicConnectionManager.createPersonTopicConnection(personTopicConnectionBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}