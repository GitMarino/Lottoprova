package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AreaBean implements Serializable {

	private static final long serialVersionUID = 7540913196740748013L;

	private Integer id;

	private String name;

	private PersonBean manager;

}