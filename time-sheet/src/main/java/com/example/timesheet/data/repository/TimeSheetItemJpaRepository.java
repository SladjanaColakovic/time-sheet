package com.example.timesheet.data.repository;

import com.example.timesheet.core.model.ReportSearch;
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
            "( (coalesce(:#{#reportSearch.startDate}, null) is null AND coalesce(:#{#reportSearch.endDate}, null) is null) OR " +
            "( (coalesce(:#{#reportSearch.startDate}, null) is not null AND i.date >= :#{#reportSearch.startDate}) OR " +
            "(coalesce(:#{#reportSearch.endDate}, null) is not null AND i.date <= :#{#reportSearch.endDate})))")
    List<TimeSheetItemEntity> reportSearch(ReportSearch reportSearch);
}
