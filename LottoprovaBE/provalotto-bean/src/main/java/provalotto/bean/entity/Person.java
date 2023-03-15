package provalotto.bean.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;

@Entity(name = Person.TABLE_NAME)
public class Person extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 3036860835681576261L;

	public static final String TABLE_NAME = "person";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String surname;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

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
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname)
				&& Objects.equals(username, other.username);
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

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateTime, maker, name, surname, username);
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

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Person [username=" + username + ", name=" + name + ", surname=" + surname + ", maker=" + maker
				+ ", dateTime=" + dateTime + "]";
	}

}