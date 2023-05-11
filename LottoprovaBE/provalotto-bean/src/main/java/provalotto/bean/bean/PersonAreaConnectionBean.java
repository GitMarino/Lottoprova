package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonAreaConnectionBean implements Serializable {

	private static final long serialVersionUID = -2301617728641316996L;

	private Integer personId;

	private Integer areaId;

}