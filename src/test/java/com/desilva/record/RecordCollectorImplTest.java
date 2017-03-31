package com.desilva.record;

import com.desilva.file.FileReader;
import com.desilva.file.FileReaderImp;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by stevedesilva.
 */
public class RecordCollectorImplTest {

    final private RecordCollector recordCollector = new RecordCollectorImpl();
    final private FileReader fileReader = new FileReaderImp();


    @Test
    public void recordCollectorConstructor_WhenCreated_ShouldNotBeNull() {
        assertNotNull(new RecordCollectorImpl());
    }


    @Test
    public void streamToRecordMapper_WhenGivenValidFile_ShouldReturnTrueRecordCollectorResult() {
        String addressBookFileName = getAddressBookFileName("AddressBook");
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
        assertTrue(recordCollectorResult.isPresent());
        assertNotNull(recordCollectorResult.get());
        long numberOfRecords = recordCollectorResult.get().size();
        assertEquals(5L,numberOfRecords);
    }

    @Test
    public void streamToRecordMapper_WhenGivenNoArg_ShouldReturnFalseRecordCollectorResult(){
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(null);
        assertFalse(recordCollectorResult.isPresent());
    }

    @Test
    public void streamToRecordMapper_WhenGivenIncorrectStream_ShouldReturnFalseRecordCollectorResult(){
        List<String> stringList = Arrays.asList("Some String", "Another String", "Yet another string");
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(stringList.stream());
        assertFalse(recordCollectorResult.isPresent());
    }

    @Test
    public void streamToRecordMapper_WhenGivenFileWithValueInWrongOrder_ShouldReturnFalseRecordCollectorResult() {
        String addressBookFileName = getAddressBookFileName("AddressBookRecordsInWrongOrder");
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
        assertFalse(recordCollectorResult.isPresent());
    }




    @Test
    public void streamToRecordMapper_WhenGivenFileWithMissingValues_ShouldReturnFalseRecordCollectorResult() {
        String addressBookFileName = getAddressBookFileName("AddressBookInvalidMissingValues");
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
        assertFalse(recordCollectorResult.isPresent());
    }

    @Test
    public void streamToRecordMapper_WhenGivenFileWithInvalidValues_ShouldReturnFalseRecordCollectorResult() {
        String addressBookFileName = getAddressBookFileName("AddressBookInvalidValues");
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
        assertFalse(recordCollectorResult.isPresent());
    }



    @Test
    public void streamToRecordMapper_WhenGivenFileWithAdditionalValues_ShouldReturnFalseRecordCollectorResult() {
        String addressBookFileName = getAddressBookFileName("AddressBookInvalidAdditionalValues");
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
        Optional<List<Record>> recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
        assertFalse(recordCollectorResult.isPresent());
    }

    private String getAddressBookFileName(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}