package com.example.zap.attacks;

import com.example.zap.model.ActiveScan;
import org.zaproxy.clientapi.core.ApiResponse;

public interface Scanner {
    ActiveScan scan(ApiResponse url);


}
