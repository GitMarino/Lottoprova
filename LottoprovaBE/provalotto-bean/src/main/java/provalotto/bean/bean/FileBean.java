package provalotto.bean.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileBean implements Serializable {

	private static final long serialVersionUID = -1500462942600431614L;

	private String name;

	private String metaype;

	private Long size;

	private byte[] content;

}