package com.egartech.test.repository;

import com.egartech.test.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query(value = "SELECT DISTINCT COMPANY_NAME FROM RECORD",
            nativeQuery = true)
    List<String> findUniqName();

    @Query("SELECT r FROM Record r WHERE r.companyName = :companyName ORDER BY r.date ASC")
    List<Record> getAllByName(String companyName);

}
