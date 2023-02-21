package provalotto.bean.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.joda.time.LocalDateTime;

import provalotto.bean.base.AbstractIdentifiedBean;
import provalotto.bean.connection.AreaTopicConnection;
import provalotto.bean.connection.PersonAreaConnection;

@Entity(name = Area.TABLE_NAME)
public class Area extends AbstractIdentifiedBean {

	private static final long serialVersionUID = 9172925256821355183L;

	public static final String TABLE_NAME = "area";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String areaManager;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@OneToMany(mappedBy = "area")
	List<PersonAreaConnection> personAreaConnections;

	@OneToMany(mappedBy = "area")
	List<AreaTopicConnection> areaTopicConnections;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		return Objects.equals(areaTopicConnections, other.areaTopicConnections)
				&& Objects.equals(dateTime, other.dateTime) && Objects.equals(maker, other.maker)
				&& Objects.equals(name, other.name)
				&& Objects.equals(personAreaConnections, other.personAreaConnections);
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

	public List<PersonAreaConnection> getPersonAreaConnections() {
		return personAreaConnections;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(areaTopicConnections, dateTime, maker, name, personAreaConnections);
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

	public void setPersonAreaConnections(final List<PersonAreaConnection> personAreaConnections) {
		this.personAreaConnections = personAreaConnections;
	}

	@Override
	public String toString() {
		return "Area [name=" + name + ", maker=" + maker + ", dateTime=" + dateTime + ", personAreaConnections="
				+ personAreaConnections + ", areaTopicConnections=" + areaTopicConnections + "]";
	}

}