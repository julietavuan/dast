package com.dast.service;

import com.dast.model.Scanning;
import com.dast.repository.ScanningRepository;
import com.dast.streaming.model.ScanningResponse;
import com.dast.streaming.model.ScanningResponseState;
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

@RunWith(SpringRunner.class)
@WebMvcTest(SpideringService.class)
public class SpideringServiceTest {

    @TestConfiguration
    static class SpideringServiceImplTestContextConfiguration {

        @Bean
        public SpideringService spideringService() {
            return new SpideringService();
        }
    }

    @Autowired
    private SpideringService spideringService;

    @MockBean
    private ScanningRepository scanningRepository;

    @Test
    public void notPublish(){}

    public void publishOk(){}


    @Test
    public void getSpideringResultOk(String spideringId){
        Scanning scanning = spideringService.getSpideringResult("29424093");
    }

    @Test
    public void getSpideringResultWrong(String spideringId){}
    @Before
    public void setUp() {
        Scanning scanning = new Scanning("https://public-firing-range.appspot.com");
        String url = "https://public-firing-range.appspot.com";
        Mockito.when(scanningRepository.findByUrl(url))
                .thenReturn(scanning);
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

    @Test
    public void failPublishScanWrong(){
        String url="alskdj";
        spideringService.failPublishScan(url);
    }

    @Test
    public void failPublishScanOk(){
        String url="https://public-firing-range.appspot.com";
        spideringService.failPublishScan(url);
    }
}
