package provalotto.bean.connection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.key.PersonAreaConnectionKey;

@Getter
@Setter
@ToString
@Entity
@Table(name = PersonAreaConnection.TABLE_NAME)
public class PersonAreaConnection {

	public static final String TABLE_NAME = "person_area";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@EmbeddedId
	PersonAreaConnectionKey id;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

}