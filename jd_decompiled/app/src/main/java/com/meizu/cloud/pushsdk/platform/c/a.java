package com.meizu.cloud.pushsdk.platform.c;

import android.content.Context;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.e.b.b;
import com.meizu.cloud.pushsdk.e.b.c;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import java.io.File;
import java.util.LinkedHashMap;

/* loaded from: classes14.dex */
public class a {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f16000c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f16001e;

    /* renamed from: f  reason: collision with root package name */
    private String f16002f;

    /* renamed from: g  reason: collision with root package name */
    private String f16003g;

    /* renamed from: h  reason: collision with root package name */
    private String f16004h;

    /* renamed from: i  reason: collision with root package name */
    private String f16005i;

    /* renamed from: j  reason: collision with root package name */
    private String f16006j;

    /* renamed from: k  reason: collision with root package name */
    private String f16007k;

    /* renamed from: l  reason: collision with root package name */
    private String f16008l;

    public a(Context context) {
        this.a = "https://api-push.meizu.com/garcia/api/client/";
        this.b = this.a + "message/registerPush";
        this.f16000c = this.a + "message/unRegisterPush";
        String str = this.a + "advance/unRegisterPush";
        this.d = this.a + "message/getRegisterSwitch";
        this.f16001e = this.a + "message/changeRegisterSwitch";
        this.f16002f = this.a + "message/changeAllSwitch";
        this.f16003g = this.a + "message/subscribeTags";
        this.f16004h = this.a + "message/unSubscribeTags";
        this.f16005i = this.a + "message/unSubAllTags";
        this.f16006j = this.a + "message/getSubTags";
        this.f16007k = this.a + "message/subscribeAlias";
        this.f16008l = this.a + "message/unSubscribeAlias";
        String str2 = this.a + "message/getSubAlias";
        String str3 = this.a + "advance/changeRegisterSwitch";
        com.meizu.cloud.pushsdk.e.a.c();
        if (MzSystemUtils.isOverseas()) {
            this.a = "https://api-push.in.meizu.com/garcia/api/client/";
            this.b = this.a + "message/registerPush";
            this.f16000c = this.a + "message/unRegisterPush";
            String str4 = this.a + "advance/unRegisterPush";
            this.d = this.a + "message/getRegisterSwitch";
            this.f16001e = this.a + "message/changeRegisterSwitch";
            this.f16002f = this.a + "message/changeAllSwitch";
            this.f16003g = this.a + "message/subscribeTags";
            this.f16004h = this.a + "message/unSubscribeTags";
            this.f16005i = this.a + "message/unSubAllTags";
            this.f16006j = this.a + "message/getSubTags";
            this.f16007k = this.a + "message/subscribeAlias";
            this.f16008l = this.a + "message/unSubscribeAlias";
            String str5 = this.a + "message/getSubAlias";
            String str6 = this.a + "advance/changeRegisterSwitch";
        }
    }

    public c a(String str, String str2, String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "checkPush post map " + linkedHashMap2);
        b.d b = com.meizu.cloud.pushsdk.e.a.b(this.d);
        b.b(linkedHashMap2);
        return b.c().k();
    }

    public c b(String str, String str2, String str3, int i2, boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put(NotificationMessageSummary.MSGTYPE, String.valueOf(i2));
        linkedHashMap.put("subSwitch", z ? "1" : "0");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", this.f16001e + " switchPush post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16001e);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c c(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        if (TextUtils.isEmpty(str4)) {
            linkedHashMap.put("deviceId", str3);
        } else {
            linkedHashMap.put("fdId", str4);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "register post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.b);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c<String> d(String str, String str2, String str3, String str4, File file) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("msgId", str);
        if (TextUtils.isEmpty(str3)) {
            linkedHashMap.put("deviceId", str2);
        } else {
            linkedHashMap.put("fdId", str3);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, "4a2ca769d79f4856bb3bd982d30de790"));
        if (!TextUtils.isEmpty(str4)) {
            linkedHashMap2.put("errorMsg", str4);
        }
        DebugLogger.i("PushAPI", "uploadLogFile post map " + linkedHashMap2);
        b.e e2 = com.meizu.cloud.pushsdk.e.a.e("https://api-push.meizu.com/garcia/api/client/log/upload");
        e2.b(linkedHashMap2);
        e2.a("logFile", file);
        return e2.c().k();
    }

    public c e(String str, String str2, String str3, boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put("subSwitch", z ? "1" : "0");
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", this.f16002f + " switchPush post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16002f);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c f(String str, String str2, String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "checkPush post map " + linkedHashMap2);
        b.d b = com.meizu.cloud.pushsdk.e.a.b(this.f16006j);
        b.b(linkedHashMap2);
        return b.c().k();
    }

    public c g(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PairKey.APP_KEY, str2);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put(PushConstants.SUB_ALIAS_STATUS_NAME, str4);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "subScribeTags post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16007k);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c h(String str, String str2, String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "subScribeAllTags post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16005i);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c i(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put("tags", str4);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "subScribeTags post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16003g);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c j(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        if (TextUtils.isEmpty(str4)) {
            linkedHashMap.put("deviceId", str3);
        } else {
            linkedHashMap.put("fdId", str4);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "unregister post map " + linkedHashMap2);
        b.d b = com.meizu.cloud.pushsdk.e.a.b(this.f16000c);
        b.b(linkedHashMap2);
        return b.c().k();
    }

    public c k(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put(PushConstants.SUB_ALIAS_STATUS_NAME, str4);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "subScribeTags post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16008l);
        d.b(linkedHashMap2);
        return d.c().k();
    }

    public c l(String str, String str2, String str3, String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("appId", str);
        linkedHashMap.put(PushConstants.KEY_PUSH_ID, str3);
        linkedHashMap.put("tags", str4);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put("sign", com.meizu.cloud.pushsdk.platform.b.b(linkedHashMap, str2));
        DebugLogger.i("PushAPI", "subScribeTags post map " + linkedHashMap2);
        b.f d = com.meizu.cloud.pushsdk.e.a.d(this.f16004h);
        d.b(linkedHashMap2);
        return d.c().k();
    }
}
