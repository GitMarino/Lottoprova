package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.joda.time.LocalDateTime;

import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;
import provalotto.bean.key.PersonAreaConnectionKey;

@Entity
public class PersonAreaConnection {

	@EmbeddedId
	PersonAreaConnectionKey id;

	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name = "person_id")
	Person person;

	@ManyToOne
	@MapsId("areaId")
	@JoinColumn(name = "area_id")
	Area area;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonAreaConnection other = (PersonAreaConnection) obj;
		return Objects.equals(area, other.area) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(id, other.id) && Objects.equals(maker, other.maker)
				&& Objects.equals(person, other.person);
	}

	public Area getArea() {
		return area;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public PersonAreaConnectionKey getId() {
		return id;
	}

	public String getMaker() {
		return maker;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, dateTime, id, maker, person);
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setId(final PersonAreaConnectionKey id) {
		this.id = id;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "PersonAreaConnection [id=" + id + ", person=" + person + ", area=" + area + ", maker=" + maker
				+ ", dateTime=" + dateTime + "]";
	}

}
