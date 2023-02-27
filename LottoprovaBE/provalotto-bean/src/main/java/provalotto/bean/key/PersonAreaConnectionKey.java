package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;

@Embeddable
public class PersonAreaConnectionKey implements Serializable {

	private static final long serialVersionUID = -6297954423935509232L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	Area area;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonAreaConnectionKey other = (PersonAreaConnectionKey) obj;
		return Objects.equals(area, other.area) && Objects.equals(person, other.person);
	}

	public Area getArea() {
		return area;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, person);
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PersonAreaConnectionKey [person=" + person + ", area=" + area + "]";
	}

}