package com.example.spring.spark.springsparkfirst.service;

import com.example.spring.spark.springsparkfirst.model.DetailStatus;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailStatusService {

    public List<DetailStatus> detailStatuses = new ArrayList<>();
    public Integer details = 0;
    public long startCollector = System.currentTimeMillis();
    final JavaSparkContext sc;

    public DetailStatusService(JavaSparkContext sc) {
        this.sc = sc;
    }

    public void collectDetailStatus(DetailStatus detailStatus) {
        detailStatuses.add(detailStatus);
        details++;
        System.out.println(details);
    }

    public JavaRDD<DetailStatus> processingDetailStatuses() throws Exception {
        long processingLimitDuration = 60000;
        long startProcessing = System.currentTimeMillis();
        JavaRDD<DetailStatus> rdd = sc.parallelize(this.detailStatuses).persist(StorageLevel.MEMORY_AND_DISK());
        detailStatuses.clear();
        details = 0;
        startCollector = System.currentTimeMillis();
        if (System.currentTimeMillis() - startProcessing > processingLimitDuration) {
            throw new Exception("Processing time limit exceeded");
        }
        return rdd;
    }
}
