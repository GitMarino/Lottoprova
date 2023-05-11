package provalotto.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table(name = Skill.TABLE_NAME)
public class Skill extends AbstractIdentifiedBean {

	public static final String TABLE_NAME = "skill";

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;

	@ManyToOne
	private Topic topic;

}