package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class PersonBean implements Serializable {

	private static final long serialVersionUID = -8695453515821545797L;

	private Long id;

	private String username;

	private String name;

	private String surname;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonBean other = (PersonBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname) && Objects.equals(username, other.username);
	}

	public Long getId() {
		return id;
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
		return Objects.hash(id, name, surname, username);
	}

	public void setId(final Long id) {
		this.id = id;
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
		return "PersonBean [id=" + id + ", username=" + username + ", name=" + name + ", surname=" + surname + "]";
	}

}
