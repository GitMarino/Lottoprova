package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

import provalotto.bean.key.PersonAreaConnectionKey;

@Entity
public class PersonAreaConnection {

	@EmbeddedId
	PersonAreaConnectionKey id;

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
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(id, other.id)
				&& Objects.equals(maker, other.maker);
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

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, id, maker);
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

	@Override
	public String toString() {
		return "PersonAreaConnection [id=" + id + ", maker=" + maker + ", dateTime=" + dateTime + "]";
	}

}
