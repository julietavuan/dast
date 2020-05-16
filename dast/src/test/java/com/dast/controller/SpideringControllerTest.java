package com.dast.controller;

import com.dast.dao.RequestScanning;
import com.dast.exception.NoScanningFoundException;
import com.dast.model.ActiveScan;
import com.dast.model.Scanning;
import com.dast.service.SpideringService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SpideringController.class)
public class SpideringControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public SpideringService service;

    @Test
    public void testInvalidUrl()throws Exception{
        RequestScanning  url= new RequestScanning("abc");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/dast/scanning")
                .content(mapper.writeValueAsString(url))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testValidUrl()throws Exception{
        RequestScanning  url= new RequestScanning("https://public-firing-range.appspot.com/");
        Scanning scanning = new Scanning(url.getUrl());
        scanning.setId(new ObjectId());
        Mockito
                .when(service.publish(url.getUrl())).thenReturn(scanning);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/dast/scanning")
                .content(mapper.writeValueAsString(url))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetScanningResultOk()throws Exception{
        String idScan= "987823lk23";
        Scanning scanning = new Scanning("url");
        scanning.setId(new ObjectId());
        scanning.setActiveScanResponses(new ArrayList<ActiveScan>());
        scanning.setStateToDone();
        Mockito
                .when(service.getSpideringResult(idScan)).thenReturn(scanning);
        mockMvc.perform(MockMvcRequestBuilders.get("/dast/spiderings/{spidering-id}/results", idScan)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
    @Test
    public void testGetScanningResultException()throws Exception{
        String idScan= "aaaa";
        Mockito
                .when(service.getSpideringResult(idScan)).thenThrow(NoScanningFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/dast/spiderings/{spidering-id}/results", idScan)
                )
                .andExpect(status().isNotFound());
    }




}
