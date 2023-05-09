package provalotto.datalayer.manager.impl;

public class Utility {

	public int add(final int i, final int j) {
		return i + j;
	}

	public int fattoriale(final int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 0) {
			return 1;
		}
		int result = 1;
		for (int i = n; i > 0; i--) {
			result *= i;
		}
		return result;
	}

}