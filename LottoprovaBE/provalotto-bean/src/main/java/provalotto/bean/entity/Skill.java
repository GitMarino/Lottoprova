package provalotto.bean.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;

@Entity(name = Skill.TABLE_NAME)
public class Skill extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 8042006035903253044L;

	public static final String TABLE_NAME = "skill";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@ManyToOne
	private Topic skillTopic;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(description, other.description)
				&& Objects.equals(maker, other.maker) && Objects.equals(name, other.name)
				&& Objects.equals(skillTopic, other.skillTopic);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getDescription() {
		return description;
	}

	public String getMaker() {
		return maker;
	}

	public String getName() {
		return name;
	}

	public Topic getSkillTopic() {
		return skillTopic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateTime, description, maker, name, skillTopic);
		return result;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSkillTopic(final Topic skillTopic) {
		this.skillTopic = skillTopic;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", description=" + description + ", maker=" + maker + ", dateTime=" + dateTime
				+ ", skillTopic=" + skillTopic + "]";
	}

}