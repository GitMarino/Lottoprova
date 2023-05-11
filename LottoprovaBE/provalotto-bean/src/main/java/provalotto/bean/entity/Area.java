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
@Table(name = Area.TABLE_NAME)
public class Area extends AbstractIdentifiedBean {

	public static final String TABLE_NAME = "area";

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@ManyToOne
	private Person manager;

	@Column(nullable = false, length = 50)
	private String maker;

	@Column(nullable = false)
	private LocalDateTime dateTime;
}