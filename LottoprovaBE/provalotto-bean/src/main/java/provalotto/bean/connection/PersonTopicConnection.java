package provalotto.bean.connection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.key.PersonTopicConnectionKey;

@Getter
@Setter
@ToString
@Entity
@Table(name = PersonTopicConnection.TABLE_NAME)
public class PersonTopicConnection {

	public static final String TABLE_NAME = "person_topic";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@EmbeddedId
	PersonTopicConnectionKey id;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

}