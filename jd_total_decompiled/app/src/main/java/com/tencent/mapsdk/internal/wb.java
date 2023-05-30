package com.tencent.mapsdk.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.map.tools.Util;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.processor.RequestProcessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class wb implements RequestProcessor {
    public static final /* synthetic */ boolean b = true;
    private final String a;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            NetMethod.values();
            int[] iArr = new int[4];
            a = iArr;
            try {
                NetMethod netMethod = NetMethod.GET;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                NetMethod netMethod2 = NetMethod.POST;
                iArr2[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private wb(String str) {
        this.a = str;
    }

    public static wb a(String str) {
        return new wb(str);
    }

    public void a(NetRequest netRequest, String str) {
        if (netRequest == null || TextUtils.isEmpty(str)) {
            return;
        }
        Uri parse = Uri.parse(netRequest.url);
        HashMap hashMap = new HashMap();
        int ordinal = netRequest.mNetMethod.ordinal();
        if (ordinal == 0) {
            byte[] bArr = netRequest.postData;
            if (bArr == null || bArr.length > 0) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(netRequest.postData));
                    JSONArray names = jSONObject.names();
                    for (int i2 = 0; i2 < names.length(); i2++) {
                        String optString = names.optString(i2);
                        hashMap.put(optString, jSONObject.opt(optString));
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (ordinal == 1) {
            String query = parse.getQuery();
            if (!TextUtils.isEmpty(query)) {
                if (!b && query == null) {
                    throw new AssertionError();
                }
                for (String str2 : query.split(ContainerUtils.FIELD_DELIMITER)) {
                    String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                    split[1] = str2.substring(split[0].length() + 1);
                    hashMap.put(split[0], split[1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(parse.getPath());
        sb.append("?");
        if (!hashMap.isEmpty()) {
            ArrayList arrayList = new ArrayList(hashMap.keySet());
            Collections.sort(arrayList);
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                String str3 = (String) arrayList.get(i3);
                Object obj = hashMap.get(str3);
                if (obj != null) {
                    int size = arrayList.size() - 1;
                    sb.append(str3);
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(obj.toString());
                    if (i3 != size) {
                        sb.append(ContainerUtils.FIELD_DELIMITER);
                    }
                }
            }
        }
        sb.append(str);
        netRequest.url = parse.buildUpon().appendQueryParameter("sig", Util.getMD5String(sb.toString())).build().toString();
    }

    @Override // com.tencent.map.tools.net.processor.RequestProcessor
    public void onRequest(NetRequest netRequest) {
        a(netRequest, this.a);
    }
}
