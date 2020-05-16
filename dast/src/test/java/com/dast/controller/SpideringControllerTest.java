package com.dast.controller;

import com.dast.dao.RequestScanning;
import com.dast.model.Scanning;
import com.dast.service.SpideringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SpideringController.class)
public class SpideringControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpideringController spideringController;

    @MockBean
    public SpideringService service;

    @MockBean
    public DastResponse dastResponse;

    @Test
    public void testInvalidUrl()throws Exception{
        RequestScanning  url= new RequestScanning("abc");
        mockMvc.perform(post("/dast/scanning", url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testValidUrl()throws Exception{
        RequestScanning  url= new RequestScanning("https://public-firing-range.appspot.com/");
        Mockito
                .when(service.publish(url.getUrl())).thenReturn(new Scanning(url.getUrl()));
        mockMvc.perform(post("/scanning", url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }


    @Test
    public void testGetScanningResultWrong()throws Exception{
        String idScan= "aaaa";
        mockMvc.perform(get("/spiderings/"+idScan)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetScanningResultOk()throws Exception{
        String idScan= "987823lk23";
        Mockito
                .when(service.getSpideringResult(idScan)).thenReturn(new Scanning());
        mockMvc.perform(get("/spiderings/"+idScan)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }


}
