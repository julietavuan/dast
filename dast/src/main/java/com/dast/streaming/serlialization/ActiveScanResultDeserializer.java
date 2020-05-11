package com.dast.streaming.serlialization;

import com.dast.model.ActiveScan;
import com.dast.model.ScanningResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class ActiveScanResultDeserializer implements Deserializer {
    @Override
    public Object deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ScanningResponse scanningResponse = null;
        try {
            scanningResponse = mapper.readValue(data, new TypeReference<ScanningResponse>(){});
        } catch (Exception e) {

            e.printStackTrace();
        }
        return scanningResponse;
    }

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public void close() {

    }
}
