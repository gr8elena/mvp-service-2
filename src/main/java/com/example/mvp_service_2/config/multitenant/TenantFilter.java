package com.example.mvp_service_2.config.multitenant;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component @Order(1)
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String tenant = httpReq.getHeader("X-Tenant");
        if (tenant == null || !tenant.matches("tenant1|tenant2")) {
            ((HttpServletResponse) res).sendError(400, "Invalid X-Tenant");
            return;
        }
        TenantContext.setTenant(tenant);
        try { chain.doFilter(req, res); }
        finally { TenantContext.clear(); }
    }
}