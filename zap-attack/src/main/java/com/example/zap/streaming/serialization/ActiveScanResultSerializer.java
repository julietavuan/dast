package com.example.zap.streaming.serialization;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class ActiveScanResultSerializer implements Serializer {
    @Override
    public byte[] serialize(String topic, Object data) {
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
    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
