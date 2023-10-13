package com.example.timesheet.data.repository;

import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSheetItemJpaRepository extends JpaRepository<TimeSheetItemEntity, Long> {

    @Query("select i from TimeSheetItemEntity i where (:#{#reportSearch.clientId} is null OR i.project.client.id = :#{#reportSearch.clientId}) AND" +
            " (:#{#reportSearch.projectId} is null OR i.project.id = :#{#reportSearch.projectId}) AND" +
            " (:#{#reportSearch.categoryId} is null OR i.category.id = :#{#reportSearch.categoryId}) AND " +
            " (:#{#reportSearch.teamMemberId} is null OR i.teamMember.id = :#{#reportSearch.teamMemberId}) AND " +
            " (coalesce(:#{#reportSearch.startDate}, null) is null OR i.date >= :#{#reportSearch.startDate}) AND " +
            " (coalesce(:#{#reportSearch.endDate}, null) is null OR i.date <= :#{#reportSearch.endDate})")
    List<TimeSheetItemEntity> reportSearch(ReportSearch reportSearch);
   @Query("select new com.example.timesheet.core.model.DailyTimeSheet(i.date, sum(i.time), sum(i.overtime)) " +
           "from TimeSheetItemEntity i where i.teamMember.id = :#{#timeSheetRange.teamMemberId} AND" +
           " i.date between :#{#timeSheetRange.from} AND :#{#timeSheetRange.to} group by i.date")
   List<DailyTimeSheet> getDailyTimeSheets(TimeSheetRange timeSheetRange);

}
