package com.example.zap.attacks;

import com.example.zap.model.ResponseScanning;
import org.zaproxy.clientapi.core.ApiResponse;

public interface Scanner {
    ResponseScanning scan(ApiResponse url);


}
