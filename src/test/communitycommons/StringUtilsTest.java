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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author res
 */
public class StringUtilsTest {

	private static final int PASSWORD_LENGTH = 12;

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

	@Test
	public void testRandomStrongPassword_WithDigitsOnly() {
		String password = StringUtils.randomStrongPassword(PASSWORD_LENGTH, PASSWORD_LENGTH, 0, PASSWORD_LENGTH, 0);

		assertEquals(password.length(), PASSWORD_LENGTH);
		assertTrue(password.matches("^\\d+$"));
	}

	@Test
	public void testRandomStrongPassword_WithUppercaseAlphaOnly() {
		String password = StringUtils.randomStrongPassword(PASSWORD_LENGTH, PASSWORD_LENGTH, PASSWORD_LENGTH, 0, 0);

		assertEquals(PASSWORD_LENGTH, password.length());
		assertTrue(password.matches("^[A-Z]+$"));
	}

	@Test
	public void testRandomStrongPassword_WithLowercaseAlphaOnly() {
		String password = StringUtils.randomStrongPassword(PASSWORD_LENGTH, PASSWORD_LENGTH, 0, PASSWORD_LENGTH, 0, 0);

		assertEquals(PASSWORD_LENGTH, password.length());
		assertTrue(password.matches("^[a-z]+$"));
	}

	@Test
	public void testRandomStrongPassword_Combined() {
		var passwordLength = 12;
		var password = StringUtils.randomStrongPassword(passwordLength, passwordLength, 2, 2, 2, 2);

		assertEquals(passwordLength, password.length());
		assertTrue(password.chars().filter(c -> Character.isUpperCase(c)).count() >= 2);
		assertTrue(password.chars().filter(c -> Character.isLowerCase(c)).count() >= 2);
		assertTrue(password.chars().filter(c -> Character.isDigit(c)).count() >= 2);
		assertTrue(password.chars().filter(c -> StringUtils.SPECIAL.indexOf(c) >= 0).count() == 2);
	}

	@Test
	public void testRandomStrongPassword_WithNoSpecifiedCharacters() {
		String password = StringUtils.randomStrongPassword(PASSWORD_LENGTH, PASSWORD_LENGTH, 0, 0, 0);

		assertEquals(PASSWORD_LENGTH, password.length());
		assertTrue(password.matches("^[A-Za-z0-9]+$"));
	}

	@Test
	public void testRandomStrongPassword_WithVaryingLength() {
		final int minLength = 10;
		final int maxLength = 11;
		final int runs = 50;

		boolean minimumHit = false;
		boolean maximumHit = false;

		// Since we are dealing with randomness, there is a probability of this test failing.
		// With 50 runs, the chance of this test failing is about 1 in 562.949.953.421.312
		for (int run = 0; run < runs; run++) {
			String password = StringUtils.randomStrongPassword(minLength, maxLength, 0, 0, 0);

			assertTrue(password.length() >= minLength);
			assertTrue(password.length() <= maxLength);

			if (password.length() == minLength) {
				minimumHit = true;
			} else {
				maximumHit = true;
			}
		}

		assertTrue(minimumHit);
		assertTrue(maximumHit);
	}

	@Test
	public void testRandomStrongPassword_WithMinLengthLargerThanMaxLength() {
		final int minLength = 12;
		final int maxLength = 10;
		assertThrows(IllegalArgumentException.class, () -> StringUtils.randomStrongPassword(minLength, maxLength, 0, 0, 0));
	}

	@Test
	public void testRandomStrongPassword_WithNumberOfSpecifiedCharactersGreaterThanLength() {
		final int upperCount = 10;
		final int digitCount = 10;
		final int specialCount = 10;

		// Password length is 1 less than the total number of minimum specified characters.
		final int length = upperCount + digitCount + specialCount - 1;

		assertThrows(IllegalArgumentException.class, () ->
			StringUtils.randomStrongPassword(length, length, upperCount, digitCount, specialCount));
	}

	@Test
	public void testHash() throws DigestException, NoSuchAlgorithmException {
		final int length = 32;
		final String originalString = "original string";
		final String hashedString = "18760223948747fb081582fdef27e3d216d0e6bc67734eb080bb1c0c4b22d01b";

		assertEquals(StringUtils.hash(originalString), StringUtils.hash(originalString, length));
		assertEquals(StringUtils.hash(originalString), hashedString);
	}

	@Test
	public void testStringFromInputStream() throws IOException {
		Charset utf8 = Charset.forName("UTF-8");
		Charset utf16 = Charset.forName("UTF-16");
		Charset utf16be = Charset.forName("UTF-16BE");
		Charset utf16le = Charset.forName("UTF-16LE");

		String text = "hello";

		assertEquals(text, testStringFromInputStream(text, utf8));
		assertEquals(text, testStringFromInputStream(text, utf16));
		assertEquals(text, testStringFromInputStream(text, utf16be));
		assertEquals(text, testStringFromInputStream(text, utf16le));

		// BOM should be removed (UTF-8)
		byte[] UTF8BOM = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		String textUTF8BOM = new String(UTF8BOM) + text;
		assertEquals(text, testStringFromInputStream(textUTF8BOM, utf8));
	}

	private String testStringFromInputStream(String text, Charset charset) throws IOException {
		return StringUtils.stringFromInputStream(stringToInputStream(text, charset), charset);
	}

	private InputStream stringToInputStream(String str, Charset charset) {
		return new ByteArrayInputStream(str.getBytes(charset));
	}
}
