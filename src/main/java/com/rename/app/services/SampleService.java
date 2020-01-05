package com.rename.app.services;

import com.rename.app.models.SampleModel;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public SampleModel getSample() {
        return SampleModel.builder()
                .name("SampleName")
                .value("SampleValue")
                .build();
    }
}
