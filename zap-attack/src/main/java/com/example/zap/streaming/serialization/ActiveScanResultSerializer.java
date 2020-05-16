package com.example.zap.streaming.serialization;

import com.example.zap.model.ScanningResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;



public class ActiveScanResultSerializer implements Serializer<ScanningResponse> {
    @Override
    public byte[] serialize(String topic, ScanningResponse data) {
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
