package com.ccsw.tutorial_loan.game.category;

import com.ccsw.tutorial_loan.game.category.model.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "SPRING-CLOUD-EUREKA-CLIENT-CATEGORY", url = "http://localhost:8080")
public interface CategoryClient {

    @GetMapping(value = "/category")
    List<CategoryDto> findAll();
}
