package com.example.spring.spark.springsparkfirst.controller;

import com.example.spring.spark.springsparkfirst.model.DetailStatus;
import com.example.spring.spark.springsparkfirst.service.DetailStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/detail")
public class DetailStatusController {

    private final DetailStatusService service;

    public DetailStatusController(DetailStatusService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> detailProcessing(@RequestBody DetailStatus detailStatus) {
        long workDuration = System.currentTimeMillis() - service.startCollector;
        Integer detailLimit = 10;
        long workDurationLimit = 60000;
        if(service.details < detailLimit || workDuration < workDurationLimit) {
            service.collectDetailStatus(detailStatus);
            return new ResponseEntity<>("collected", HttpStatus.OK);
        } else {
            long processingDuration = service.startDetailProcessing();
            service.collectDetailStatus(detailStatus);
            return new ResponseEntity<>("Processing duration: " + processingDuration, HttpStatus.OK);
    }

}
}
