package provalotto.bean.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;

@Entity(name = Topic.TABLE_NAME)
public class Topic extends AbstractIdentifiedBean {

	private static final long serialVersionUID = -1764964352925715761L;

	public static final String TABLE_NAME = "topic";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(maker, other.maker)
				&& Objects.equals(name, other.name);
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
		result = prime * result + Objects.hash(dateTime, maker, name);
		return result;
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
		return "Topic [name=" + name + ", maker=" + maker + ", dateTime=" + dateTime + "]";
	}

}