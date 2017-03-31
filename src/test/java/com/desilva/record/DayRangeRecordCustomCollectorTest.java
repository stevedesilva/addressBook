package com.desilva.record;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.desilva.record.DayRangeRecordCustomCollector.*;
import static com.desilva.record.DayRangeRecordCustomCollector.AgeBoundary.*;
import static com.desilva.record.DayRangeRecordCustomCollector.AgeBoundary.OLDER;
import static com.desilva.record.DayRangeRecordCustomCollector.AgeBoundary.YOUNGER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by stevedesilva.
 */
public class DayRangeRecordCustomCollectorTest {
    final private Record olderRecord = new Record("Older, Male, 01/01/80");
    final private Record youngerRecord = new Record("Younger, Male, 01/01/81");

    @Test
    public void dayRangeRecordCustomCollectorCollectorConstructor_WhenCreated_ShouldNotBeNull() {
        assertNotNull(new DayRangeRecordCustomCollectorTest());
    }

    @Test
    public void calculateDifferenceInDaysBetweenBirthDates_GivenYoungerAndOlder_ShouldReturnDiff(){
        Map collector = createDayRangeCustomCollector("Older, Male, 01/01/80", "Younger, Male, 01/01/81");
        long value =  new DayRangeRecordCustomCollector("Younger","Older").calculateDifferenceInDaysBetweenBirthDates(collector);
        assertEquals(366L, value);
    }

    @Test
    public void calculateDifferenceInDaysBetweenBirthDates_WhenGivenIncorrectOrder_ShouldReorderAndCalculateDiff(){
        Map collector = createDayRangeCustomCollector("Younger, Male, 01/01/81","Older, Male, 01/01/80");
        long value =  new DayRangeRecordCustomCollector("Younger","Older").calculateDifferenceInDaysBetweenBirthDates(collector);
        assertEquals(366L, value);
    }






    private Map createDayRangeCustomCollector(String olderRow, String youngerRow) {
        Map collector = new HashMap<AgeBoundary,Record>();

        Record olderRecord = new Record(olderRow);
        Record youngerRecord = new Record(youngerRow);

        collector.put(OLDER, olderRecord);
        collector.put(YOUNGER, youngerRecord);
        return collector;
    }



    @Test
    public void supplier_WhenCalled_ShouldReturnSupplierFunc() throws Exception {
        Supplier<Map<AgeBoundary, Record>> value =  new DayRangeRecordCustomCollector("Younger","Older").supplier();
        assertNotNull(value);
    }

    @Test
    public void accumulator_WhenCalled_ShouldReturnBiConsumer() throws Exception {
        BiConsumer<Map<AgeBoundary, Record>, Record> accumulator =  new DayRangeRecordCustomCollector("Younger","Older").accumulator();
        assertNotNull(accumulator);
    }

    @Test
    public void updateCollectorWithYoungOldRecord_WhenMatchOlderRecord_ShouldAddToOlderMap() throws Exception {
        DayRangeRecordCustomCollector customCollector = new DayRangeRecordCustomCollector("Younger","Older");
        Map accumulatorMap = new HashMap<AgeBoundary,Record>();
        customCollector.updateCollectorWithYoungOldRecord(accumulatorMap, olderRecord);

        assertEquals(olderRecord,accumulatorMap.get(OLDER));

    }

    @Test
    public void updateCollectorWithYoungOldRecord_WhenMatchingOlderRecord_ShouldAddToYoungerMap() throws Exception {
        DayRangeRecordCustomCollector customCollector = new DayRangeRecordCustomCollector("Younger","Older");
        Map accumulatorMap = new HashMap<AgeBoundary,Record>();
        customCollector.updateCollectorWithYoungOldRecord(accumulatorMap, youngerRecord);
        assertEquals(youngerRecord,accumulatorMap.get(YOUNGER));
    }

    @Test
    public void updateCollectorWithYoungOldRecord_WhenNoMatchFound_ShouldNotAddToMap() throws Exception {
        DayRangeRecordCustomCollector customCollector = new DayRangeRecordCustomCollector("Younger","Older");
        Map accumulatorMap = new HashMap<AgeBoundary,Record>();
        customCollector.updateCollectorWithYoungOldRecord(accumulatorMap, new Record("Not Matching, Male, 01/01/81"));
        assertEquals(null,accumulatorMap.get(OLDER));
        assertEquals(null,accumulatorMap.get(YOUNGER));
    }



    @Test
    public void combiner_WhenJoiningMaps_ShouldUpdateMap1IfEmptyAndMap2HasARecord() throws Exception {
        DayRangeRecordCustomCollector customCollector = new DayRangeRecordCustomCollector("Younger","Older");
        Map accumulatorMap1 = new HashMap<AgeBoundary,Record>();
        Map accumulatorMap2 = new HashMap<AgeBoundary,Record>();
        accumulatorMap2.put(YOUNGER,youngerRecord);
        accumulatorMap2.put(OLDER,olderRecord);

        customCollector.combineAgeBoundaryRecordMaps(accumulatorMap1,accumulatorMap2);

        assertEquals(youngerRecord, accumulatorMap1.get(YOUNGER));
        assertEquals(olderRecord, accumulatorMap1.get(OLDER));
    }

    @Test
    public void combiner_WhenJoiningMaps_ShouldNotUpdateMap1IfAlreadyContainsRecord() throws Exception {
        DayRangeRecordCustomCollector customCollector = new DayRangeRecordCustomCollector("Younger","Older");
        Map accumulatorMap1 = new HashMap<AgeBoundary,Record>();
        Map accumulatorMap2 = new HashMap<AgeBoundary,Record>();
        accumulatorMap1.put(YOUNGER,youngerRecord);
        accumulatorMap1.put(OLDER,olderRecord);

        customCollector.combineAgeBoundaryRecordMaps(accumulatorMap1,accumulatorMap2);
        assertEquals(youngerRecord, accumulatorMap1.get(YOUNGER));
        assertEquals(olderRecord, accumulatorMap1.get(OLDER));
    }


    @Test
    public void finisher_WhenCalled_ShouldReturnBiConsumer() throws Exception {
        Function<Map<AgeBoundary, Record>, Long> finisher =  new DayRangeRecordCustomCollector("Younger","Older").finisher();
        assertNotNull(finisher);
    }

    @Test
    public void characteristics_WhenCalled_ShouldReturnBiConsumer() throws Exception {
        Set<Collector.Characteristics> characteristics =  new DayRangeRecordCustomCollector("Younger","Older").characteristics();
        assertNotNull(characteristics);
    }

}