package provalotto.bean.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import provalotto.bean.entity.Person;
import provalotto.bean.entity.Skill;

@Getter
@Setter
@ToString
@Embeddable
public class PersonSkillConnectionKey implements Serializable {

	private static final long serialVersionUID = 1172717306705581241L;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	Person person;

	@ManyToOne
	@JoinColumn(name = "skill_id", nullable = false)
	Skill skill;

}