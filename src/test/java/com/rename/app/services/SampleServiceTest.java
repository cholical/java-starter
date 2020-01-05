package com.rename.app.services;

import com.rename.app.TestNGWithSpringApplication;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
@SpringBootTest(classes = TestNGWithSpringApplication.class)
public class SampleServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SampleService sampleService;

    @BeforeClass
    public void setup() {
        log.info("TestNG setup");
    }

    @Test
    public void testGetSample() {
        log.info("Running testGetSample in SampleServiceTest");
        val sample = sampleService.getSample();
        assertEquals(sample.getName(), "SampleName");
        assertEquals(sample.getValue(), "SampleValue");
    }
}
