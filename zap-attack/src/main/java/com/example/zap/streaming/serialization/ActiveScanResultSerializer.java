package com.example.zap.streaming.serialization;


import com.example.zap.model.ActiveScanResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.List;


public class ActiveScanResultSerializer implements Serializer<List<ActiveScanResponse>> {
    @Override
    public byte[] serialize(String topic, List<ActiveScanResponse> data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}
