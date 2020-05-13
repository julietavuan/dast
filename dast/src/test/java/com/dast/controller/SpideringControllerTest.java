package com.dast.controller;

import com.dast.model.Scanning;
import com.dast.service.SpideringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpideringController.class)
public class SpideringControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpideringService service;
/*

    @Test
    public void testScanningOk() throws Exception {
        String url = "https://public-firing-range.appspot.com";
        mockMvc.perform(post("scanning").contentType(MediaType.APPLICATION_JSON)
                .content(url)).andExpect(status().isAccepted());

    }
    @Test
    public void testScanningResultsOk() throws Exception {
        Scanning scanning = new Scanning()
        mockMvc.perform(get("/dast/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
*/


}
