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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@WebMvcTest(SpideringController.class)
public class SpideringControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpideringService service;


/*    @Test
    public void testInValidUrl()throws Exception{
        String invalidUrl="abc";
        Boolean isValidUrl=isValidUrl(invalidUrl);
        assertFalse(isValidUrl);
    }

    @Test
    public void testInValidUrlEmpty()throws Exception{
        String invalidUrl="";
        Boolean isValidUrl=isValidUrl(invalidUrl);
        assertFalse(isValidUrl);
    }

    @Test
    public void testValidUrl()throws Exception{
        String validUrl="http://localhost:8080";
        Boolean isValidUrl=isValidUrl(validUrl);
        assertTrue(isValidUrl);
    }


    @Test
    public void testGetUrlFromRequest()throws Exception{
        String request=getRequestAsString("requests/okRequest");
        String url=getUrlFromRequest(request);
        assertEquals("http://localhost/setup.php", url);

    }

    @Test
    public void testInValidId()throws Exception{
        String id="12";
        Boolean isValid=DastUtil.validateId(id);
        assertFalse(isValid);
    }

    @Test
    public void testValidId()throws Exception{
        //String id=UUID.randomUUID().toString();
        Boolean isValid=DastUtil.validateId(id);
        assertTrue(isValid);
    }*/
}
