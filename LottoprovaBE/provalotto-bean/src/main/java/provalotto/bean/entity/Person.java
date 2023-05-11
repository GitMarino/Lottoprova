package provalotto.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.base.AbstractIdentifiedBean;

@Getter
@Setter
@ToString
@Entity
@Table(name = Person.TABLE_NAME)
public class Person extends AbstractIdentifiedBean {

	public static final String TABLE_NAME = "person";

	@Column(nullable = false, length = 50, unique = true)
	private Integer serial;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String surname;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

}