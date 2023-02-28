package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class SearchPeopleObject implements Serializable {

	private static final long serialVersionUID = -1673741700766730965L;

	private Long areaId;

	private Long skillId;

	private Long topicId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchPeopleObject other = (SearchPeopleObject) obj;
		return Objects.equals(areaId, other.areaId) && Objects.equals(skillId, other.skillId)
				&& Objects.equals(topicId, other.topicId);
	}

	public Long getAreaId() {
		return areaId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public Long getTopicId() {
		return topicId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaId, skillId, topicId);
	}

	public void setAreaId(final Long areaId) {
		this.areaId = areaId;
	}

	public void setSkillId(final Long skillId) {
		this.skillId = skillId;
	}

	public void setTopicId(final Long topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "SearchPeopleByBeans [areaId=" + areaId + ", skillId=" + skillId + ", topicId=" + topicId + "]";
	}

}