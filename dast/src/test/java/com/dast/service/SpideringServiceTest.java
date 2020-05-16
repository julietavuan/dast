package com.dast.service;

import com.dast.model.Scanning;
import com.dast.model.ScanningState;
import com.dast.repository.ScanningRepository;
import com.dast.streaming.model.ScanningResponse;
import com.dast.streaming.model.ScanningResponseState;
import com.dast.streaming.publisher.StreamingPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

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
    public void updateScanningFail(){
        Scanning scanning = new Scanning("url");
        ScanningResponse scanningResponse = new ScanningResponse();
        scanningResponse.setUrl("url");
        scanningResponse.setState(ScanningResponseState.FAIL);
        Mockito
                .when(scanningRepository.findByUrl("url")).thenReturn(scanning);
        spideringService.updateScanning(scanningResponse);
        assertEquals(ScanningResponseState.FAIL.toString(),scanning.getState());
    }

    @Test
    public void updateScanningSuccess(){
        Scanning scanning = new Scanning("url");
        ScanningResponse scanningResponse = new ScanningResponse();
        scanningResponse.setUrl("url");
        scanningResponse.setState(ScanningResponseState.SUCCESS);
        Mockito
                .when(scanningRepository.findByUrl("url")).thenReturn(scanning);
        spideringService.updateScanning(scanningResponse);
        assertEquals(ScanningResponseState.SUCCESS.toString(),scanning.getState());
    }

    @Test
    public void failPublishScan(){
        Scanning scanning = new Scanning("url");
        Mockito
                .when(scanningRepository.findByUrl("url")).thenReturn(scanning);
        spideringService.failPublishScan("url");
        assertEquals(ScanningResponseState.FAIL.toString(),scanning.getState());
    }
}
