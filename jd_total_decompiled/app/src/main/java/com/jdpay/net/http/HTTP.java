package com.jdpay.net.http;

/* loaded from: classes18.dex */
public interface HTTP {
    public static final String CONTENT_TYPE_FORM_DATA = "multipart/form-data";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final int FORMAT_JSON = 0;
    public static final int FORMAT_PROTOCOLBUF = 2;
    public static final int FORMAT_XML = 1;
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final int NO_CONVERTER = -1;
    public static final int PROVIDER_OKHTTP = 1;
}
