package com.example.springstarbucksapi.repository;
import com.example.springstarbucksapi.model.StarbucksModel;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface StarbucksRepository extends CrudRepository<StarbucksModel, Long> {
    List<StarbucksModel> findByOrderNumber(String orderNumber) ;

}

