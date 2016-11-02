package communitycommons.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.mendix.core.objectmanagement.member.MendixHashString;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
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
        Map<String, ? extends IMendixObjectMember<?>> expectedMembers = expected.getMembers(context);
        Map<String, ? extends IMendixObjectMember<?>> cleanedExpectedMembers =
                cleanDefaultKeys(expectedMembers);

        Map<String, ? extends IMendixObjectMember<?>> actualMembers = actual.getMembers(context);
        Map<String, ? extends IMendixObjectMember<?>> cleanedActualMembers =
                cleanDefaultKeys(actualMembers);

        MapDifference<String, ? extends IMendixObjectMember<?>> mapDifference =
                Maps.difference(cleanedExpectedMembers, cleanedActualMembers);

        if (!mapDifference.entriesDiffering().isEmpty()) {
            assertStatus = false;
            Map<String, ? extends ValueDifference<? extends IMendixObjectMember<?>>> differences =
                    mapDifference.entriesDiffering();
            for (Map.Entry<String, ? extends ValueDifference<? extends IMendixObjectMember<?>>> entry : differences.entrySet()) {
                logger.error(expected.getType() + "." + entry.getKey() + "' expected value is '"
                        + entry.getValue().leftValue().getValue(context) + "' where actual value is '"
                        + entry.getValue().rightValue().getValue(context) + "'");
            }
        }
        if (!mapDifference.entriesOnlyOnLeft().isEmpty()) {
            assertStatus = false;
            Map<String, ? extends IMendixObjectMember<?>> existsInExpected =
                    mapDifference.entriesOnlyOnLeft();
            for (Map.Entry<String, ? extends IMendixObjectMember<?>> entry : existsInExpected.entrySet()) {
                logger.error(expected.getType() + "." + entry.getKey() + " => " +
                        entry.getValue().getValue(context) + "' is not set in actual object.");
            }
        }
        if (!mapDifference.entriesOnlyOnRight().isEmpty()) {
            assertStatus = false;
            Map<String, ? extends IMendixObjectMember<?>> existsInExpected =
                    mapDifference.entriesOnlyOnRight();
            for (Map.Entry<String, ? extends IMendixObjectMember<?>> entry : existsInExpected.entrySet()) {
                logger.error(actual.getType() + "." + entry.getKey() + " => " +
                        entry.getValue().getValue(context) + "' is not set in expected object.");
            }
        }
        return assertStatus;
    }

    private Map<String, ? extends IMendixObjectMember<?>> cleanDefaultKeys(
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
            if (map.containsKey(defaultKey)) {
                map.remove(defaultKey);
            }
        }

        for (Iterator<? extends Map.Entry<String, ? extends IMendixObjectMember<?>>> it =
             map.entrySet().iterator(); it.hasNext();) {
            Entry<String, ? extends IMendixObjectMember<?>> entry = it.next();
            if (entry.getValue() instanceof MendixHashString) {
                it.remove();
            }
            if (entry.getValue().getValue(context) == null) {
                it.remove();
            }
            if (entry.getValue().getValue(context) instanceof List) {
                List list = (List) entry.getValue().getValue(context);
                if (list.size() == 0) {
                    it.remove();
                }
            }
        }
        return map;
    }
}
