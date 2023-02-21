package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.joda.time.LocalDateTime;

import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;
import provalotto.bean.key.PersonSkillConnectionKey;

@Entity
public class PersonSkillConnection {

	@EmbeddedId
	PersonSkillConnectionKey id;

	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name = "person_id")
	Person person;

	@ManyToOne
	@MapsId("skillId")
	@JoinColumn(name = "skill_id")
	Skill skill;

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
				&& Objects.equals(maker, other.maker) && Objects.equals(person, other.person)
				&& Objects.equals(skill, other.skill);
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

	public Person getPerson() {
		return person;
	}

	public Skill getSkill() {
		return skill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, id, maker, person, skill);
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

	public void setPerson(final Person person) {
		this.person = person;
	}

	public void setSkill(final Skill skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "PersonSkillConnection [id=" + id + ", person=" + person + ", skill=" + skill + ", maker=" + maker
				+ ", dateTime=" + dateTime + "]";
	}

}