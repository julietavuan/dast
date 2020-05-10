package com.example.zap.attacks;

import org.zaproxy.clientapi.core.ApiResponse;

public interface Scanner {
    String scan(ApiResponse url);


}
