package com.dast.streaming.serlialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class AnalysisResultDeserializer implements Deserializer {
    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        List<AnalysisResult> analysisResults = null;
        try {
            analysisResults = mapper.readValue(data, new TypeReference<List<AnalysisResult>>(){});
        } catch (Exception e) {

            e.printStackTrace();
        }
        return analysisResults;
    }

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    @Override
    public void close() {

    }
}
