package provalotto.bean.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;
import provalotto.bean.connection.AreaTopicConnection;
import provalotto.bean.connection.PersonTopicConnection;

@Entity(name = Topic.TABLE_NAME)
public class Topic extends AbstractIdentifiedBean {

	private static final long serialVersionUID = -1764964352925715761L;

	public static final String TABLE_NAME = "topic";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@OneToMany(mappedBy = "topic")
	List<AreaTopicConnection> areaTopicConnections;

	@OneToMany(mappedBy = "topic")
	List<PersonTopicConnection> personTopicConnections;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		return Objects.equals(areaTopicConnections, other.areaTopicConnections)
				&& Objects.equals(dateTime, other.dateTime) && Objects.equals(maker, other.maker)
				&& Objects.equals(name, other.name);
	}

	public List<AreaTopicConnection> getAreaTopicConnections() {
		return areaTopicConnections;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getMaker() {
		return maker;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(areaTopicConnections, dateTime, maker, name);
		return result;
	}

	public void setAreaTopicConnections(final List<AreaTopicConnection> areaTopicConnections) {
		this.areaTopicConnections = areaTopicConnections;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Topic [name=" + name + ", maker=" + maker + ", dateTime=" + dateTime + ", areaTopicConnections="
				+ areaTopicConnections + "]";
	}
}