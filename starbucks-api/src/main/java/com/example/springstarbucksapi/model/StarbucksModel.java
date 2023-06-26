package com.example.springstarbucksapi.model;

import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "orders", indexes=@Index(name = "altIndex", columnList = "orderNumber", unique = true))
@Data
@RequiredArgsConstructor
public class StarbucksModel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String orderNumber ;
    private String status ;

}