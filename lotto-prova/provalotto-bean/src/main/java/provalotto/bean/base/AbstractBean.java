package provalotto.bean.base;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	@Override
	public abstract String toString();
}
