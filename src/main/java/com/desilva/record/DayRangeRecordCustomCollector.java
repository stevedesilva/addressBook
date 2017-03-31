package com.desilva.record;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.UNORDERED;

/**
 * Created by stevedesilva.
 */
public class DayRangeRecordCustomCollector implements Collector<Record, Map<DayRangeRecordCustomCollector.AgeBoundary, Record>, Long> {
    private String youngerPersonName;
    private String olderPersonName;

    public DayRangeRecordCustomCollector(String youngerPerson, String olderPerson) {
        this.youngerPersonName = youngerPerson;
        this.olderPersonName = olderPerson;
    }

    public enum AgeBoundary {
        OLDER, YOUNGER
    }

    public long calculateDifferenceInDaysBetweenBirthDates(Map<AgeBoundary, Record> acc) {
        Record youngerPersonRecord = acc.get(AgeBoundary.YOUNGER);
        Record olderPersonRecord = acc.get(AgeBoundary.OLDER);

        LocalDate olderPersonRecordBirth = olderPersonRecord.getBirth();
        LocalDate youngerPersonRecordBirth = youngerPersonRecord.getBirth();

        // reorder dates if needed
        if (olderPersonRecordBirth.isAfter(youngerPersonRecordBirth)) {
            olderPersonRecordBirth = youngerPersonRecord.getBirth();
            youngerPersonRecordBirth = olderPersonRecord.getBirth();
        }
        return ChronoUnit.DAYS.between(olderPersonRecordBirth, youngerPersonRecordBirth);
    }

    @Override
    public Supplier<Map<AgeBoundary, Record>> supplier() {
        return () -> new HashMap<AgeBoundary, Record>();
    }

    @Override
    public BiConsumer<Map<AgeBoundary, Record>, Record> accumulator() {
        return (Map<AgeBoundary, Record> collector, Record record) -> {
            updateCollectorWithYoungOldRecord(collector, record);
        };
    }

    public void updateCollectorWithYoungOldRecord(Map<AgeBoundary, Record> collector, Record record) {
        if (olderPersonName.equals(record.getName())) {
            collector.put(AgeBoundary.OLDER, record);
        } else if (youngerPersonName.equals(record.getName())) {
            collector.put(AgeBoundary.YOUNGER, record);
        }
    }


    @Override
    public BinaryOperator<Map<AgeBoundary, Record>> combiner() {
        return (Map<AgeBoundary, Record> acc1, Map<AgeBoundary, Record> acc2) -> {
            return combineAgeBoundaryRecordMaps(acc1, acc2);
        };
    }

    protected Map<AgeBoundary, Record> combineAgeBoundaryRecordMaps(Map<AgeBoundary, Record> acc1, Map<AgeBoundary, Record> acc2) {
        if (acc1.get(AgeBoundary.OLDER) == null && acc2.get(AgeBoundary.OLDER) != null) {
            acc1.put(AgeBoundary.OLDER, acc2.get(AgeBoundary.OLDER));
        }
        if (acc1.get(AgeBoundary.YOUNGER) == null && acc2.get(AgeBoundary.YOUNGER) != null) {
            acc1.put(AgeBoundary.YOUNGER, acc2.get(AgeBoundary.YOUNGER));
        }
        return acc1;
    }

    @Override
    public Function<Map<AgeBoundary, Record>, Long> finisher() {
        return t -> calculateDifferenceInDaysBetweenBirthDates(t);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(CONCURRENT, UNORDERED));
    }

}
