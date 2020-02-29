package com.example.spring.spark.springsparkfirst.service;

import com.example.spring.spark.springsparkfirst.model.DetailStatus;
import com.example.spring.spark.springsparkfirst.repository.DetailStatusRepository;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailStatusService {

    public List<DetailStatus> detailStatuses = new ArrayList<>();
    private final DetailStatusRepository detailStatusRepository;
    public Integer details = 0;
    public long startCollector = System.currentTimeMillis();
    final JavaSparkContext sc;

    public DetailStatusService(DetailStatusRepository detailStatusRepository, JavaSparkContext sc) {
        this.detailStatusRepository = detailStatusRepository;
        this.sc = sc;
    }

    public void collectDetailStatus(DetailStatus detailStatus) {
        detailStatuses.add(detailStatus);
        details++;
    }

    public long startDetailProcessing() {
        long startProcessing = System.currentTimeMillis();
        JavaRDD<DetailStatus> detailsCollection = sc.parallelize(this.detailStatuses);
        detailStatuses.clear();
        details = 0;
        startCollector = System.currentTimeMillis();
        return System.currentTimeMillis() - startProcessing;
    }
}
