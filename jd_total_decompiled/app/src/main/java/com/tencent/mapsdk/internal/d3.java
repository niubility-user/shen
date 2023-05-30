package com.tencent.mapsdk.internal;

import com.google.common.net.HttpHeaders;
import com.jdpay.net.http.HTTP;
import com.tencent.connect.common.Constants;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.NetUtil;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetHead;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface d3 extends j2.a {
    @NetRequest(constQuery = "appid=sdk&logid=launch_stat&channel=1&output=json&uuid=unknown", method = NetMethod.GET, path = "pingd", queryKeys = {"sessionid", "hm", "suid", "duid", "os", "psv", "ver", Constants.PARAM_PLATFORM_ID, "nt"}, userAgent = NetUtil.MAP_USER_AGENT)
    NetResponse launchStat(String str, String str2, String str3, String str4, int i2, String str5, String str6, String str7, String str8);

    @NetRequest(constQuery = "channel=1&output=json&uuid=unknown", head = @NetHead(keys = {HttpHeaders.CONTENT_TYPE}, values = {HTTP.CONTENT_TYPE_JSON}), method = NetMethod.POST, path = "ditusdk/monitor", queryKeys = {"key", "key2", "pid", "pid2", "hm", "suid", "os", "psv", "ver", Constants.PARAM_PLATFORM_ID, "nt"}, userAgent = NetUtil.MAP_USER_AGENT)
    NetResponse report(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, String str8, String str9, String str10, byte[] bArr);
}
