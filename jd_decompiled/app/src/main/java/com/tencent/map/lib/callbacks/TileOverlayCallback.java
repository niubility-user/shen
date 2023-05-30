package com.tencent.map.lib.callbacks;

import android.graphics.Bitmap;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes9.dex */
public interface TileOverlayCallback {
    Bitmap onLoadTile(int i2, int i3, int i4, byte[] bArr);

    void onLoadTileFinish(int i2, int i3, int i4);

    void onWriteTile(int i2, int i3, int i4, String str, byte[] bArr);
}
