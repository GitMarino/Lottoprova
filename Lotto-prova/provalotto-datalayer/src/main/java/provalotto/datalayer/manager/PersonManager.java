package provalotto.datalayer.manager;

import java.util.List;

import provalotto.bean.entity.Person;

public interface PersonManager {

	Person createPerson(Person person);

	boolean deletePerson(Person person);

	List<Person> getAllPeople();

	Person savePerson(Person person);

}