package com.example.timesheet.controller;

import com.example.timesheet.app.dto.TimeSheetRangeDTO;
import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.service.TimeSheetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static com.example.timesheet.constants.TimeSheetConstants.*;
import static com.example.timesheet.constants.TimeSheetConstants.HOURS_PER_DAY_4;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasItem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSheetControllerTests {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TimeSheetService timeSheetService;


    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        when(timeSheetService.getTimeSheet(any())).thenReturn(new TimeSheet(Arrays.asList(
                        new DailyTimeSheet(DATE_1, HOURS_PER_DAY_1, FLAG_1),
                        new DailyTimeSheet(DATE_2, HOURS_PER_DAY_2, FLAG_2),
                        new DailyTimeSheet(DATE_3, HOURS_PER_DAY_3, FLAG_3),
                        new DailyTimeSheet(DATE_4, HOURS_PER_DAY_4, FLAG_4),
                        new DailyTimeSheet(DATE_5, HOURS_PER_DAY_5, FLAG_5)
                ), TOTAL_HOURS)
        );
    }

    @Test
    @WithMockUser(username = "nikola10", roles = {"WORKER"})
    public void Should_ReturnStatusOK_ForGivenDateRange() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/timeSheet")
                        .content(asJsonString(new TimeSheetRangeDTO(FROM, TO, TEAM_MEMBER_ID)))
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dailyTimeSheets").exists())
                .andExpect(jsonPath("$.totalHours").exists())
                .andExpect(jsonPath("$.dailyTimeSheets", hasSize(5)))
                .andExpect(jsonPath("$.totalHours").value(TOTAL_HOURS))
                .andExpect(jsonPath("$.dailyTimeSheets[0].totalHoursPerDay").value(HOURS_PER_DAY_1))
                .andExpect(jsonPath("$.dailyTimeSheets[0].flag").value(FLAG))
                .andExpect(jsonPath("$.dailyTimeSheets[*].totalHoursPerDay").value(hasItem(HOURS_PER_DAY_5)));

    }


}
