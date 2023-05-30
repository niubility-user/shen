package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.map.tools.json.JsonComposer;
import com.tencent.map.tools.json.annotation.Json;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class d4 extends JsonComposer {
    @Json(name = "layerid")
    private String a;
    @Json(name = "type")
    private int b;
    @Json(name = "data_ver")

    /* renamed from: c  reason: collision with root package name */
    private String f16393c;
    @Json(name = "local_data_ver")
    private int d;
    @Json(name = "cfg_ver")

    /* renamed from: e  reason: collision with root package name */
    private String f16394e;
    @Json(name = "local_cfg_ver")

    /* renamed from: f  reason: collision with root package name */
    private int f16395f;
    @Json(name = "params")

    /* renamed from: g  reason: collision with root package name */
    private String[] f16396g;
    @Json(name = "url")

    /* renamed from: h  reason: collision with root package name */
    private String f16397h;
    @Json(ignore = true)

    /* renamed from: i  reason: collision with root package name */
    private String f16398i;

    private boolean f() {
        if (this.f16393c.equals(this.d + "")) {
            if (this.f16394e.equals(this.f16395f + "")) {
                return false;
            }
        }
        return true;
    }

    public String a() {
        if (f()) {
            this.f16394e = this.f16395f + "";
            this.f16393c = this.d + "";
            this.f16398i = null;
        }
        if (this.f16396g != null && this.f16398i == null && !TextUtils.isEmpty(this.f16397h)) {
            this.f16398i = this.f16397h;
            for (String str : this.f16396g) {
                try {
                    Object fieldValueByJsonName = getFieldValueByJsonName(str);
                    if (fieldValueByJsonName instanceof String) {
                        this.f16398i = this.f16398i.replaceAll("\\{" + str + "\\}", (String) fieldValueByJsonName);
                    }
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return this.f16398i;
    }

    public void a(int i2) {
        if (i2 != this.d) {
            this.f16398i = null;
        }
        this.d = i2;
    }

    public String b() {
        return this.d + "";
    }

    public void b(int i2) {
        if (i2 != this.f16395f) {
            this.f16398i = null;
        }
        this.f16395f = i2;
    }

    public String c() {
        return this.a;
    }

    public z3 d() {
        return z3.b(this.b);
    }

    public String e() {
        return this.f16395f + "";
    }

    public String toString() {
        return "DataLayer{layerId='" + this.a + "', layerType='" + this.b + "', remoteDataVersion='" + this.f16393c + "', dataVersion=" + this.d + ", remoteStyleVersion='" + this.f16394e + "', styleVersion=" + this.f16395f + ", params=" + Arrays.toString(this.f16396g) + ", dataUrl='" + this.f16397h + "', decodeDataUrl='" + this.f16398i + "'}";
    }
}
