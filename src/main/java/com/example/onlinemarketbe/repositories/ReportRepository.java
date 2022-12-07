package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.dto.ReportDto;
import com.example.onlinemarketbe.model.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Modifying
    @Query(value = "INSERT INTO report (reason, status, user_id_report, user_id_reported) VALUES (:reason, false, :user_id_report, :user_id_reported)", nativeQuery = true)
    void createReport(@Param("user_id_report") int reportId, @Param("user_id_reported") int reportedId, @Param("reason") String reason);

    @Query("SELECT r FROM Report r ORDER BY r.createAt ASC")
    List<Report> findAllReport(Pageable pageable);

}
