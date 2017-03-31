package com.desilva.response;

import com.desilva.file.FileReader;
import com.desilva.file.FileReaderImp;
import com.desilva.record.Record;
import com.desilva.record.RecordCollector;
import com.desilva.record.RecordCollectorImpl;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by stevedesilva on 31/03/2017.
 */
public class QuestionsImplTest {
    final private RecordCollector recordCollector = new RecordCollectorImpl();
    final private FileReader fileReader = new FileReaderImp();
    final private List<Record> recordList = createRecordLists();

    @Test
    public void questions_WhenConstructed_ShouldNotBeNull() {
        assertNotNull(new QuestionsImpl(recordList));
    }

    private List<Record> createRecordLists() {
        List<Record> recordList = new ArrayList<>();
        recordList.add(new Record("Bill McKnight, Male, 16/03/77"));
        recordList.add(new Record("Paul Robinson, Male, 15/01/85"));
        recordList.add(new Record("Clive Happy, Male, 11/12/99"));
        return recordList;
    }

    @Test
    public void questions_WhenCalled_ShouldNoReturnEmptyList() throws Exception {
        Questions questions = new QuestionsImpl(recordList);
        questions.getQuestions();
        assertNotNull(questions.getQuestions());
    }

    @Test
    public void questions_WhenCalled_ShouldReturnListOfQuestionResponse() throws Exception {
        Questions questions = new QuestionsImpl(recordList);
        questions.getQuestions();
        assertEquals(3, questions.getQuestions().size());
    }


//    public void getRecords() {
//        String addressBookFileName = getAddressBookFileName("AddressBookRecords");
//        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(addressBookFileName);
//        RecordCollectorResult recordCollectorResult = recordCollector.streamToRecordMapper(fileReaderResult.get());
//        assertNull(recordCollectorResult.getRecords());
//        assertFalse(recordCollectorResult.isValid());
//    }

    private String getAddressBookFileName(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}