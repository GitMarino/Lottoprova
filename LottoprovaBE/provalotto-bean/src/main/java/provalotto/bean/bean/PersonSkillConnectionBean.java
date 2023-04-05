package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class PersonSkillConnectionBean implements Serializable {

	private static final long serialVersionUID = -848697128745921798L;

	private Long personId;

	private Long skillId;

	private Integer mark;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonSkillConnectionBean other = (PersonSkillConnectionBean) obj;
		return Objects.equals(mark, other.mark) && Objects.equals(personId, other.personId)
				&& Objects.equals(skillId, other.skillId);
	}

	public Integer getMark() {
		return mark;
	}

	public Long getPersonId() {
		return personId;
	}

	public Long getSkillId() {
		return skillId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mark, personId, skillId);
	}

	public void setMark(final Integer mark) {
		this.mark = mark;
	}

	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	public void setSkillId(final Long skillId) {
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "PersonSkillConnectionBean [personId=" + personId + ", skillId=" + skillId + ", mark=" + mark + "]";
	}

}