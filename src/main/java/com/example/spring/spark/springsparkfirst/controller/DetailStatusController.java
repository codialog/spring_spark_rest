package com.example.spring.spark.springsparkfirst.controller;

import com.example.spring.spark.springsparkfirst.model.DetailStatus;
import com.example.spring.spark.springsparkfirst.service.DetailStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/detail")
public class DetailStatusController {

    private final DetailStatusService service;

    public DetailStatusController(DetailStatusService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> detailProcessing(@RequestBody DetailStatus detailStatus) throws Exception {
        long workDuration = System.currentTimeMillis() - service.startCollector;
        Integer detailLimit = 100000;
        long workDurationLimit = 120000;
        if (service.details >= detailLimit || workDuration >= workDurationLimit) {
            service.processingDetailStatuses();
        }
        service.collectDetailStatus(detailStatus);
        return new ResponseEntity<>("collected", HttpStatus.OK);
    }
}
