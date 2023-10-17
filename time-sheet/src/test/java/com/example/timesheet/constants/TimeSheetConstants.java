package com.example.timesheet.constants;

import com.example.timesheet.core.enumeration.Flag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class TimeSheetConstants {
    public static final LocalDate FROM = LocalDate.parse("2023-10-10");
    public static final LocalDate TO = LocalDate.parse("2023-10-20");
    public static final Long TEAM_MEMBER_ID = 1L;

    public static final LocalDate DATE_1 = LocalDate.parse("2023-10-12");
    public static final LocalDate DATE_2 = LocalDate.parse("2023-10-13");
    public static final LocalDate DATE_3 = LocalDate.parse("2023-10-14");
    public static final LocalDate DATE_4 = LocalDate.parse("2023-10-15");
    public static final LocalDate DATE_5 = LocalDate.parse("2023-10-16");
    public static final double HOURS_PER_DAY_1 = 7.5;
    public static final double HOURS_PER_DAY_2 = 4.5;
    public static final double HOURS_PER_DAY_3 = 8.5;
    public static final double HOURS_PER_DAY_4 = 5.5;
    public static final double HOURS_PER_DAY_5 = 0.0;

    public static final Flag FLAG_1 = Flag.FULFILLED;
    public static final Flag FLAG_2 = Flag.UNFULFILLED;
    public static final Flag FLAG_3 = Flag.FULFILLED;
    public static final Flag FLAG_4 = Flag.UNFULFILLED;
    public static final Flag FLAG_5 = Flag.NOT_FILLED;
    public static final String FLAG = "FULFILLED";
    public static final double TOTAL_HOURS = 26.0;


    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
