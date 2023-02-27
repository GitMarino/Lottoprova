package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import provalotto.bean.entity.Area;
import provalotto.bean.entity.Topic;

@Embeddable
public class AreaTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = 357855369493136L;

	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	Area area;

	@ManyToOne
	@JoinColumn(name = "topic_id", nullable = false)
	Topic topic;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaTopicConnectionKey other = (AreaTopicConnectionKey) obj;
		return Objects.equals(area, other.area) && Objects.equals(topic, other.topic);
	}

	public Area getArea() {
		return area;
	}

	public Topic getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, topic);
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "AreaTopicConnectionKey [area=" + area + ", topic=" + topic + "]";
	}

}