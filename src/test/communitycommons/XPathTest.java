package communitycommons;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mendix.core.CoreException;
import com.mendix.core.objectmanagement.member.MendixObjectReference;
import com.mendix.core.objectmanagement.member.MendixObjectReferenceSet;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;

public class XPathTest {

	@Test
	public void valueToXPathValue() throws Exception {
		assertEquals(XPath.NULL, XPath.valueToXPathValue(null));
		assertEquals("42", XPath.valueToXPathValue(42));
		assertEquals("42.42", XPath.valueToXPathValue(42.42));
		assertEquals("'string'", XPath.valueToXPathValue("string"));
		assertEquals("true()", XPath.valueToXPathValue(true));
		assertEquals("false()", XPath.valueToXPathValue(false));

		var d = new Date();
		assertEquals("" + d.getTime(), XPath.valueToXPathValue(d));

		var id = new TestIdentifier(123);
		assertEquals("'123'", XPath.valueToXPathValue(id));

		var obj = new TestObject(id);
		assertEquals("'123'", XPath.valueToXPathValue(obj));

		var proxy = new TestProxy(obj);
		assertEquals("'123'", XPath.valueToXPathValue(proxy));
	}

	// XPath
	@Test
	public void simple() throws Exception {
		var xpath = XPath.create(null, "A");
		assertEquals("//A", xpath.getXPath());
	}

	// comparisons
	@Test
	public void eq() throws Exception {
		var xpath = XPath.create(null, "A").eq("attr", 42);
		assertEquals("//A[attr = 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").eq("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr = 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").eq("assoc", "attr", 42);
		});
	}

	@Test
	public void notEq() throws Exception {
		var xpath = XPath.create(null, "A").notEq("attr", 42);
		assertEquals("//A[attr != 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").notEq("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr != 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").notEq("assoc", "attr", 42);
		});
	}

	@Test
	public void gt() throws Exception {
		var xpath = XPath.create(null, "A").gt("attr", 42);
		assertEquals("//A[attr > 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").gt("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr > 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").gt("assoc", "attr", 42);
		});
	}

	@Test
	public void gte() throws Exception {
		var xpath = XPath.create(null, "A").gte("attr", 42);
		assertEquals("//A[attr >= 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").gte("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr >= 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").gte("assoc", "attr", 42);
		});
	}

	@Test
	public void lt() throws Exception {
		var xpath = XPath.create(null, "A").lt("attr", 42);
		assertEquals("//A[attr < 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").lt("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr < 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").lt("assoc", "attr", 42);
		});
	}

	@Test
	public void lte() throws Exception {
		var xpath = XPath.create(null, "A").lte("attr", 42);
		assertEquals("//A[attr <= 42]", xpath.getXPath());

		var xpathPath = XPath.create(null, "A").lte("assoc", "entity", "attr", 42);
		assertEquals("//A[assoc/entity/attr <= 42]", xpathPath.getXPath());

		assertThrows("Expected an even number of xpath path parts", java.lang.IllegalArgumentException.class, () -> {
			XPath.create(null, "A").lte("assoc", "attr", 42);
		});
	}

	// contains, startsWith, endsWith
	@Test
	public void contains() throws Exception {
		var xpath = XPath.create(null, "A").contains("attr", "abc");
		assertEquals("//A[ contains(attr,'abc') ]", xpath.getXPath());
	}

	@Test
	public void startsWith() throws Exception {
		var xpath = XPath.create(null, "A").startsWith("attr", "abc");
		assertEquals("//A[ starts-with(attr,'abc') ]", xpath.getXPath());
	}

	@Test
	public void endsWith() throws Exception {
		var xpath = XPath.create(null, "A").endsWith("attr", "abc");
		assertEquals("//A[ ends-with(attr,'abc') ]", xpath.getXPath());
	}

	// test classes
	private static class TestIdentifier implements IMendixIdentifier {
		private final long id;

		public TestIdentifier(long id) {
			this.id = id;
		}

		@Override
		public String getObjectType() {
			throw new UnsupportedOperationException("Unimplemented method 'getObjectType'");
		}

		@Override
		public long toLong() {
			return id;
		}

		@Deprecated
		@Override
		public short getEntityId() {
			throw new UnsupportedOperationException("Unimplemented method 'getEntityId'");
		}

		@Override
		public IMendixObject getObject() {
			throw new UnsupportedOperationException("Unimplemented method 'getObject'");
		}

		@Deprecated
		@Override
		public void setObject(IMendixObject object) {
			throw new UnsupportedOperationException("Unimplemented method 'setObject'");
		}

		@Deprecated
		public TestIdentifier clone() {
			return new TestIdentifier(id);
		}
	}

	private static class TestObject implements IMendixObject {
		private final IMendixIdentifier id;

		public TestObject(IMendixIdentifier id) {
			this.id = id;
		}

		@Override
		public Map<String, ? extends IMendixObjectMember<?>> getMembers(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getMembers'");
		}

		@Override
		public List<? extends IMendixObjectMember<?>> getChangedMembers(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getChangedMembers'");
		}

		@Override
		public List<? extends MendixObjectReference> getReferences(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getReferences'");
		}

		@Override
		public List<? extends MendixObjectReferenceSet> getReferenceSets(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getReferenceSets'");
		}

		@Override
		public List<? extends IMendixObjectMember<?>> getPrimitives(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getPrimitives'");
		}

		@Override
		public Map<String, ? extends IMendixObjectMember<?>> getVirtualMembers(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'getVirtualMembers'");
		}

		@Override
		public boolean isVirtual(IContext context, String name) {
			throw new UnsupportedOperationException("Unimplemented method 'isVirtual'");
		}

		@Override
		public String getType() {
			throw new UnsupportedOperationException("Unimplemented method 'getType'");
		}

		@Override
		public void setValue(IContext context, String memberName, Object value) {
			throw new UnsupportedOperationException("Unimplemented method 'setValue'");
		}

		@Override
		public <T> T getValue(IContext context, String memberName) {
			throw new UnsupportedOperationException("Unimplemented method 'getValue'");
		}

		@Override
		public boolean hasMember(String memberName) {
			throw new UnsupportedOperationException("Unimplemented method 'hasMember'");
		}

		@Deprecated
		@Override
		public IMendixObjectMember<?> getMember(IContext context, String memberName) {
			throw new UnsupportedOperationException("Unimplemented method 'getMember'");
		}

		@Override
		public IMendixObjectMember<?> getMember(String memberName) {
			throw new UnsupportedOperationException("Unimplemented method 'getMember'");
		}

		@Override
		public IMendixIdentifier getId() {
			return id;
		}

		@Override
		public ObjectState getState() {
			throw new UnsupportedOperationException("Unimplemented method 'getState'");
		}

		@Override
		public boolean isChanged() {
			throw new UnsupportedOperationException("Unimplemented method 'isChanged'");
		}

		@Override
		public boolean hasChangedMemberValue(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'hasChangedMemberValue'");
		}

		@Override
		public boolean hasChangedByAttribute() {
			throw new UnsupportedOperationException("Unimplemented method 'hasChangedByAttribute'");
		}

		@Override
		public boolean hasChangedDateAttribute() {
			throw new UnsupportedOperationException("Unimplemented method 'hasChangedDateAttribute'");
		}

		@Override
		public boolean hasOwnerAttribute() {
			throw new UnsupportedOperationException("Unimplemented method 'hasOwnerAttribute'");
		}

		@Override
		public IMendixIdentifier getChangedBy(IContext context) throws CoreException {
			throw new UnsupportedOperationException("Unimplemented method 'getChangedBy'");
		}

		@Override
		public Date getChangedDate(IContext context) throws CoreException {
			throw new UnsupportedOperationException("Unimplemented method 'getChangedDate'");
		}

		@Override
		public IMendixIdentifier getOwner(IContext context) throws CoreException {
			throw new UnsupportedOperationException("Unimplemented method 'getOwner'");
		}

		@Override
		public boolean hasCreatedDateAttribute() {
			throw new UnsupportedOperationException("Unimplemented method 'hasCreatedDateAttribute'");
		}

		@Override
		public Date getCreatedDate(IContext context) throws CoreException {
			throw new UnsupportedOperationException("Unimplemented method 'getCreatedDate'");
		}

		@Override
		public boolean hasNullValues(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'hasNullValues'");
		}

		@Override
		public IMetaObject getMetaObject() {
			throw new UnsupportedOperationException("Unimplemented method 'getMetaObject'");
		}

		@Override
		public boolean hasDeleteRights(IContext context) {
			throw new UnsupportedOperationException("Unimplemented method 'hasDeleteRights'");
		}

		@Override
		public boolean isNew() {
			throw new UnsupportedOperationException("Unimplemented method 'isNew'");
		}

		@Deprecated
		@Override
		public TestObject createClone() {
			return new TestObject(id);
		}

		@Deprecated
		public TestObject clone() {
			return new TestObject(id);
		}
	}

	private static class TestProxy {
		private final IMendixObject obj;

		public TestProxy(IMendixObject obj) {
			this.obj = obj;
		}

		public IMendixObject getMendixObject() {
			return obj;
		}
	}

}
