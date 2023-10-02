package com.example.testappwithtests_springboot.web;

import com.example.testappwithtests_springboot.service.filldb.LoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoaderController {

    private final LoaderService loaderService;

    @PostMapping("users/generate/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public void fillDataBase(@PathVariable int amount) {
        loaderService.generateData(amount);
    }
}
