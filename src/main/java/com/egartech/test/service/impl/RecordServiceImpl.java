package com.egartech.test.service.impl;

import com.egartech.test.model.Record;
import com.egartech.test.repository.RecordRepository;
import com.egartech.test.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Record addRecord(Record record) {
        Record savedRecord = recordRepository.saveAndFlush(record);
        return savedRecord;
    }

    @Override
    public void delete(Integer id) {
        recordRepository.deleteById(id);
    }

    @Override
    public List<Record> getAllByName(String companyName) {
        return recordRepository.getAllByName(companyName);
    }

    @Override
    public Record update(Record record) {
        return recordRepository.saveAndFlush(record);
    }

    @Override
    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    @Override
    public List<String> getUniqNames() {
        return recordRepository.findUniqName();
    }

    public Record getById(Integer id) {
        var record = recordRepository.findById(id);
        return record.isEmpty() ? null : record.get();
    }
}
