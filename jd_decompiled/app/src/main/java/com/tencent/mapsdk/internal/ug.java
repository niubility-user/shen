package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public class ug extends UrlTileProvider {
    private static final int b = 256;

    /* renamed from: c  reason: collision with root package name */
    private static int f17342c;
    private TileOverlayOptions a;

    public ug(TileOverlayOptions tileOverlayOptions) {
        super(256, 256);
        this.a = tileOverlayOptions;
        f17342c = sg.a();
        TileOverlayOptions tileOverlayOptions2 = this.a;
        if (tileOverlayOptions2 != null) {
            tileOverlayOptions2.versionInfo(a());
        }
    }

    private String a() {
        return Integer.toString(f17342c);
    }

    public void b() {
        f17342c = sg.a();
        TileOverlayOptions tileOverlayOptions = this.a;
        if (tileOverlayOptions != null) {
            tileOverlayOptions.versionInfo(a());
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider
    public URL getTileUrl(int i2, int i3, int i4) {
        double d = i3;
        Double.isNaN(d);
        String sketchTileUrl = ((c3) ((q3) l2.a(q3.class)).d()).sketchTileUrl(i2, (int) ((Math.pow(2.0d, i4) - 1.0d) - d), i4, f17342c);
        try {
            if (TextUtils.isEmpty(sketchTileUrl)) {
                return null;
            }
            return new URL(sketchTileUrl);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
