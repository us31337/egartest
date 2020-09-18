package com.egartech.test.service;

import com.egartech.test.model.Record;

import java.util.List;

public interface RecordService {
    Record addRecord(Record record);
    void delete(Integer id);
    List<Record> getAllByName(String name);
    Record update(Record record);
    List<Record> getAll();
    List<String> getUniqNames();
    Record getById(Integer id);
}
