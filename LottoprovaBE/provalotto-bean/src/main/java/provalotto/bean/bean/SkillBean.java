package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class SkillBean implements Serializable {

	private static final long serialVersionUID = -9067051484301110887L;

	private Long id;

	private String name;

	private String description;

	private TopicBean skillTopic;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillBean other = (SkillBean) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(skillTopic, other.skillTopic);
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TopicBean getSkillTopic() {
		return skillTopic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, skillTopic);
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSkillTopic(final TopicBean skillTopic) {
		this.skillTopic = skillTopic;
	}

	@Override
	public String toString() {
		return "SkillBean [id=" + id + ", name=" + name + ", description=" + description + ", skillTopic=" + skillTopic
				+ "]";
	}

}