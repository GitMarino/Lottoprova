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

import provalotto.bean.entity.Person;
import provalotto.datalayer.manager.PersonManager;
import provalotto.datalayer.ws.PersonWs;

@RestController
@RequestMapping("/person")
public class PersonController implements PersonWs {

	@Autowired
	private PersonManager personManager;

	@Override
	@PostMapping
	public Person createPerson(final @RequestBody Person person) {
		return personManager.createPerson(person);
	}

	@Override
	@DeleteMapping
	public boolean deletePerson(final @RequestBody Person person) {
		return personManager.deletePerson(person);
	}

	@Override
	@GetMapping("/people")
	public List<Person> getAllPeople() {
		return personManager.getAllPeople();
	}

	@Override
	@PutMapping
	public Person savePerson(final @RequestBody Person person) {
		return personManager.savePerson(person);
	}

}