package communitycommons.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.mendix.core.objectmanagement.MendixObjectMember;
import com.mendix.core.objectmanagement.member.MendixHashString;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixIdentifier;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.IMendixObjectMember;

public class Comparator {

    private IMendixObject expected;
    private IMendixObject actual;
    private ILogNode logger;
    private IContext context;
    private boolean assertStatus = true;

    public Comparator(IMendixObject expected, IMendixObject actual, IContext context,
                      ILogNode logger) {
        this.expected = expected;
        this.actual = actual;
        this.logger = logger;
        this.context = context;
    }

    public boolean CompareLists() {
        if (expected == null) {
          logger.error("Expected object is NULL.");
          return false;
        }
        
        if (actual == null) {
          logger.error("Actual object is NULL.");
          return false;
        }
        
        Map<String, ? extends IMendixObjectMember<?>> expectedMembers = expected.getMembers(context);
        TreeMap<String, Object> cleanedExpectedMembers =
                cleanDefaultKeys(expectedMembers);
        
        Map<String, ? extends IMendixObjectMember<?>> actualMembers = actual.getMembers(context);
        TreeMap<String, Object> cleanedActualMembers =
                cleanDefaultKeys(actualMembers);
        
        MapDifference<String, Object> mapDifference =
                Maps.difference(cleanedExpectedMembers, cleanedActualMembers);

        if (!mapDifference.entriesDiffering().isEmpty()) {
            assertStatus = false;
            Map<String, ValueDifference<Object>> differences =
                    mapDifference.entriesDiffering();
            for (Map.Entry<String, ? extends ValueDifference<Object>> entry : differences.entrySet()) {
                logger.error(expected.getType() + "." + entry.getKey() + 
                        " expected value is '" + entry.getValue().leftValue().getClass().getSimpleName() + " => " +
                        entry.getValue().leftValue() + "' where actual value is '" + 
                        entry.getValue().leftValue().getClass().getSimpleName() + " => " + 
                        entry.getValue().rightValue()  + "'");
            }
        }
        if (!mapDifference.entriesOnlyOnLeft().isEmpty()) {
            assertStatus = false;
            Map<String, Object> existsInExpected =
                    mapDifference.entriesOnlyOnLeft();
            for (Map.Entry<String, Object> entry : existsInExpected.entrySet()) {
                logger.error(expected.getType() + "." + entry.getKey() + " => " +
                        entry.getValue() + "' is not set in actual object.");
            }
        }
        if (!mapDifference.entriesOnlyOnRight().isEmpty()) {
            assertStatus = false;
            Map<String, Object> existsInExpected =
                    mapDifference.entriesOnlyOnRight();
            for (Map.Entry<String, Object> entry : existsInExpected.entrySet()) {
                logger.error(actual.getType() + "." + entry.getKey() + " => " +
                        entry.getValue() + "' is not set in expected object.");
            }
        }
        return assertStatus;
    }

    private TreeMap<String, Object> cleanDefaultKeys(
            Map<String, ? extends IMendixObjectMember<?>> map) {
        List<String> defaultKeys = new ArrayList<>();
        defaultKeys.add("changedDate");
        defaultKeys.add("createdDate");
        defaultKeys.add("System.changedBy");
        defaultKeys.add("System.owner");
        
        for (String defaultKey : defaultKeys) {
            if (map.get(defaultKey) != null) {
                map.remove(defaultKey);
            }
        }

        TreeMap<String, Object> treeMap = new TreeMap<>();
        
        for (Iterator<? extends Map.Entry<String, ? extends IMendixObjectMember<?>>> it =
             map.entrySet().iterator(); it.hasNext();) {
            Entry<String, ? extends IMendixObjectMember<?>> entry = it.next();
            if (entry.getValue() instanceof MendixHashString) {
                it.remove();
                continue;
            }
            if (entry.getValue().getValue(context) == null) {
                it.remove();
                continue;
            }
            if (entry.getValue().getValue(context) instanceof List) {
              List<MendixObjectMember<List<IMendixIdentifier>>> list = (List<MendixObjectMember<List<IMendixIdentifier>>>) entry.getValue().getValue(context);
                if (list.size() == 0) {
                    it.remove();
                    continue;
                }
            }
            treeMap.put(entry.getKey(), entry.getValue().getValue(context));
            continue;
        }

        return treeMap;
    }
}
