package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.jdma.minterface.BaseEvent;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;

/* loaded from: classes9.dex */
public class di extends JsonComposer {
    @Json(name = "name")
    private String a;
    @Json(name = DeeplinkProductDetailHelper.LAYER_STYLE)
    private int b;
    @Json(name = BaseEvent.SCENE)

    /* renamed from: c  reason: collision with root package name */
    private int f16431c;
    @Json(name = "version")
    private int d;
    @Json(name = "url")

    /* renamed from: e  reason: collision with root package name */
    private String f16432e;
    @Json(name = "range")

    /* renamed from: f  reason: collision with root package name */
    private int[] f16433f;

    private int a(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 * i3 < 0 ? i4 + i3 : i4;
    }

    public String a() {
        return this.a;
    }

    public String a(int i2, int i3, int i4, String str) {
        String str2 = this.f16432e;
        int[] b = b();
        return str2.replaceFirst("\\{range\\}", b.length == 0 ? "" : Integer.toString(a(i2 + i3, b.length))).replaceFirst("\\{z\\}", Integer.toString(i4)).replaceFirst("\\{x\\}", Integer.toString(i2)).replaceFirst("\\{y\\}", Integer.toString(i3)).replaceFirst("\\{style\\}", Integer.toString(d())).replaceFirst("\\{scene\\}", Integer.toString(c())).replaceFirst("\\{version\\}", Integer.toString(f())).replaceFirst("\\{ch\\}", str);
    }

    public void a(int i2) {
        this.f16431c = i2;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(int[] iArr) {
        this.f16433f = iArr;
    }

    public void b(int i2) {
        this.b = i2;
    }

    public void b(String str) {
        this.f16432e = str;
    }

    public int[] b() {
        return this.f16433f;
    }

    public int c() {
        return this.f16431c;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public int d() {
        return this.b;
    }

    public String e() {
        return this.f16432e;
    }

    public int f() {
        return this.d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WorldMapTileSrc{");
        stringBuffer.append("mName='");
        stringBuffer.append(this.a);
        stringBuffer.append('\'');
        stringBuffer.append(", mStyle=");
        stringBuffer.append(this.b);
        stringBuffer.append(", mScene=");
        stringBuffer.append(this.f16431c);
        stringBuffer.append(", mVersion=");
        stringBuffer.append(this.d);
        stringBuffer.append(", mUrl='");
        stringBuffer.append(this.f16432e);
        stringBuffer.append('\'');
        stringBuffer.append(", mRanges=");
        if (this.f16433f == null) {
            stringBuffer.append(DYConstants.DY_NULL_STR);
        } else {
            stringBuffer.append('[');
            int i2 = 0;
            while (i2 < this.f16433f.length) {
                stringBuffer.append(i2 == 0 ? "" : ", ");
                stringBuffer.append(this.f16433f[i2]);
                i2++;
            }
            stringBuffer.append(']');
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
