package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonBean implements Serializable {

	private static final long serialVersionUID = -8695453515821545797L;

	private Integer id;

	private Integer serial;

	private String name;

	private String surname;

}