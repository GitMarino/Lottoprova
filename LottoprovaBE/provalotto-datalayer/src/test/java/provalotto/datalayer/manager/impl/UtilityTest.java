package provalotto.datalayer.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class UtilityTest {

	Utility utility;

	@Before
	public void setUp() {
		utility = new Utility();
	}

	@Test
	public void testAdd() {
		assertEquals(101, utility.add(1, 100));
	}

	@Test
	public void testFattoriale() {
		assertEquals(40320, utility.fattoriale(8));
	}

	@Test
	public void testFattorialeNegativo() {
		assertThrows(IllegalArgumentException.class, () -> utility.fattoriale(-1));
	}

	@Test
	public void testFattorialeZero() {
		assertEquals(1, utility.fattoriale(0));
	}

}