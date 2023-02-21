package provalotto.bean.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;
import provalotto.bean.connection.PersonAreaConnection;
import provalotto.bean.connection.PersonSkillConnection;
import provalotto.bean.connection.PersonTopicConnection;

@Entity(name = Person.TABLE_NAME)
public class Person extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 3036860835681576261L;

	public static final String TABLE_NAME = "person";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String surname;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@OneToMany(mappedBy = "person")
	List<PersonAreaConnection> personAreaConnections;

	@OneToMany(mappedBy = "person")
	List<PersonTopicConnection> personTopicConnections;

	@OneToMany(mappedBy = "person")
	List<PersonSkillConnection> personSkillConnections;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(maker, other.maker)
				&& Objects.equals(name, other.name)
				&& Objects.equals(personAreaConnections, other.personAreaConnections)
				&& Objects.equals(surname, other.surname);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getMaker() {
		return maker;
	}

	public String getName() {
		return name;
	}

	public List<PersonAreaConnection> getPersonAreaConnections() {
		return personAreaConnections;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateTime, maker, name, personAreaConnections, surname);
		return result;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPersonAreaConnections(final List<PersonAreaConnection> personAreaConnections) {
		this.personAreaConnections = personAreaConnections;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", maker=" + maker + ", dateTime=" + dateTime
				+ ", personAreaConnections=" + personAreaConnections + "]";
	}
}
