package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonSkillConnectionKey implements Serializable {

	private static final long serialVersionUID = -8420327609367513737L;

	@Column(name = "person_id")
	Long personId;

	@Column(name = "skill_id")
	Long skillId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonSkillConnectionKey other = (PersonSkillConnectionKey) obj;
		return Objects.equals(personId, other.personId) && Objects.equals(skillId, other.skillId);
	}

	public Long getPersonId() {
		return personId;
	}

	public Long getSkillId() {
		return skillId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, skillId);
	}

	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	public void setSkillId(final Long skillId) {
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "PersonSkillConnectionKey [personId=" + personId + ", skillId=" + skillId + "]";
	}

}