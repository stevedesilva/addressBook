package com.desilva.record;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by stevedesilva
 */
public class RecordCollectorImpl implements RecordCollector {

    @Override
    public Optional<List<Record>> streamToRecordMapper(Stream<String> stream) {

        List<Record> records;
        if (stream == null) {
            return Optional.empty();
        }
        try {
           records = stream
                    .map(Record::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException ie) {
            return Optional.empty();
        } catch (DateTimeParseException de) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.ofNullable(records);
    }


}
