package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AreaTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = 357855369493136L;

	@Column(name = "area_id")
	Long areaId;

	@Column(name = "topic_id")
	Long topicId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaTopicConnectionKey other = (AreaTopicConnectionKey) obj;
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
		return "AreaTopicConnectionKey [areaId=" + areaId + ", topicId=" + topicId + "]";
	}
}
