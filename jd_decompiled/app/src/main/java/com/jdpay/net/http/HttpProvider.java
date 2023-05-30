package com.jdpay.net.http;

import com.jdpay.lib.converter.Converter;
import com.jdpay.net.Provider;
import com.jdpay.net.http.HttpRequest;
import com.jdpay.net.http.HttpRequest.Builder;
import com.jdpay.net.http.HttpResult;

/* loaded from: classes18.dex */
public interface HttpProvider<REQUEST extends HttpRequest, CONVERTER extends Converter<HttpResponse, ?>, BUILDER extends HttpRequest.Builder<REQUEST, CONVERTER>, RESULT extends HttpResult> extends Provider<REQUEST, CONVERTER, HttpResponse<REQUEST>, BUILDER, RESULT> {
}
