package com.desilva.file;

import org.junit.Test;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by stevedesilva.
 */
public class FileReaderImpTest {

    final private FileReader fileReader = new FileReaderImp();

    @Test
    public void fileReader_WhenConstructed_ShouldNotBeNull() {
        assertNotNull(new FileReaderImp());
    }

    @Test
    public void isInputValid_WhenGivenValidStringArg_ShouldReturnTrue() {
        String addressBookFileName = getAddressBookFileName();
        boolean isValidFile =  fileReader.fileValidator(addressBookFileName);
        assertTrue(isValidFile);
    }

    @Test
    public void isInputValid_WhenGivenNoArg_ShouldReturnFalse() {
        boolean isValidFile =  fileReader.fileValidator(null);
        assertFalse(isValidFile);
    }

    @Test
    public void isInputValid_WhenGivenInvalidFormat_ShouldReturnFalse() {
        String invalidFileName = "£$$%RRRT";
        boolean isValidFile =  fileReader.fileValidator(invalidFileName);
        assertFalse(isValidFile);
    }

    @Test
    public void isInputValid_WhenGivenDirectory_ShouldReturnFalse() {
        String fakeDirectory = "/some/fake/directory/";
        boolean isValidFile =  fileReader.fileValidator(fakeDirectory);
        assertFalse(isValidFile);
    }

    @Test
    public void isInputValid_WhenGivenFileThatDoesntExist_ShouldReturnFalse() {
        String nonExistentFile = "/some/fake/directory/StevesNonExistentFile";
        boolean isValidFile =  fileReader.fileValidator(nonExistentFile);
        assertFalse(isValidFile);
    }

    @Test
    public void fileToStream_WhenGivenValidFile_ShouldReturnTrueFileReaderResult(){
        String addressBookFileName = getAddressBookFileName();
        Optional<Stream<String>> fileReaderResult =  fileReader.fileToStream(addressBookFileName);
        assertTrue(fileReaderResult.isPresent());
        long numberOfRecords = fileReaderResult.get().count();
        assertEquals(5L,numberOfRecords);
    }

    @Test
    public void fileToStream_WhenGivenInValidFile_ShouldReturnFalseFileReaderResult(){
        String nonExistentFile = "/some/fake/directory/StevesNonExistentFile";
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(nonExistentFile);
        assertFalse(fileReaderResult.isPresent());

    }

    @Test
    public void fileToStream_WhenGivenNoArg_ShouldReturnFalseFileReaderResult(){
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(null);
        assertFalse(fileReaderResult.isPresent());
    }

    @Test
    public void fileToStream_WhenGivenInvalidFormat_ShouldReturnFalseFileReaderResult(){
        String invalidFileName = "£$$%RRRT";
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(invalidFileName);
        assertFalse(fileReaderResult.isPresent());
    }

    @Test
    public void fileToStream_WhenGivenOnlyDirectory_ShouldReturnFalseFileReaderResult() {
        String fakeDirectory = "/some/fake/directory/";
        Optional<Stream<String>> fileReaderResult= fileReader.fileToStream(fakeDirectory);
        assertFalse(fileReaderResult.isPresent());
    }

    @Test
    public void fileToStream_WhenGivenFileThatDoesntExist_ShouldReturnFalseFileReaderResult() {
        String nonExistentFile = "/some/fake/directory/StevesNonExistentFile";
        Optional<Stream<String>> fileReaderResult = fileReader.fileToStream(nonExistentFile);
        assertFalse(fileReaderResult.isPresent());
    }

    private String getAddressBookFileName() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("AddressBook").getFile());
        return file.getAbsolutePath();
    }

}