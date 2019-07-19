/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communitycommons;

import communitycommons.proxies.SanitizerPolicy;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author reinout
 */
public class MiscTest {

	public MiscTest() {
	}

	@BeforeClass
	public static void setUpClass() {

	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of enumFromString method, of class Misc.
	 */
	@Test
	public void testEnumFromString() {
		System.out.println("enumFromString");
		SanitizerPolicy expResult = SanitizerPolicy.BLOCKS;
		Optional<SanitizerPolicy> result = Misc.enumFromString(SanitizerPolicy.class, "BLOCKS");
		assertEquals(expResult, result.get());
	}

	@Test
	public void testEnumFromString_WithNonExistingValue() {
		System.out.println("enumFromString_WithNonExistingValue");
		Optional<SanitizerPolicy> result = Misc.enumFromString(SanitizerPolicy.class, "NOT_A_POLICY");
		Assert.assertFalse(result.isPresent());
	}

	@Test
	public void testEnumFromString_WithNull() {
		System.out.println("enumFromString_WithNull");
		Optional<SanitizerPolicy> result = Misc.enumFromString(SanitizerPolicy.class, null);
		Assert.assertFalse(result.isPresent());
	}

}
