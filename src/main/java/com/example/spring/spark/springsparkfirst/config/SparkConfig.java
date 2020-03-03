package com.example.spring.spark.springsparkfirst.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Value("Spring Spark first")
    private String appName;
    @Value("local[*]")
    private String masterUri;

    @Bean
    public SparkConf conf() {
        return new SparkConf()
                .setAppName(appName)
                .setMaster(masterUri)
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
    }

    @Bean
    public JavaSparkContext sc() {
        return new JavaSparkContext(conf());
    }

}
