package provalotto.datalayer.ws;

import java.util.List;

import provalotto.bean.entity.Person;

public interface PersonWs {

	Person createPerson(Person person);

	boolean deletePerson(Person person);

	List<Person> getAllPeople();

	Person savePerson(Person person);
}