package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.exception.NetErrorException;
import java.net.URL;

/* loaded from: classes9.dex */
public abstract class UrlTileProvider implements TileProvider {
    private final int mHeight;
    private final int mWidth;

    public UrlTileProvider() {
        this(256, 256);
    }

    public UrlTileProvider(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileProvider
    public final Tile getTile(int i2, int i3, int i4) {
        URL tileUrl = getTileUrl(i2, i3, i4);
        Tile tile = TileProvider.NO_TILE;
        if (tileUrl == null) {
            return tile;
        }
        NetResponse requestTileData = requestTileData(tileUrl.toString());
        byte[] bArr = null;
        if (requestTileData != null) {
            if (requestTileData.available()) {
                bArr = requestTileData.data;
            } else if (requestTileData.exception instanceof NetErrorException) {
                return requestTileData.statusCode == 404 ? tile : new Tile(this.mWidth, this.mHeight, null);
            }
        }
        return (bArr == null || bArr.length == 0) ? tile : new Tile(this.mWidth, this.mHeight, bArr);
    }

    public abstract URL getTileUrl(int i2, int i3, int i4);

    public NetResponse requestTileData(String str) {
        try {
            return NetManager.getInstance().builder().url(str).doGet();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
