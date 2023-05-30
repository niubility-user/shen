package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetResponse;
import g.i.a.a.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class v3<IN extends g.i.a.a.a, OUT extends g.i.a.a.a> extends t3 {
    private Class<IN> a;
    private Class<OUT> b;

    /* renamed from: c */
    private g.i.a.a.a f17376c;

    /* loaded from: classes9.dex */
    public static class a<OUT extends g.i.a.a.a> extends NetResponse {
        private OUT a;

        public a(NetResponse netResponse, Class<OUT> cls) {
            clone(netResponse);
            byte[] bArr = netResponse.data;
            if (bArr != null) {
                m mVar = new m(bArr);
                OUT out = (OUT) d7.a(cls, new Object[0]);
                this.a = out;
                if (out != null) {
                    out.readFrom(mVar);
                }
            }
            ma.c(la.f16820g, "[JCE-RESP]:" + this.a);
        }

        public OUT a() {
            return this.a;
        }

        @Override // com.tencent.map.tools.net.NetResponse
        public boolean available() {
            return super.available() && this.a != null;
        }
    }

    public v3(Class<IN> cls, Class<OUT> cls2) {
        this.a = cls;
        this.b = cls2;
    }

    @Override // com.tencent.mapsdk.internal.t3, com.tencent.mapsdk.internal.x3
    public Object[] a(int[] iArr, Object[] objArr) {
        if (objArr != null && iArr != null && iArr.length > 0 && objArr.length > 0) {
            List asList = Arrays.asList(objArr);
            int i2 = iArr[0];
            int i3 = iArr.length == 1 ? iArr[0] : iArr[1];
            if (objArr.length - 1 >= i3 && i2 >= 0) {
                g.i.a.a.a aVar = (g.i.a.a.a) d7.a(this.a, Arrays.copyOfRange(objArr, i2, i3 + 1));
                this.f17376c = aVar;
                byte[] bArr = new byte[0];
                if (aVar != null) {
                    bArr = aVar.toByteArray();
                }
                ArrayList arrayList = new ArrayList();
                for (int i4 = 0; i4 < asList.size(); i4++) {
                    if (i4 < i2 || i4 > i3) {
                        arrayList.add(asList.get(i4));
                    } else if (i4 == i3) {
                        arrayList.add(bArr);
                    }
                }
                return arrayList.toArray();
            }
        }
        return super.a(iArr, objArr);
    }

    @Override // com.tencent.mapsdk.internal.t3, com.tencent.mapsdk.internal.x3
    /* renamed from: b */
    public a<OUT> a(NetResponse netResponse) {
        return new a<>(netResponse, this.b);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("JceResolver{");
        stringBuffer.append("inJce=");
        stringBuffer.append(this.f17376c);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
