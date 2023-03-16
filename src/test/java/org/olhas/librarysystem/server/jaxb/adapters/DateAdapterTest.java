package org.olhas.librarysystem.server.jaxb.adapters;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateAdapterTest {

    DateAdapter dateAdapter = new DateAdapter();

    @Test
    void testUnmarshal() {
        LocalDate date = dateAdapter.unmarshal("2022-02-10");
        assertEquals(LocalDate.of(2022, 2, 10), date);
    }

    @Test
    void textMarshal() {
        String date = dateAdapter.marshal(LocalDate.of(2023, 3, 10));
        assertEquals(date, "2023-03-10");
    }
}