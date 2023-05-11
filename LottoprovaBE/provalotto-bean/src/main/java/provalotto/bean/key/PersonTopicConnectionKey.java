package provalotto.bean.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Topic;

@Getter
@Setter
@ToString
@Embeddable
public class PersonTopicConnectionKey implements Serializable {

	private static final long serialVersionUID = -5929262021433086964L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

	@ManyToOne
	@JoinColumn(name = "topic_id", nullable = false)
	Topic topic;

}