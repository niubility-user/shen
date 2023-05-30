package com.jingdong.common.jdreactFramework.views.videoview;

import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import java.util.Map;
import tv.danmaku.ijk.media.widget.JDPlayerView;

/* loaded from: classes5.dex */
public class JDVideoPlayerManager extends SimpleViewManager<FrameLayout> {
    private static boolean ISUSEV1 = true;
    private JDCustomIjkPlayerManagerV2 ijkNewPlayerManager;
    private JDCustomIjkPlayerManager ijkPlayerManager;

    public JDVideoPlayerManager() {
        boolean equals = "1".equals(JDMobileConfig.getInstance().getConfig("JDReact", "JDReactPlayerV1", "switch", "0"));
        ISUSEV1 = equals;
        if (equals) {
            this.ijkPlayerManager = new JDCustomIjkPlayerManager();
        } else {
            this.ijkNewPlayerManager = new JDCustomIjkPlayerManagerV2();
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        if (ISUSEV1) {
            return this.ijkPlayerManager.getCommandsMap();
        }
        return this.ijkNewPlayerManager.getCommandsMap();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        if (ISUSEV1) {
            return this.ijkPlayerManager.getExportedCustomDirectEventTypeConstants();
        }
        return this.ijkNewPlayerManager.getExportedCustomDirectEventTypeConstants();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDCustomIjkPlayer";
    }

    @ReactProp(name = "source")
    public void setSource(FrameLayout frameLayout, @Nullable ReadableMap readableMap) {
        if (ISUSEV1) {
            this.ijkPlayerManager.setSource((CustomIjkPlayer) frameLayout, readableMap);
        } else {
            this.ijkNewPlayerManager.setSource((JDPlayerView) frameLayout, readableMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public FrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        if (ISUSEV1) {
            return this.ijkPlayerManager.createViewInstance(themedReactContext);
        }
        return this.ijkNewPlayerManager.createViewInstance(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(FrameLayout frameLayout) {
        if (ISUSEV1) {
            this.ijkPlayerManager.onDropViewInstance((CustomIjkPlayer) frameLayout);
        } else {
            this.ijkNewPlayerManager.onDropViewInstance((JDPlayerView) frameLayout);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(FrameLayout frameLayout, int i2, @Nullable ReadableArray readableArray) {
        if (ISUSEV1) {
            this.ijkPlayerManager.receiveCommand((CustomIjkPlayer) frameLayout, i2, readableArray);
        } else {
            this.ijkNewPlayerManager.receiveCommand((JDPlayerView) frameLayout, i2, readableArray);
        }
    }
}
