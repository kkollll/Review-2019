package com.review.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("PROVIDER-USER")
public interface HelloFeign {

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name);
}
