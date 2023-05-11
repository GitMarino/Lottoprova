package provalotto.bean.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.entity.Area;
import provalotto.bean.entity.Person;

@Getter
@Setter
@ToString
@Embeddable
public class PersonAreaConnectionKey implements Serializable {

	private static final long serialVersionUID = 7750066843114913048L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

	@ManyToOne
	@JoinColumn(name = "area_id", nullable = false)
	Area area;

}