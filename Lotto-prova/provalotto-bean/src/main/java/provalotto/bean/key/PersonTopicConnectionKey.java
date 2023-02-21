package provalotto.bean.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = 3955910219976678208L;

	@Column(name = "person_id")
	Long personId;

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
		PersonTopicConnectionKey other = (PersonTopicConnectionKey) obj;
		return Objects.equals(personId, other.personId) && Objects.equals(topicId, other.topicId);
	}

	public Long getPersonId() {
		return personId;
	}

	public Long getTopicId() {
		return topicId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, topicId);
	}

	public void setPersonId(final Long personId) {
		this.personId = personId;
	}

	public void setTopicId(final Long topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "PersonTopicConnectionKey [personId=" + personId + ", topicId=" + topicId + "]";
	}

}