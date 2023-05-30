package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.engine.jni.models.IconImageInfo;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes9.dex */
public class aj implements yd {

    /* renamed from: c  reason: collision with root package name */
    private Context f16255c;
    private final float d;

    /* renamed from: e  reason: collision with root package name */
    private String f16256e;

    /* renamed from: f  reason: collision with root package name */
    private String f16257f;

    public aj(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        this.f16255c = applicationContext;
        this.d = f7.d(applicationContext);
        this.f16256e = str;
    }

    private Bitmap a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream c2 = ga.c(lc.b(this.f16255c).c(this.f16256e) + str);
        if (c2 == null) {
            c2 = ga.c(lc.b(this.f16255c).a(this.f16256e) + str);
        }
        if (c2 == null) {
            c2 = ga.c(lc.b(this.f16255c).a() + str);
        }
        if (c2 == null && this.f16257f != null) {
            c2 = ga.a(new File(this.f16257f, str));
        }
        if (c2 == null) {
            if (ic.a() != null) {
                c2 = ic.a(this.f16255c, ic.a() + str);
            } else if (ic.b() != null) {
                c2 = ga.c(ic.b() + str);
            }
        }
        if (c2 == null) {
            c2 = ic.b(this.f16255c, str);
        }
        if (c2 == null) {
            c2 = ic.a(this.f16255c, str);
        }
        if (c2 == null) {
            return null;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(c2);
        ga.a((Closeable) c2);
        return decodeStream;
    }

    @Override // com.tencent.mapsdk.internal.yd
    public String a(GeoPoint geoPoint) {
        return null;
    }

    @Override // com.tencent.mapsdk.internal.yd
    public void a(String str, IconImageInfo iconImageInfo) {
        Bitmap a = pc.a(str);
        if (a == null) {
            a = a7.f16233e.a(str);
        }
        iconImageInfo.bitmap = a;
        if (str.endsWith(j4.s) || str.contains("@2x")) {
            iconImageInfo.scale = 2.0f;
        } else {
            iconImageInfo.scale = (str.endsWith("@3x.png") || str.contains("@3x")) ? 3.0f : this.d;
        }
        if (this.f16255c != null && a == null) {
            try {
                if (str.startsWith("poi_icon") || str.startsWith(j4.r)) {
                    a = a(e7.g(str) + j4.s);
                }
                if (a != null) {
                    iconImageInfo.bitmap = a;
                    iconImageInfo.scale = 2.0f;
                    return;
                }
                iconImageInfo.bitmap = a(str);
                if (!str.equals(yd.a) && !str.equals(yd.b)) {
                    iconImageInfo.scale = 1.0f;
                    return;
                }
                iconImageInfo.scale = this.d;
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.yd
    public void setOptionalResourcePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("../")) {
            str = str.replaceAll("\\.\\./", "");
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f16257f = str;
    }
}
