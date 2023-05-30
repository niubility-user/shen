package com.jdpay.membercode;

import com.jdpay.membercode.network.EnterRequestBean;
import com.jdpay.membercode.network.OpenRequestBean;
import com.jdpay.membercode.network.PayResultRequestBean;
import com.jdpay.membercode.network.QueryVerifyPayWayRequestBean;
import com.jdpay.membercode.network.RefreshCodeRequestBean;
import com.jdpay.membercode.network.SignAgainRequestBean;
import com.jdpay.membercode.network.StopRequestBean;
import com.jdpay.net.http.HttpRequestAdapter;
import com.jdpay.net.http.annotation.Entry;
import com.jdpay.net.http.annotation.HttpService;

/* loaded from: classes18.dex */
public interface a {
    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/signAgreement")
    HttpRequestAdapter a(@Entry(contentType = "text/plain") SignAgainRequestBean signAgainRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/visitAccess")
    HttpRequestAdapter b(@Entry(contentType = "text/plain") EnterRequestBean enterRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/open")
    HttpRequestAdapter c(@Entry(contentType = "text/plain") OpenRequestBean openRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/refreshCode")
    HttpRequestAdapter d(@Entry(contentType = "text/plain") RefreshCodeRequestBean refreshCodeRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/queryVerifyPayWay")
    HttpRequestAdapter e(@Entry(contentType = "text/plain") QueryVerifyPayWayRequestBean queryVerifyPayWayRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/queryPayResult")
    HttpRequestAdapter f(@Entry(contentType = "text/plain") PayResultRequestBean payResultRequestBean);

    @HttpService(method = "POST", requestConverter = 100, responseConverter = 1000, url = "https://mgate.jd.com/customer/aks/stop")
    HttpRequestAdapter g(@Entry(contentType = "text/plain") StopRequestBean stopRequestBean);
}
