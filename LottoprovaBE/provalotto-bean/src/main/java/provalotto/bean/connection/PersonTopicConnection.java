package provalotto.bean.connection;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.joda.time.LocalDateTime;

import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;
import provalotto.bean.key.PersonTopicConnectionKey;

@Entity
public class PersonTopicConnection {

	@EmbeddedId
	PersonTopicConnectionKey id;

	@ManyToOne
	@MapsId("personId")
	@JoinColumn(name = "person_id")
	Person person;

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
		PersonTopicConnection other = (PersonTopicConnection) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(id, other.id)
				&& Objects.equals(maker, other.maker) && Objects.equals(person, other.person)
				&& Objects.equals(topic, other.topic);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public PersonTopicConnectionKey getId() {
		return id;
	}

	public String getMaker() {
		return maker;
	}

	public Person getPerson() {
		return person;
	}

	public Topic getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, id, maker, person, topic);
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setId(final PersonTopicConnectionKey id) {
		this.id = id;
	}

	public void setMaker(final String maker) {
		this.maker = maker;
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "PersonTopicConnection [id=" + id + ", person=" + person + ", topic=" + topic + ", maker=" + maker
				+ ", dateTime=" + dateTime + "]";
	}
}