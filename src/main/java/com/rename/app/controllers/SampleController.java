package com.rename.app.controllers;

import com.rename.app.models.SampleModel;
import com.rename.app.services.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private String stringBean;

    @GetMapping("/**")
    public @ResponseBody String getStringBean(HttpServletRequest request) {
        return stringBean;
    }

    @GetMapping("/getSample")
    public @ResponseBody ResponseEntity<SampleModel> getSample(HttpServletRequest request) {
        log.info("/getSample called.");
        return ResponseEntity.status(200).body(sampleService.getSample());
    }
}
