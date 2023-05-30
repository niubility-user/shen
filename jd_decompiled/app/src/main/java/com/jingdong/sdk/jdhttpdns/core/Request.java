package com.jingdong.sdk.jdhttpdns.core;

import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.util.HashMap;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes7.dex */
public class Request {
    private ConnectHostType connectHostType;
    private String host;
    private InternalResolveListener internalResolveListener;
    private boolean isCdnParam;
    private boolean isPreload;
    private String lastRequestUrlStr;
    private String param;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public enum ConnectHostType {
        TYPE_IPV6,
        TYPE_IPV4,
        TYPE_DOMAIN
    }

    public Request(String str, InternalResolveListener internalResolveListener) {
        this.param = str;
        this.internalResolveListener = internalResolveListener;
        this.host = JDHttpDnsToolkit.getInstance().isThirdPartApp() ? HttpDnsConfig.DNS_DOMAIN_THIRD_PARTY : HttpDnsConfig.DNS_DOMAIN_1;
        if (TextUtils.equals(HttpDnsConfig.PREDOWNLOAD_PARAMS, str)) {
            this.isPreload = true;
        }
    }

    public ConnectHostType getConnectHostType(String str) {
        if (InetAddressUtils.isIPv6Address(str)) {
            return ConnectHostType.TYPE_IPV6;
        }
        if (InetAddressUtils.isIPv4Address(str)) {
            return ConnectHostType.TYPE_IPV4;
        }
        return ConnectHostType.TYPE_DOMAIN;
    }

    public HashMap<String, String> getHeader() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Connection", "close");
        hashMap.put("Charset", "UTF-8");
        ConnectHostType connectHostType = this.connectHostType;
        if (connectHostType == ConnectHostType.TYPE_IPV6 || connectHostType == ConnectHostType.TYPE_IPV4) {
            hashMap.put("host", this.host);
        }
        return hashMap;
    }

    public InternalResolveListener getInternalResolveListener() {
        return this.internalResolveListener;
    }

    public String getLastRequestUrlStr() {
        return this.lastRequestUrlStr;
    }

    public String getUrl() {
        String str;
        ConnectHostType connectHostType = this.connectHostType;
        String str2 = HttpDnsConfig.DNS_DOMAIN_1;
        if (connectHostType == null) {
            str2 = this.isCdnParam ? JDHttpDnsToolkit.getInstance().getRouteSelector().getTargetCdnVip() : JDHttpDnsToolkit.getInstance().getRouteSelector().getTargetVip();
            this.connectHostType = getConnectHostType(str2);
            DNSLog.d("\u9996\u6b21\u8bf7\u6c42\uff0c\u83b7\u53d6\u5230\u6709\u6548host : " + str2);
        } else if (connectHostType == ConnectHostType.TYPE_IPV6) {
            str2 = JDHttpDnsToolkit.getInstance().getRouteSelector().getIpv4();
            this.connectHostType = ConnectHostType.TYPE_IPV4;
            DNSLog.d("ipv6\u8bf7\u6c42\u5931\u8d25\uff0c\u964d\u7ea7\u5230host : " + str2);
        } else if (connectHostType == ConnectHostType.TYPE_IPV4) {
            this.connectHostType = ConnectHostType.TYPE_DOMAIN;
            DNSLog.d("ipv4\u8bf7\u6c42\u5931\u8d25\uff0c\u964d\u7ea7\u5230host : " + HttpDnsConfig.DNS_DOMAIN_1);
        } else {
            this.connectHostType = ConnectHostType.TYPE_DOMAIN;
            DNSLog.d("\u6700\u7ec8\u7684\u515c\u5e95\u57df\u540d : " + HttpDnsConfig.DNS_DOMAIN_1);
        }
        if (JDHttpDnsToolkit.getInstance().isThirdPartApp()) {
            str = "/d?dn=";
        } else {
            str = TextUtils.equals(HttpDnsConfig.PREDOWNLOAD_PARAMS, this.param) ? "/v6/b" : "/v6/d";
        }
        String url = getUrl("https://", str2, str, !JDHttpDnsToolkit.getInstance().isThirdPartApp() ? ParamHelper.getDnsQueryStr(this.param) : this.param);
        this.lastRequestUrlStr = url;
        return url;
    }

    public boolean isFinalDowngrade() {
        return this.connectHostType == ConnectHostType.TYPE_DOMAIN;
    }

    public boolean isPreload() {
        return this.isPreload;
    }

    public void setIsCdnParam(boolean z) {
        this.isCdnParam = z;
    }

    public static String getUrl(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (InetAddressUtils.isIPv6Address(str2)) {
            str2 = String.format("[%s]", str2);
        }
        stringBuffer.append(str);
        stringBuffer.append(str2);
        if (!JDHttpDnsToolkit.getInstance().isThirdPartApp()) {
            stringBuffer.append(str3);
            stringBuffer.append("?");
            stringBuffer.append(str4);
        } else {
            stringBuffer.append("/");
            stringBuffer.append(JDHttpDnsToolkit.getInstance().getAccountId());
            stringBuffer.append(str3);
            String valueOf = String.valueOf(System.currentTimeMillis());
            String HMACSHA256 = SignatureHelper.HMACSHA256((str4 + "-" + valueOf).getBytes(), JDHttpDnsToolkit.getInstance().getSecretKey().getBytes());
            stringBuffer.append(str4);
            stringBuffer.append("&m=hmac_sha256");
            stringBuffer.append("&t=" + valueOf);
            stringBuffer.append("&s=" + HMACSHA256);
        }
        return stringBuffer.toString();
    }
}
