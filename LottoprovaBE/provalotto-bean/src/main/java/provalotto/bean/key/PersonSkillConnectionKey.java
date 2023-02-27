package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;

@Embeddable
public class PersonSkillConnectionKey implements Serializable {

	private static final long serialVersionUID = -8420327609367513737L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

	@ManyToOne
	@JoinColumn(name = "skill_id", nullable = false)
	Skill skill;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonSkillConnectionKey other = (PersonSkillConnectionKey) obj;
		return Objects.equals(person, other.person) && Objects.equals(skill, other.skill);
	}

	public Person getPerson() {
		return person;
	}

	public Skill getSkill() {
		return skill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, skill);
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	public void setSkill(final Skill skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "PersonSkillConnectionKey [person=" + person + ", skill=" + skill + "]";
	}

}