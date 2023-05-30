package com.tencent.mapsdk.internal;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.tencent.map.sdk.comps.offlinemap.OfflineItem;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.io.File;

/* loaded from: classes9.dex */
public class a2 extends JsonComposer {
    @Json(ignore = true)
    public String a;
    @Json(name = "md5")
    public String b;
    @Json(name = "pinyin")

    /* renamed from: c  reason: collision with root package name */
    public String f16220c;
    @Json(name = ApkDownloadTable.FIELD_SIZE)
    public int d;
    @Json(name = "ver")

    /* renamed from: e  reason: collision with root package name */
    public int f16221e;

    public String a() {
        return this.f16220c + ".dat";
    }

    public boolean a(OfflineItem offlineItem) {
        return offlineItem.getPinyin().equals(this.f16220c);
    }

    public boolean a(hc hcVar) {
        if (hcVar != null) {
            String d = hcVar.d(this.f16220c + "-md5");
            StringBuilder sb = new StringBuilder();
            sb.append(this.f16220c);
            sb.append("-version");
            return (this.f16221e == hcVar.a(sb.toString(), -1) && (d == null || d.equals(this.b))) ? false : true;
        }
        return false;
    }

    public String b() {
        return this.a + File.separator + this.f16220c + this.f16221e + ".zip";
    }

    public void b(hc hcVar) {
        if (hcVar != null) {
            hcVar.b(this.f16220c + "-md5", this.b);
            hcVar.b(this.f16220c + "-version", this.f16221e);
        }
    }

    public String c() {
        return this.f16220c + ".zip";
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("OfflineMapConfigCity{");
        stringBuffer.append("url='");
        stringBuffer.append(this.a);
        stringBuffer.append('\'');
        stringBuffer.append(", md5='");
        stringBuffer.append(this.b);
        stringBuffer.append('\'');
        stringBuffer.append(", pinyin='");
        stringBuffer.append(this.f16220c);
        stringBuffer.append('\'');
        stringBuffer.append(", size=");
        stringBuffer.append(this.d);
        stringBuffer.append(", version=");
        stringBuffer.append(this.f16221e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
