package com.example.jymapplication.openfeign;

import com.example.jymapplication.dto.TrainerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice", url = "http://localhost:9999")
public interface AnotherMicroserviceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/action")
    ResponseEntity<String> saveInfo(@RequestBody TrainerRequest trainerRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    ResponseEntity<String> hello();
}
