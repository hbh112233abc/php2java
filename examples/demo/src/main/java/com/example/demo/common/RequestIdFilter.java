package com.example.demo.common;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class RequestIdFilter  implements  Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        String traceId = UUID.randomUUID().toString();
        String requestId = UUID.randomUUID().toString();
        // MDC.put("traceId", traceId);
        MDC.put("requestId", requestId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear(); // 必须清理，避免线程复用污染
        }
    }
}
