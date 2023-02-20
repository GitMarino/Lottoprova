package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.joda.time.LocalDateTime;

import provalotto.bean.entity.Area;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.AreaTopicConnectionKey;

@Entity
public class AreaTopicConnection {

	@EmbeddedId
	AreaTopicConnectionKey id;

	@ManyToOne
	@MapsId("areaId")
	@JoinColumn(name = "area_id")
	Area area;

	@ManyToOne
	@MapsId("topicId")
	@JoinColumn(name = "topic_id")
	Topic topic;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaTopicConnection other = (AreaTopicConnection) obj;
		return Objects.equals(area, other.area) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(id, other.id) && Objects.equals(maker, other.maker)
				&& Objects.equals(topic, other.topic);
	}

	public Area getArea() {
		return area;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public AreaTopicConnectionKey getId() {
		return id;
	}

	public String getMaker() {
		return maker;
	}

	public Topic getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, dateTime, id, maker, topic);
	}

	public void setArea(final Area area) {
		this.area = area;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setId(final AreaTopicConnectionKey id) {
		this.id = id;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "AreaTopicConnection [id=" + id + ", area=" + area + ", topic=" + topic + ", maker=" + maker
				+ ", dateTime=" + dateTime + "]";
	}
}
