package com.udemy.udemy.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component // this notation is important to tell spring to use our custom formatter instead of default one
public class LocalDateFormatter implements Formatter<LocalDate> { // implements the formatter interface with the dependency of Localdate
    // NOTE:
    // so LocalDate Formatter has two default methods which are written blow
    // so we need to overRide these to method for our custom output

    private  final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM,dd,yyyy");
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException { // convert string into date format
        return null;
    }

    @Override
    public String print(LocalDate object, Locale locale) { // convert string into local date
        return dateTimeFormatter.format(object);
    }
}
