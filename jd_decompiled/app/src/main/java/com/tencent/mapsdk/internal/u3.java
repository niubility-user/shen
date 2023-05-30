package com.tencent.mapsdk.internal;

import com.facebook.cache.disk.DefaultDiskStorage;
import com.tencent.map.tools.net.NetResponse;
import java.io.File;

/* loaded from: classes9.dex */
public class u3 extends t3 {
    private String a;
    private String b;

    /* loaded from: classes9.dex */
    public static class a extends NetResponse {
        private File a;

        public a(NetResponse netResponse, String str, String str2) {
            clone(netResponse);
            if (netResponse.available()) {
                this.a = new File(str, str2);
                File file = new File(str, str2 + DefaultDiskStorage.FileType.TEMP);
                fa.d(file);
                if (fa.b(file) && fa.b(file, netResponse.data) && fa.b(file, this.a)) {
                    fa.d(file);
                }
            }
        }

        public File a() {
            return this.a;
        }

        @Override // com.tencent.map.tools.net.NetResponse
        public boolean available() {
            File file;
            return super.available() && (file = this.a) != null && file.exists();
        }
    }

    public u3(String str) {
        this.a = str;
    }

    @Override // com.tencent.mapsdk.internal.t3, com.tencent.mapsdk.internal.x3
    public Object[] a(int[] iArr, Object[] objArr) {
        if (objArr != null && iArr != null && iArr.length == 1 && objArr.length > 0) {
            Object obj = objArr[iArr[0]];
            if (obj instanceof String) {
                this.b = String.valueOf(obj);
            }
        }
        return super.a(iArr, objArr);
    }

    @Override // com.tencent.mapsdk.internal.t3, com.tencent.mapsdk.internal.x3
    /* renamed from: b */
    public a a(NetResponse netResponse) {
        return new a(netResponse, this.b, this.a);
    }
}
