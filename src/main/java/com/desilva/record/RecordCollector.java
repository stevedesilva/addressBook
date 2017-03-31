package com.desilva.record;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by stevedesilva
 */
public interface RecordCollector {

    Optional<List<Record>> streamToRecordMapper(Stream<String> stream);

}
