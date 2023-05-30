package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public class wh extends OverSeaTileProvider {
    private di a;
    private v6 b;

    /* renamed from: c  reason: collision with root package name */
    private Language f17438c;
    private OverSeaSource d;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            OverSeaSource.values();
            int[] iArr = new int[2];
            a = iArr;
            try {
                OverSeaSource overSeaSource = OverSeaSource.DEFAULT;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                OverSeaSource overSeaSource2 = OverSeaSource.SPARE;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public wh(@NonNull di diVar, OverSeaSource overSeaSource, v6 v6Var) {
        super(diVar.a(), diVar.f());
        this.f17438c = Language.zh;
        this.b = v6Var;
        this.d = overSeaSource;
        this.a = diVar;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider
    public Bitmap getLogo(boolean z) {
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider
    public URL getTileUrl(int i2, int i3, int i4) {
        String a2 = this.a.a(i2, i3, i4, this.f17438c.name());
        if (a2 != null) {
            ma.c(la.f16821h, "\u8bf7\u6c42\u6d77\u5916\u56fe\u74e6\u7247\uff1a" + a2);
            try {
                return new URL(a2);
            } catch (MalformedURLException e2) {
                ma.b(Log.getStackTraceString(e2));
            }
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider
    public boolean onLanguageChange(Language language) {
        this.f17438c = language;
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider
    public NetResponse requestTileData(String str) {
        NetResponse doGet = NetManager.getInstance().builder().url(str).doGet();
        byte[] bArr = doGet != null ? doGet.data : null;
        if (bArr != null && bArr.length != 0 && this.b != null) {
            int ordinal = this.d.ordinal();
            if (ordinal == 0) {
                this.b.n().b(1);
            } else if (ordinal == 1) {
                this.b.n().a(1);
            }
        }
        return doGet;
    }
}
