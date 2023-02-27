package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

import provalotto.bean.key.PersonSkillConnectionKey;

@Entity
public class PersonSkillConnection {

	@EmbeddedId
	PersonSkillConnectionKey id;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Column(nullable = false)
	private int mark;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonSkillConnection other = (PersonSkillConnection) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(id, other.id)
				&& Objects.equals(maker, other.maker) && mark == other.mark;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public PersonSkillConnectionKey getId() {
		return id;
	}

	public String getMaker() {
		return maker;
	}

	public int getMark() {
		return mark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, id, maker, mark);
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setId(final PersonSkillConnectionKey id) {
		this.id = id;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setMark(final int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "PersonSkillConnection [id=" + id + ", maker=" + maker + ", dateTime=" + dateTime + ", mark=" + mark
				+ "]";
	}

}