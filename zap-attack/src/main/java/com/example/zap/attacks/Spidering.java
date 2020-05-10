package com.example.zap.attacks;

import org.zaproxy.clientapi.core.ApiResponse;

import java.util.List;

public interface Spidering {
    List<ApiResponse> spidering(String url);
}
