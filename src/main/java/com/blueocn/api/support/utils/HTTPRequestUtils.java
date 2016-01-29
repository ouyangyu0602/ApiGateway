package com.blueocn.api.support.utils;

import javax.servlet.http.HttpServletRequest;

import static com.blueocn.api.support.Constants.X_FORWARDED_FOR_HEADER;

/**
 * Title: HTTPRequestUtils
 * Description: Some handy methods for working with HTTP requests
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-28 16:11
 */
public enum HTTPRequestUtils {

    INSTANCE;

    /**
     * Get the IP address of client making the request.
     * <p/>
     * Uses the "x-forwarded-for" HTTP header if available, otherwise uses the remote
     * IP of requester.
     *
     * @param request <code>HttpServletRequest</code>
     * @return <code>String</code> IP address
     */
    public String getClientIP(HttpServletRequest request) {
        final String xForwardedFor = request.getHeader(X_FORWARDED_FOR_HEADER);
        String clientIP = null;
        if (xForwardedFor == null) {
            clientIP = request.getRemoteAddr();
        } else {
            clientIP = extractClientIpFromXForwardedFor(xForwardedFor);
        }
        return clientIP;
    }

    /**
     * Extract the client IP address from an x-forwarded-for header. Returns null if there is no x-forwarded-for header
     *
     * @param xForwardedFor a <code>String</code> value
     * @return a <code>String</code> value
     */
    public final String extractClientIpFromXForwardedFor(String xForwardedFor) {
        if (xForwardedFor != null) {
            String tokenize[] = xForwardedFor.trim().split(",");
            if (tokenize.length != 0) {
                return tokenize[0].trim();
            }
        }
        return null;
    }
}
