package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetResponse;
import java.io.UnsupportedEncodingException;

/* loaded from: classes9.dex */
public class w3<OUT extends JsonComposer> extends t3 {
    private Class<OUT> a;

    /* loaded from: classes9.dex */
    public static class a<OUT extends JsonComposer> extends NetResponse {
        private String a;
        private OUT b;

        public a(NetResponse netResponse) {
            clone(netResponse);
            if (netResponse.available()) {
                try {
                    this.a = new String(netResponse.data, netResponse.charset);
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }

        public a(NetResponse netResponse, Class<OUT> cls) {
            clone(netResponse);
            if (netResponse.available()) {
                try {
                    String str = new String(netResponse.data, netResponse.charset);
                    this.a = str;
                    this.b = (OUT) JsonUtils.parseToModel(str, cls, new Object[0]);
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }

        public String a() {
            return this.a;
        }

        @Override // com.tencent.map.tools.net.NetResponse
        public boolean available() {
            String str;
            return (!super.available() || (str = this.a) == null || TextUtils.isEmpty(str)) ? false : true;
        }

        public OUT b() {
            return this.b;
        }
    }

    public w3(Class<OUT> cls) {
        this.a = cls;
    }

    @Override // com.tencent.mapsdk.internal.t3, com.tencent.mapsdk.internal.x3
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public a<OUT> a(NetResponse netResponse) {
        return new a<>(netResponse, this.a);
    }
}
