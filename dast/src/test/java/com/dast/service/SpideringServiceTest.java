package com.dast.service;

import com.dast.model.Scanning;
import com.dast.model.ScanningState;
import com.dast.repository.ScanningRepository;
import com.dast.streaming.model.ScanningResponse;
import com.dast.streaming.model.ScanningResponseState;
import com.dast.streaming.publisher.StreamingPublisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(SpideringService.class)
public class SpideringServiceTest {

    @Autowired
    private SpideringService spideringService;

    @MockBean
    private ScanningRepository scanningRepository;

    @MockBean
    private StreamingPublisher streamingPublisher;

    @Test
    public void publishNew(){
        String url = "sarasa";
        Mockito
                .when(scanningRepository.findByUrl(url)).thenReturn(null);
        Scanning scanning = spideringService.publish(url);
        assertEquals(scanning.getUrl(),url);
        assertEquals(scanning.getState(),ScanningState.PROCESSING.toString());
    }

    @Test
    public void publishOld(){
        String url = "sarasa";
        Scanning scanning = new Scanning(url);
        LocalDate date = LocalDate.now().minusDays(8);
        scanning.setTime(date);
        scanning.setStateToDone();
        Mockito
                .when(scanningRepository.findByUrl(url)).thenReturn(scanning);
        Scanning scanningResponse = spideringService.publish(url);
        assertEquals(ScanningState.PROCESSING.toString(),scanningResponse.getState());
    }

    @Test
    public void publishFailed(){
        String url = "sarasa";
        Scanning scanning = new Scanning(url);
        LocalDate date = LocalDate.now().minusDays(8);
        scanning.setTime(date);
        scanning.setStateToFail();
        Mockito
                .when(scanningRepository.findByUrl(url)).thenReturn(scanning);
        Scanning scanningResponse = spideringService.publish(url);
        assertEquals(ScanningState.PROCESSING.toString(),scanningResponse.getState());
    }

    @Test
    public void updateScanningOk(){
        String url="https://public-firing-range.appspot.com";
        ScanningResponse scanningResponse = new ScanningResponse(url);
        scanningResponse.setState(ScanningResponseState.FAIL);
        spideringService.updateScanning(scanningResponse);
    }

    @Test
    public void updateScanningToDone(){
        String url="https://public-firing-range.appspot.com";
        ScanningResponse scanningResponse = new ScanningResponse(url);
        scanningResponse.setState(ScanningResponseState.SUCCESS);
        spideringService.updateScanning(scanningResponse);

    }
}
