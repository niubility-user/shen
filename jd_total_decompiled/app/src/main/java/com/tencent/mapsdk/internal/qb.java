package com.tencent.mapsdk.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.processor.RequestProcessor;
import com.tencent.map.tools.net.processor.ResponseProcessor;
import com.tencent.mapsdk.shell.events.NetFlowEventModel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class qb implements RequestProcessor, ResponseProcessor {
    private static final String d = "NetFlow";

    /* renamed from: e  reason: collision with root package name */
    private static final boolean f17047e = false;
    private HashMap<String, String> a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    public NetFlowEventModel f17048c;

    public qb() {
        this.a = new HashMap<>();
        this.b = false;
    }

    public qb(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        this.a = hashMap2;
        this.b = false;
        hashMap2.putAll(hashMap);
    }

    public boolean a(String str) {
        Uri parse;
        String scheme;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (scheme = parse.getScheme()) == null || !scheme.startsWith("http")) {
            return false;
        }
        String str2 = parse.getHost() + parse.getPath();
        if (!this.a.containsKey(str2)) {
            for (String str3 : this.a.keySet()) {
                if (str2.contains(str3)) {
                    this.f17048c.bizType = this.a.get(str3);
                }
            }
            return this.b;
        }
        this.f17048c.bizType = this.a.get(str2);
        this.b = true;
        return this.b;
    }

    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        NetFlowEventModel netFlowEventModel = new NetFlowEventModel();
        this.f17048c = netFlowEventModel;
        netFlowEventModel.url = netRequest.url;
        netFlowEventModel.uploadFlow = r1.getBytes().length;
        byte[] bArr = netRequest.postData;
        if (bArr != null) {
            NetFlowEventModel netFlowEventModel2 = this.f17048c;
            double d2 = netFlowEventModel2.uploadFlow;
            double length = bArr.length;
            Double.isNaN(length);
            netFlowEventModel2.uploadFlow = d2 + length;
        }
        for (Map.Entry<String, String> entry : netRequest.mapHeaders.entrySet()) {
            NetFlowEventModel netFlowEventModel3 = this.f17048c;
            double d3 = netFlowEventModel3.uploadFlow;
            double length2 = entry.getKey().getBytes().length + entry.getValue().getBytes().length;
            Double.isNaN(length2);
            netFlowEventModel3.uploadFlow = d3 + length2;
        }
        NetFlowEventModel netFlowEventModel4 = this.f17048c;
        double d4 = netFlowEventModel4.uploadFlow / 1000.0d;
        netFlowEventModel4.uploadFlow = d4;
        double round = Math.round(d4 * 1000.0d);
        Double.isNaN(round);
        netFlowEventModel4.uploadFlow = round / 1000.0d;
        this.f17048c.uploadTime = System.currentTimeMillis();
        if (a(netRequest.url)) {
            return;
        }
        this.f17048c.bizType = "";
    }

    public void onResponse(NetResponse netResponse) {
        int length;
        if (netResponse.available()) {
            NetFlowEventModel netFlowEventModel = this.f17048c;
            netFlowEventModel.errorCode = netResponse.statusCode;
            if (netResponse.errorCode != 0) {
                byte[] bArr = netResponse.errorData;
                if (bArr != null) {
                    length = bArr.length;
                    netFlowEventModel.downloadFlow = length;
                }
                netFlowEventModel.downloadFlow = -1.0d;
            } else {
                byte[] bArr2 = netResponse.data;
                if (bArr2 != null) {
                    length = bArr2.length;
                    netFlowEventModel.downloadFlow = length;
                }
                netFlowEventModel.downloadFlow = -1.0d;
            }
            double d2 = netFlowEventModel.downloadFlow / 1000.0d;
            netFlowEventModel.downloadFlow = d2;
            double round = Math.round(d2 * 1000.0d);
            Double.isNaN(round);
            netFlowEventModel.downloadFlow = round / 1000.0d;
        } else {
            this.f17048c.errorCode = -100;
        }
        this.f17048c.downloadTime = System.currentTimeMillis();
    }
}
