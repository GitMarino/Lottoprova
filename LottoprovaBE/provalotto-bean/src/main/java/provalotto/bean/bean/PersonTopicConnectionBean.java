package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonTopicConnectionBean implements Serializable {

	private static final long serialVersionUID = -3150708829984918207L;

	private Integer personId;

	private Integer topicId;

}