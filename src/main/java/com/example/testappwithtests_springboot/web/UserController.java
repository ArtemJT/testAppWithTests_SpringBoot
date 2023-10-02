package com.example.testappwithtests_springboot.web;

import com.example.testappwithtests_springboot.service.fillDataBase.LoaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoaderController {

    private final LoaderService loaderService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("users/generate/{amount}")
    public void fillDataBase(@PathVariable int amount) {
        log.info("fillDataBase() LoaderController - start: ");
        loaderService.generateData(amount);
        log.info("fillDataBase() LoaderController - end: amount = {}", amount);
    }
}
