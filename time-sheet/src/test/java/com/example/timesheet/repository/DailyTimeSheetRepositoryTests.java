package com.example.timesheet.repository;

import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.data.impl.DailyTimeSheetRepository;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
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
import static com.example.timesheet.constants.TimeSheetConstants.HOURS_PER_DAY_4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyTimeSheetRepositoryTests {

    @Mock
    private TimeSheetItemJpaRepository timeSheetItemJpaRepository;

    @InjectMocks
    private DailyTimeSheetRepository dailyTimeSheetRepository;

    @Before
    public void setUp(){
        when(timeSheetItemJpaRepository.getDailyTimeSheets(any())).thenReturn(Arrays.asList(
                        new DailyTimeSheet(DATE_1, HOURS_PER_DAY_1),
                        new DailyTimeSheet(DATE_2, HOURS_PER_DAY_2),
                        new DailyTimeSheet(DATE_3, HOURS_PER_DAY_3),
                        new DailyTimeSheet(DATE_4, HOURS_PER_DAY_4)
                )
        );
    }

    @Test
    public void Should_ReturnFourDailyTimeSheets_ForGivenDateRange(){
        TimeSheetRange range = new TimeSheetRange(FROM, TO, TEAM_MEMBER_ID);
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(range);

        assertEquals(4, dailyTimeSheets.size());
        assertEquals(DATE_1, dailyTimeSheets.get(0).getDate());
        assertTrue(HOURS_PER_DAY_1 == dailyTimeSheets.get(0).getTotalHoursPerDay());
        verify(timeSheetItemJpaRepository, times(1)).getDailyTimeSheets(any());
        verifyNoMoreInteractions(timeSheetItemJpaRepository);
    }
}
