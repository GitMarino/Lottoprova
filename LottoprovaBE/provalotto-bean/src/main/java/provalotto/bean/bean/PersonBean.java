package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class PersonBean implements Serializable {

	private static final long serialVersionUID = -8695453515821545797L;

	private Long id;

	private Long serial;

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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(serial, other.serial)
				&& Objects.equals(surname, other.surname);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getSerial() {
		return serial;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, serial, surname);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSerial(final Long serial) {
		this.serial = serial;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "PersonBean [id=" + id + ", serial=" + serial + ", name=" + name + ", surname=" + surname + "]";
	}

}