package communitycommons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive.PrimitiveType;

public class ORMTest {

	private static final String[] SYSTEM_MEMBERS = { "__UUID__", "createdDate", "changedDate", "System.owner", "System.changedBy" };

	// copyAttributes

	@Test
	public void copyAttributesCopiesEverythingByDefault() {
		var source = fakeObject(false, "Name", "Other", "createdDate", "changedDate", "__UUID__");
		var target = fakeObject(false, "Name", "Other", "createdDate", "changedDate", "__UUID__");

		ORM.copyAttributes(null, source, target);

		assertEquals(5, valuesOf(target).size());
		assertEquals("Other-value", valuesOf(target).get("Other"));
		assertEquals("createdDate-value", valuesOf(target).get("createdDate"));
	}

	@Test
	public void copyAttributesCopiesSystemAttributesWhenFlagTrueOrEmpty() {
		for (Boolean flag : new Boolean[] { true, null }) {
			var source = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__");
			var target = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__");

			ORM.copyAttributes(null, source, target, flag, null);

			assertEquals("flag " + flag, 4, valuesOf(target).size());
		}
	}

	@Test
	public void copyAttributesSkipsSystemAttributesWhenFlagFalse() {
		var source = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__", "owner", "changedBy");
		var target = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__", "owner", "changedBy");

		ORM.copyAttributes(null, source, target, false, null);

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesSkipsExcludedAttributes() {
		var source = fakeObject(false, "Name", "Other", "Third", "createdDate");
		var target = fakeObject(false, "Name", "Other", "Third", "createdDate");

		ORM.copyAttributes(null, source, target, true, "Other, Third,,");

		assertEquals(List.of("Name", "createdDate"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesCombinesFlagAndExcludeList() {
		var source = fakeObject(false, "Name", "Other", "createdDate");
		var target = fakeObject(false, "Name", "Other", "createdDate");

		ORM.copyAttributes(null, source, target, false, "Other");

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesIgnoresUnknownExcludeName() {
		var source = fakeObject(false, "Name");
		var target = fakeObject(false, "Name");

		ORM.copyAttributes(null, source, target, true, "DoesNotExist");

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesStillSkipsUuidForFileDocuments() {
		var source = fakeObject(true, "Name", "__UUID__");
		var target = fakeObject(true, "Name", "__UUID__");

		ORM.copyAttributes(null, source, target, true, null);

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesSkipsMembersMissingOnSource() {
		var source = fakeObject(false, "Name");
		var target = fakeObject(false, "Name", "OnlyOnTarget");

		ORM.copyAttributes(null, source, target);

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void copyAttributesThrowsOnNull() {
		var object = fakeObject(false, "Name");

		assertThrows(IllegalStateException.class, () -> ORM.copyAttributes(null, null, object, true, null));
		assertThrows(IllegalStateException.class, () -> ORM.copyAttributes(null, object, null, true, null));
	}

	// cloneObject

	@Test
	public void cloneObjectCopiesEverythingByDefault() {
		var source = fakeObject(false, "Name", "createdDate", "__UUID__", "System.owner");
		var target = fakeObject(false);

		ORM.cloneObject(null, source, target, true);

		assertEquals(4, valuesOf(target).size());
	}

	@Test
	public void cloneObjectCopiesSystemAttributesWhenFlagTrueOrEmpty() {
		for (Boolean flag : new Boolean[] { true, null }) {
			var source = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__", "System.owner", "System.changedBy");
			var target = fakeObject(false);

			ORM.cloneObject(null, source, target, true, flag, null);

			assertEquals("flag " + flag, 6, valuesOf(target).size());
		}
	}

	@Test
	public void cloneObjectSkipsSystemAttributesWhenFlagFalse() {
		var source = fakeObject(false, "Name", "createdDate", "changedDate", "__UUID__", "System.owner", "System.changedBy");
		var target = fakeObject(false);

		ORM.cloneObject(null, source, target, true, false, null);

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
		for (String name : SYSTEM_MEMBERS) {
			assertFalse(name, valuesOf(target).containsKey(name));
		}
	}

	@Test
	public void cloneObjectSkipsExcludedAttributes() {
		var source = fakeObject(false, "Name", "Other", "Third");
		var target = fakeObject(false);

		ORM.cloneObject(null, source, target, true, true, " Name , Third");

		assertEquals(List.of("Other"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void cloneObjectStillSkipsUuidForFileDocuments() {
		var source = fakeObject(true, "Name", "__UUID__");
		var target = fakeObject(true);

		ORM.cloneObject(null, source, target, true, true, null);

		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	@Test
	public void cloneObjectKeepsMembersWhenFlagEmptyAndListEmpty() {
		var source = fakeObject(false, "Name");
		var target = fakeObject(false);

		assertTrue(ORM.cloneObject(null, source, target, true, null, ""));
		assertEquals(List.of("Name"), new ArrayList<>(valuesOf(target).keySet()));
	}

	// fakes

	/** Value of a member is "<name>-value" on the source side. Setting a value on an object records it. */
	private static IMendixObject fakeObject(boolean fileDocument, String... memberNames) {
		var values = new LinkedHashMap<String, Object>();
		var sourceValues = new LinkedHashMap<String, Object>();
		for (String name : memberNames) {
			sourceValues.put(name, name + "-value");
		}

		IMetaObject meta = (IMetaObject) Proxy.newProxyInstance(
				ORMTest.class.getClassLoader(), new Class<?>[] { IMetaObject.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "isFileDocument":
						return fileDocument;
					case "getMetaPrimitives":
						List<IMetaPrimitive> primitives = new ArrayList<>();
						for (String name : memberNames) {
							primitives.add(fakePrimitive(name));
						}
						return primitives;
					default:
						return defaultFor(proxy, method.getName(), args);
					}
				});

		return (IMendixObject) Proxy.newProxyInstance(
				ORMTest.class.getClassLoader(), new Class<?>[] { IMendixObject.class, ValueHolder.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "getMetaObject":
						return meta;
					case "hasMember":
						return sourceValues.containsKey(args[0]);
					case "getValue":
						return sourceValues.get(args[1]);
					case "setValue":
						values.put((String) args[1], args[2]);
						return null;
					case "getMembers":
						Map<String, IMendixObjectMember<?>> members = new LinkedHashMap<>();
						for (String name : memberNames) {
							members.put(name, fakeMember(name, sourceValues.get(name)));
						}
						return members;
					case "values":
						return values;
					default:
						return defaultFor(proxy, method.getName(), args);
					}
				});
	}

	/** Marker interface so the test can read back the recorded values from the proxy. */
	public interface ValueHolder {
		Map<String, Object> values();
	}

	private static Map<String, Object> valuesOf(IMendixObject object) {
		return ((ValueHolder) object).values();
	}

	private static IMetaPrimitive fakePrimitive(String name) {
		return (IMetaPrimitive) Proxy.newProxyInstance(
				ORMTest.class.getClassLoader(), new Class<?>[] { IMetaPrimitive.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "getName":
						return name;
					case "isVirtual":
						return false;
					case "getType":
						return PrimitiveType.String;
					default:
						return defaultFor(proxy, method.getName(), args);
					}
				});
	}

	@SuppressWarnings("unchecked")
	private static IMendixObjectMember<Object> fakeMember(String name, Object value) {
		return (IMendixObjectMember<Object>) Proxy.newProxyInstance(
				ORMTest.class.getClassLoader(), new Class<?>[] { IMendixObjectMember.class }, (proxy, method, args) -> {
					switch (method.getName()) {
					case "getName":
						return name;
					case "isVirtual":
						return false;
					case "getValue":
						return value;
					default:
						return defaultFor(proxy, method.getName(), args);
					}
				});
	}

	private static Object defaultFor(Object proxy, String methodName, Object[] args) {
		switch (methodName) {
		case "toString":
			return "fake";
		case "hashCode":
			return System.identityHashCode(proxy);
		case "equals":
			return proxy == args[0];
		default:
			throw new UnsupportedOperationException(methodName);
		}
	}
}
