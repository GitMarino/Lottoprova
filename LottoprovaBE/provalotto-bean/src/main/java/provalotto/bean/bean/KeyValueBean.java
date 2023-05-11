package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeyValueBean implements Serializable {

	private static final long serialVersionUID = -5797384763938225572L;

	private Integer id;

	private String value;

}