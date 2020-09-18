package com.egartech.test.model;

import com.egartech.test.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordTest {
    @Autowired
    private RecordService recordService;

    @Test
    void addNewRecordDate() {
        var record = new Record();
        record.setDate(new Date(System.currentTimeMillis()));
        record.setCompanyName("Автоваз");
        record.setPrice(100.100000000005d);
        var newRecord = recordService.addRecord(record);
        System.out.println(newRecord.getPrice());
        var listRecords = recordService.getAll();
        System.out.println(listRecords);
    }
}