package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class SearchByAreaAndTopicBean implements Serializable {

	private static final long serialVersionUID = -1673741700766730965L;

	private Long areaId;

	private Long topicId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchByAreaAndTopicBean other = (SearchByAreaAndTopicBean) obj;
		return Objects.equals(areaId, other.areaId) && Objects.equals(topicId, other.topicId);
	}

	public Long getAreaId() {
		return areaId;
	}

	public Long getTopicId() {
		return topicId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaId, topicId);
	}

	public void setAreaId(final Long areaId) {
		this.areaId = areaId;
	}

	public void setTopicId(final Long topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "SearchByAreaAndTopicBean [areaId=" + areaId + ", topicId=" + topicId + "]";
	}

}