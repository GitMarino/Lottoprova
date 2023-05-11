package provalotto.bean.connection;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.key.AreaTopicConnectionKey;

@Getter
@Setter
@ToString
@Entity
@Table(name = AreaTopicConnection.TABLE_NAME)
public class AreaTopicConnection {

	public static final String TABLE_NAME = "area_topic";

	public static String getTableName() {
		return TABLE_NAME;
	}

	@EmbeddedId
	AreaTopicConnectionKey id;

	@Column(nullable = false)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

}