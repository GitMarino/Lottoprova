package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TopicBean implements Serializable {

	private static final long serialVersionUID = 2686995141118232899L;

	private Integer id;

	private String name;

}