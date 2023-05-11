package provalotto.bean.connection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.key.PersonSkillConnectionKey;

@Getter
@Setter
@ToString
@Entity
@Table(name = PersonSkillConnection.TABLE_NAME)
public class PersonSkillConnection {

	public static final String TABLE_NAME = "person_skill";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@EmbeddedId
	PersonSkillConnectionKey id;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@Column(nullable = false)
	private int mark;

}