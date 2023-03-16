package org.olhas.librarysystem.server.jaxb.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

/**
 * An adapter for {@link LocalDate} class.
 * Since JAXB know only common data types like strings and integer numbers
 * out-of-the-box, this adapter is used to convert LocalDate to String and
 * vice versa depending on the operation being performed.
 *
 * @author Olha
 * @version 1.0
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String date) {
        return LocalDate.parse(date);
    }

    @Override
    public String marshal(LocalDate date)  {
        return date.toString();
    }
}

