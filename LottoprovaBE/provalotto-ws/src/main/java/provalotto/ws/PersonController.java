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

import provalotto.bean.bean.PersonBean;
import provalotto.datalayer.manager.PersonManager;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonManager personManager;

	@PostMapping
	public ResponseEntity<PersonBean> createPerson(final @RequestBody PersonBean personBean) {
		try {
			return ResponseEntity.ok(personManager.createPerson(personBean));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping
	public boolean deletePerson(final @RequestBody PersonBean personBean) {
		return personManager.deletePerson(personBean);
	}

	@GetMapping
	public List<PersonBean> getAllPeople() {
		return personManager.getAllPeople();
	}

}