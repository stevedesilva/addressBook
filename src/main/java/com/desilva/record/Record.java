package com.desilva.record;


import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by stevedesilva .
 */
public class Record {

    private static final String REGEX_PATTERN = "^(?<name>[^,]*), (?<gender>[^,]*), (?<birth>.*)$";
    private static final Pattern PATTERN_TO_MATCH = Pattern.compile(REGEX_PATTERN);
    private static final String DATE_PATTERN = "dd/MM/";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String BIRTH = "birth";
    private static final int DATE_WIDTH = 2;
    private static final int MAX_DATE_WIDTH = 2;
    private static final int DATE_BASE_VALUE = Year.now().getValue() - 80;

    public enum Gender {MALE, FEMALE}

    private Gender gender;
    private LocalDate birth;
    private String name;


    private static final DateTimeFormatter RECORD_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern(DATE_PATTERN)
            .appendValueReduced(ChronoField.YEAR, DATE_WIDTH, MAX_DATE_WIDTH, DATE_BASE_VALUE)
            .toFormatter();

    public Record(String row) {
        final Matcher matcher = PATTERN_TO_MATCH.matcher(row);
        if (matcher.find()) {
            name = matcher.group(NAME);
            gender = Gender.valueOf(matcher.group(GENDER).toUpperCase());
            try {
                birth = LocalDate.parse(matcher.group(BIRTH), RECORD_DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(String.format("Error: Date field cannot be parsed. Row(%s)", row));
            }
        } else {
            throw new IllegalArgumentException(row);
        }

        if ((name == null) || (name.isEmpty() || name.trim().isEmpty())) {
            throw new IllegalArgumentException(String.format("Error: Name field cannot be empty. Row(%s)", row));
        }

    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (gender != record.gender) return false;
        if (!birth.equals(record.birth)) return false;
        return name.equals(record.name);
    }

    @Override
    public int hashCode() {
        int result = gender.hashCode();
        result = 31 * result + birth.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "gender=" + gender +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                '}';
    }
}