package provalotto.bean.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;

@Entity(name = Area.TABLE_NAME)
public class Area extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 9172925256821355183L;

	public static final String TABLE_NAME = "area";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@ManyToOne
	private Person manager;

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
		Area other = (Area) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(maker, other.maker)
				&& Objects.equals(manager, other.manager) && Objects.equals(name, other.name);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getMaker() {
		return maker;
	}

	public Person getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateTime, maker, manager, name);
		return result;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setManager(final Person manager) {
		this.manager = manager;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Area [name=" + name + ", manager=" + manager + ", maker=" + maker + ", dateTime=" + dateTime + "]";
	}

}