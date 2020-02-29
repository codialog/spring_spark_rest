package com.example.spring.spark.springsparkfirst.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "detail_status")
public class DetailStatus extends BaseEntity {

    @Column(name = "id_sample")
    private String id_sample;

    @Column(name = "num_id")
    private String num_id;

    @Column(name = "id_location")
    private String id_location;

    @Column(name = "id_signal_par")
    private String id_signal_par;

    @Column(name = "id_detected")
    private String id_detected;

    @Column(name = "id_class_det")
    private String id_class_det;
}
