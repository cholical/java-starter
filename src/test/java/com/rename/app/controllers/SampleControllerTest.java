package com.rename.app.controllers;

import com.rename.app.models.SampleModel;
import com.rename.app.services.SampleService;
import com.rename.app.TestNGWithSpringApplication;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.testng.Assert.assertEquals;

@Slf4j
@SpringBootTest(classes = TestNGWithSpringApplication.class)
public class SampleControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SampleService sampleService;

    @Mock
    private HttpServletRequest request;

    @Autowired
    @InjectMocks
    private SampleController sampleController;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSample() {
        log.info("Running testGetSample in SampleControllerTest");
        String testName = "MockName";
        String testValue = "MockValue";
        when(sampleService.getSample()).thenReturn(SampleModel.builder()
                .name(testName)
                .value(testValue)
                .build());

        ResponseEntity<SampleModel> response = sampleController.getSample(request);

        verify(sampleService, times(1)).getSample();
        assertEquals(Objects.requireNonNull(response.getBody()).getName(), testName);
        assertEquals(Objects.requireNonNull(response.getBody()).getValue(), testValue);
    }
}
