package communitycommons.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mendix.core.objectmanagement.MendixObjectMember;
import com.mendix.core.objectmanagement.member.MendixHashString;
import com.mendix.core.objectmanagement.member.MendixObjectReferenceSet;
import com.mendix.core.objectmanagement.member.MendixString;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;

import communitycommons.actions.Comparator;

public class ComparatorTest {

    private IMendixObject expectedMock;
    private IMendixObject actualMock;
    private ILogNode loggerMock;
    private IContext context;
    private Map<String, IMendixObjectMember<?>> expectedMockMap = new HashMap<String, IMendixObjectMember<?>>();
    private Map<String, IMendixObjectMember<?>> actualMockMap = new HashMap<String, IMendixObjectMember<?>>();

    @Before
    public void setUp() throws Exception {
        this.context = mock(IContext.class);
        this.expectedMock = mock(IMendixObject.class);
        this.actualMock = mock(IMendixObject.class);
        this.loggerMock = mock(ILogNode.class);
    }

    @Test
    public void testIfTrueIsReturnedWhenObjectsAreEqual() {
        IMendixObjectMember<String> expectedString = mock(MendixString.class);
        when(expectedString.getValue(context)).thenReturn("Some string");
        expectedMockMap.put("SomeKeyString", expectedString);
        when(expectedMock.getMembers(context)).thenReturn(expectedMockMap);

        actualMockMap.put("SomeKeyString", expectedString);
        when(actualMock.getMembers(context)).thenReturn(actualMockMap);

        Comparator comparator = new Comparator(expectedMock, actualMock, context, loggerMock);
        assertTrue(comparator.CompareLists());
        verify(expectedMock, times(1)).getMembers(context);
        verify(actualMock, times(1)).getMembers(context);
        verify(loggerMock, times(0)).error(Mockito.anyString());
    }

    @Test
    public void testIfTrueIsReturnedWhenValuesAreNull() {
        IMendixObjectMember<String> expectedString = mock(MendixString.class);
        when(expectedString.getValue(context)).thenReturn(null);
        expectedMockMap.put("SomeKeyString", expectedString);
        when(expectedMock.getMembers(context)).thenReturn(expectedMockMap);

        IMendixObjectMember<String> actualString = mock(MendixString.class);
        when(actualString.getValue(context)).thenReturn(null);
        actualMockMap.put("SomeKeyString", actualString);
        when(actualMock.getMembers(context)).thenReturn(actualMockMap);

        Comparator comparator = new Comparator(expectedMock, actualMock, context, loggerMock);
        assertTrue(comparator.CompareLists());
        verify(expectedMock, times(1)).getMembers(context);
        verify(actualMock, times(1)).getMembers(context);
        verify(loggerMock, times(0)).error(Mockito.anyString());
    }

    @Test
    public void testIfFalseIsReturnedWhenValuesAreNotEqual() {
        // Given we have an expected object with a String value and an actual with another String value
        IMendixObjectMember<String> expectedString = mock(MendixString.class);
        expectedMockMap.put("OtherStringTest", expectedString);
        when(expectedString.getValue(context)).thenReturn("Some string");
        
        IMendixObjectMember<String> actualString = mock(MendixString.class);
        when(actualString.getValue(context)).thenReturn("Some other string");
        actualMockMap.put("OtherStringTest", actualString);
        
        // And we have an expected object with a null value where actual is a String
        IMendixObjectMember<String> expectedNull = mock(MendixString.class);
        expectedMockMap.put("NullStringTestExpected", expectedNull);
        when(expectedNull.getValue(context)).thenReturn(null);
        
        IMendixObjectMember<String> actualSecondString = mock(MendixString.class);
        actualMockMap.put("NullStringTestExpected", actualSecondString);
        when(actualSecondString.getValue(context)).thenReturn("Some second string");

        // And we have an expected object with a String where actual is null
        IMendixObjectMember<String> expectedSecondString = mock(MendixString.class);
        expectedMockMap.put("NullStringTestActual", expectedSecondString);
        when(expectedSecondString.getValue(context)).thenReturn("Some second string");
        
        IMendixObjectMember<String> actualNull = mock(MendixString.class);
        actualMockMap.put("NullStringTestActual", actualNull);
        when(actualNull.getValue(context)).thenReturn(null);
        
        when(expectedMock.getMembers(context)).thenReturn(expectedMockMap);
        when(actualMock.getMembers(context)).thenReturn(actualMockMap);

        // When the comparator is called
        Comparator comparator = new Comparator(expectedMock, actualMock, context, loggerMock);
        
        // Then we expect False to be returned
        assertFalse(comparator.CompareLists());
        
        // And that the mocks are called
        verify(expectedMock, times(1)).getMembers(context);
        verify(actualMock, times(1)).getMembers(context);
        verify(loggerMock, times(3)).error(Mockito.anyString());
    }
    
    @Test
    public void testIfDefaultKeysAreProperlyDeleted() {
        // Given we have an expected object with a all the default keys + another string
        IMendixObjectMember<String> expectedString = mock(MendixString.class);
        IMendixObjectMember<String> expectedHashString = mock(MendixHashString.class);
        expectedMockMap.put("changedDate", expectedString);
        expectedMockMap.put("createdDate", expectedString);
        expectedMockMap.put("System.changedBy", expectedString);
        expectedMockMap.put("System.owner", expectedString);
        expectedMockMap.put("HashString", expectedHashString);
        expectedMockMap.put("OtherStringTest", expectedString);
        when(expectedString.getValue(context)).thenReturn("Some string");
        when(expectedHashString.getValue(context)).thenReturn("Some hash");
        
        // And we have an expected object with an empty list
        MendixObjectMember<List<IMendixIdentifier>> expectedList = mock(MendixObjectReferenceSet.class); 
        expectedMockMap.put("SomeList", expectedList);
        List<IMendixIdentifier> someList = new ArrayList<IMendixIdentifier>();
        when(expectedList.getValue(context)).thenReturn(someList);
        
        // And we have an actual object without the default keys
        actualMockMap.put("OtherStringTest", expectedString);
        
        when(expectedMock.getMembers(context)).thenReturn(expectedMockMap);
        when(actualMock.getMembers(context)).thenReturn(actualMockMap);

        // When the comparator is called
        Comparator comparator = new Comparator(expectedMock, actualMock, context, loggerMock);
        
        // Then we expect True to be returned
        assertTrue(comparator.CompareLists());
        
        // And that the mocks are called
        verify(expectedMock, times(1)).getMembers(context);
        verify(actualMock, times(1)).getMembers(context);
        verify(loggerMock, times(0)).error(Mockito.anyString());
    }
    
    @Test
    public void testIfFalseIsReturnedWhenIMendixObjectIsNull() {
      // When: Expected and Actual objects are null
      Comparator comparator = new Comparator(null, null, context, loggerMock);
      
      // Then: I expect false to be returned
      assertFalse(comparator.CompareLists());
      
      // And: I expect a log message
      verify(loggerMock, times(1)).error(Mockito.anyString());
    }
}
