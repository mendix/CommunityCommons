/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communitycommons;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 *
 * @author res
 */
public class StringUtilsTest {

	public StringUtilsTest() {
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
	 * Test of HTMLToPlainText method, of class StringUtils.
	 */
	@Test
	public void testHTMLToPlainText() throws Exception {
		System.out.println("HTMLToPlainText");
		String html = "<html>Hello world</html>";
		String expResult = "Hello world";
		String result = StringUtils.HTMLToPlainText(html);
		assertEquals(expResult, result);
	}

	/**
	 * Test of sanitizeHTML method, of class StringUtils.
	 */
	@Test
	public void testSanitizeHTML_String_PolicyFactory() {
		System.out.println("sanitizeHTML");
		String html = "<a onclick=\"alert(1)\">link</a>";
		PolicyFactory policyFactory = Sanitizers.FORMATTING;
		String expResult = "link";
		String result = StringUtils.sanitizeHTML(html, policyFactory);
		assertEquals(expResult, result);	
	}

}
