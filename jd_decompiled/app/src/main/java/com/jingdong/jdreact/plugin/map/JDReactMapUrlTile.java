package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes13.dex */
public class JDReactMapUrlTile extends JDReactMapFeature {
    private TileOverlay tileOverlay;
    private TileOverlayOptions tileOverlayOptions;
    private JDReactMapUrlTileProvider tileProvider;
    private String urlTemplate;
    private int zIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class JDReactMapUrlTileProvider extends UrlTileProvider {
        private int tileHeight;
        private int tileWidth;
        private String urlTemplate;

        public JDReactMapUrlTileProvider(int i2, int i3, String str) {
            this.tileWidth = 0;
            this.tileHeight = 0;
            this.urlTemplate = str;
            this.tileWidth = i2;
            this.tileHeight = i3;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.model.UrlTileProvider
        public synchronized URL getTileUrl(int i2, int i3, int i4) {
            try {
            } catch (MalformedURLException e2) {
                throw new AssertionError(e2);
            }
            return new URL(this.urlTemplate.replace("{x}", Integer.toString(i2)).replace("{y}", Integer.toString(i3)).replace("{z}", Integer.toString(i4)));
        }

        public void setUrlTemplate(String str) {
            this.urlTemplate = str;
        }
    }

    public JDReactMapUrlTile(Context context) {
        super(context);
    }

    private TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        JDReactMapUrlTileProvider jDReactMapUrlTileProvider = new JDReactMapUrlTileProvider(256, 256, this.urlTemplate);
        this.tileProvider = jDReactMapUrlTileProvider;
        tileOverlayOptions.tileProvider(jDReactMapUrlTileProvider);
        return tileOverlayOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void addToMap(TencentMap tencentMap) {
        this.tileOverlay = tencentMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public Object getFeature() {
        return this.tileOverlay;
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.tileOverlayOptions == null) {
            this.tileOverlayOptions = createTileOverlayOptions();
        }
        return this.tileOverlayOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void removeFromMap(TencentMap tencentMap) {
        this.tileOverlay.remove();
    }

    public void setUrlTemplate(String str) {
        this.urlTemplate = str;
        JDReactMapUrlTileProvider jDReactMapUrlTileProvider = this.tileProvider;
        if (jDReactMapUrlTileProvider != null) {
            jDReactMapUrlTileProvider.setUrlTemplate(str);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }
}
