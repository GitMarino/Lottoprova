package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SkillBean implements Serializable {

	private static final long serialVersionUID = -9067051484301110887L;

	private Integer id;

	private String name;

	private String description;

	private TopicBean topic;

}