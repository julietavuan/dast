package com.example.zap.attacks;

import com.example.zap.model.ActiveScanResponse;
import com.example.zap.model.Site;
import org.zaproxy.clientapi.core.ApiResponse;

public interface Scanner {
    ActiveScanResponse scan(ApiResponse url);


}
