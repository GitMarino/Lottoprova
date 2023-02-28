package provalotto.bean.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import provalotto.bean.utility.SkillMark;

public class PersonBean implements Serializable {

	private static final long serialVersionUID = -8695453515821545797L;

	private Long id;

	private String username;

	private String name;

	private String surname;

	private List<SkillMark> skillMarkList;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonBean other = (PersonBean) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(skillMarkList, other.skillMarkList) && Objects.equals(surname, other.surname)
				&& Objects.equals(username, other.username);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<SkillMark> getSkillMarkList() {
		return skillMarkList;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, skillMarkList, surname, username);
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSkillMarkList(final List<SkillMark> skillMarkList) {
		this.skillMarkList = skillMarkList;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "PersonBean [id=" + id + ", username=" + username + ", name=" + name + ", surname=" + surname
				+ ", skillMarkList=" + skillMarkList + "]";
	}

}
