package com.example.timesheet.service;

import com.example.timesheet.core.enumeration.Flag;
import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.core.repository.IDailyTimeSheetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.TimeSheetConstants.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetServiceTests {
    @Mock
    private IDailyTimeSheetRepository dailyTimeSheetRepository;

    @InjectMocks
    private TimeSheetService timeSheetService;

    @Before
    public void setUp(){
        when(dailyTimeSheetRepository.getDailyTimeSheets(any())).thenReturn(Arrays.asList(
                new DailyTimeSheet(DATE_1, HOURS_PER_DAY_1),
                new DailyTimeSheet(DATE_2, HOURS_PER_DAY_2),
                new DailyTimeSheet(DATE_3, HOURS_PER_DAY_3),
                new DailyTimeSheet(DATE_4, HOURS_PER_DAY_4)
                )
        );
    }

    @Test
    public void Should_ReturnTenDailyTimeSheets_ForGivenDateRange(){
        TimeSheetRange range = new TimeSheetRange(FROM, TO, TEAM_MEMBER_ID);
        TimeSheet timeSheet = timeSheetService.getTimeSheet(range);

        assertEquals(10, timeSheet.getDailyTimeSheets().size());
        assertTrue(timeSheet.getTotalHours() == TOTAL_HOURS);
        verify(dailyTimeSheetRepository, times(1)).getDailyTimeSheets(any());
        verifyNoMoreInteractions(dailyTimeSheetRepository);
    }

    @Test
    public void Should_ReturnTwoFulfilled_TwoUnfulfilled_SixNotFilled_DailyTimeSheets_ForGivenDateRange(){
        TimeSheetRange range = new TimeSheetRange(FROM, TO, TEAM_MEMBER_ID);
        TimeSheet timeSheet = timeSheetService.getTimeSheet(range);

        List<DailyTimeSheet> fulfilledDailyTimeSheets = timeSheet.getDailyTimeSheets().stream()
                .filter(element -> element.getFlag().equals(Flag.FULFILLED))
                .toList();
        List<DailyTimeSheet> unfulfilledDailyTimeSheets = timeSheet.getDailyTimeSheets().stream()
                .filter(element -> element.getFlag().equals(Flag.UNFULFILLED))
                .toList();
        List<DailyTimeSheet> notFilledDailyTimeSheets = timeSheet.getDailyTimeSheets().stream()
                .filter(element -> element.getFlag().equals(Flag.NOT_FILLED))
                .toList();

        assertEquals(2, fulfilledDailyTimeSheets.size());
        assertEquals(2, unfulfilledDailyTimeSheets.size());
        assertEquals(6, notFilledDailyTimeSheets.size());
    }

}
