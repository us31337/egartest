package com.egartech.test.controller;

import com.egartech.test.model.Record;
import com.egartech.test.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class Main {
    @Autowired
    private RecordService recordService;

    @Value("${default.date.format}")
    private String dateFormat;

    @GetMapping("/listallrecords")
    public List<Record> getAllRecords() {
        return recordService.getAll();
    }

    @PostMapping("/addnewrecord")
    public ResponseEntity addNewRecord(@RequestParam String companyName,
                                       @RequestParam String date,
                                       @RequestParam String price) {
        var record = new Record();
        if (companyName.length() > 0) {
            record.setCompanyName(companyName);
        } else {
            return new ResponseEntity("Слишком короткое название", HttpStatus.BAD_REQUEST);
        }
        try {
            var sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = sdf.parse(date);
            record.setDate(newDate);
        } catch (Exception e) {
            return new ResponseEntity("Дата введена неверно", HttpStatus.BAD_REQUEST);
        }
        try {
            var newPrice = Double.parseDouble(price);
            record.setPrice(newPrice);
        } catch (Exception e) {
            return new ResponseEntity("Цена введена неверно", HttpStatus.BAD_REQUEST);
        }
        recordService.addRecord(record);
        return new ResponseEntity("Новая запись добавлена", HttpStatus.OK);
    }

    @PostMapping("/delrecord")
    public ResponseEntity delRecord(@RequestParam String id) {
        int num = 0;
        try {
            num = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity("Не найдена запись с ID " + id, HttpStatus.BAD_REQUEST);
        }
        recordService.delete(num);
        return new ResponseEntity("Запись успешно удалена", HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateRecord(@PathVariable Integer id,
                                       @RequestParam String type,
                                       @RequestParam String value) {
        var record = recordService.getById(id);
        if (record.equals(null)) return new ResponseEntity("Не найден id = " + id, HttpStatus.BAD_REQUEST);

        switch (type) {
            case "date":
                var sdf = new SimpleDateFormat(dateFormat);
                try {
                    Date newDate = sdf.parse(value);
                    record.setDate(newDate);
                } catch (ParseException e) {
                    return new ResponseEntity("Дата введена неверно", HttpStatus.BAD_REQUEST);
                }
                break;
            case "price":
                try {
                    var newDelimiter = value.replace(',', '.');
                    var newPrice = Double.parseDouble(newDelimiter);
                    record.setPrice(newPrice);
                } catch (Exception e) {
                    return new ResponseEntity("Цена введена неверно", HttpStatus.BAD_REQUEST);
                }
                break;
            case "companyName":
                record.setCompanyName(value);
                break;
            default:
                return new ResponseEntity("Некорректное поле " + type, HttpStatus.BAD_REQUEST);
        }
        recordService.update(record);
        return new ResponseEntity("Успешно обновлена запись " + id, HttpStatus.OK);
    }

}
