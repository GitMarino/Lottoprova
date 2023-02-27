package provalotto.bean.bean;

import java.io.Serializable;
import java.util.Objects;

public class PersonTopicConnectionBean implements Serializable {

	private static final long serialVersionUID = -3150708829984918207L;

	private Long personId;

	private Long topicId;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonTopicConnectionBean other = (PersonTopicConnectionBean) obj;
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
		return "PersonTopicConnectionBean [personId=" + personId + ", topicId=" + topicId + "]";
	}
}