package com.desilva.record;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by stevedesilva.
 */
public class RecordTest {

    @Test
    public void record_WhenConstructed_ShouldNotBeNull() {
        Record record = new Record("TestName, Female, 11/12/99");
        assertNotNull(record);
    }

    @Test
    public void record_WhenConstructed_ShouldCreateName() {
        Record record = new Record("Mike Happy, Male, 11/12/99");
        assertNotNull(record);
        assertEquals("Mike Happy", record.getName());
    }

    @Test
    public void record_WhenConstructedWithNumericName_ShouldCreateName() {
        Record record = new Record("C3PO, Male, 11/12/70");
        assertNotNull(record);
        assertEquals("C3PO", record.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithSpaceAsName_ShouldThrowException() {
        new Record("  , Male, 11/12/70");
        fail("Exception should have been thrown during creation of record.");

    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithMissingName_ShouldThrowException() {
        new Record("Male, 11/12/70");
        fail("Exception should have been thrown during creation of record.");
    }


    @Test
    public void record_WhenConstructed_ShouldCreateMaleGender() {
        Record record = new Record("Mike Happy, Male, 11/12/99");
        assertNotNull(record);
        assertEquals(Record.Gender.MALE, record.getGender());
    }

    @Test
    public void record_WhenConstructed_ShouldCreateFemaleGender() {
        Record record = new Record("Sally Happy, Female, 11/12/99");
        assertNotNull(record);
        assertEquals(Record.Gender.FEMALE, record.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithMissingGender_ShouldThrowException() {
        new Record("Sally Happy,, 11/12/99");
        fail("Exception should have been thrown during creation of record.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithInvalidGender_ShouldThrowException() {
        new Record("Sally Happy,Invalid Gender, 11/12/99");
        fail("Exception should have been thrown during creation of record.");
    }


    @Test
    public void record_WhenConstructed_ShouldCreateDate() {
        Record record = new Record("Mike Happy, Male, 11/12/99");
        assertNotNull(record);
        assertEquals(LocalDate.of(1999, 12, 11), record.getBirth());
    }


    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithMissingDate_ShouldThrowException() {
        new Record("Sally Happy,Male");
        fail("Exception should have been thrown during creation of record.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithInvalidDateRange_ShouldThrowException() {
        new Record("Sally Happy,Female, 99/99/99");
        fail("Exception should have been thrown during creation of record.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void record_WhenConstructedWithInvalidDate_ShouldThrowException() {
        new Record("Sally Happy, Female, iii9999");
        fail("Exception should have been thrown during creation of record.");
    }




}