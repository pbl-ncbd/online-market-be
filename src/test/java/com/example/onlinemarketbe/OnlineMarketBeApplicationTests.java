package com.example.onlinemarketbe;

import com.example.onlinemarketbe.service.ItemServiceTests;
import org.junit.internal.TextListener;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OnlineMarketBeApplication.class)
class OnlineMarketBeApplicationTests {

    @Test
    void  contextLoads() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        Result result = junit.run(
                ItemServiceTests.class

        );
        resultReport(result);
    }
    public static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }

}
