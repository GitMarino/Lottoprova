package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonSkillConnectionBean implements Serializable {

	private static final long serialVersionUID = -848697128745921798L;

	private Integer personId;

	private Integer skillId;

	private Integer mark;

}