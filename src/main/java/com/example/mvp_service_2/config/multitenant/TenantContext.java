package com.example.mvp_service_2.config.multitenant;

// TenantContext.java
public final class TenantContext {
    private static final ThreadLocal<String> tenant = new InheritableThreadLocal<>();
    public static void setTenant(String t) { tenant.set(t); }
    public static String getTenant() { return tenant.get(); }
    public static void clear() { tenant.remove(); }
}
