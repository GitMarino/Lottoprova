package provalotto.bean.utility;

import java.util.Objects;

public class SkillMark {

	private String skillName;

	private int mark;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillMark other = (SkillMark) obj;
		return mark == other.mark && Objects.equals(skillName, other.skillName);
	}

	public int getMark() {
		return mark;
	}

	public String getSkillName() {
		return skillName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mark, skillName);
	}

	public void setMark(final int mark) {
		this.mark = mark;
	}

	public void setSkillName(final String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "SkillMark [skillName=" + skillName + ", mark=" + mark + "]";
	}

}