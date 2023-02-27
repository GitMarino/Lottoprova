package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;

@Embeddable
public class PersonTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = 3955910219976678208L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

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
		PersonTopicConnectionKey other = (PersonTopicConnectionKey) obj;
		return Objects.equals(person, other.person) && Objects.equals(topic, other.topic);
	}

	public Person getPerson() {
		return person;
	}

	public Topic getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, topic);
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	public void setTopic(final Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "PersonTopicConnectionKey [person=" + person + ", topic=" + topic + "]";
	}

}