package com.jingdong.common.jdreactFramework.views.channel;

import android.text.TextUtils;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.common.widget.custom.CustomChannelFollowView;
import com.jingdong.sdk.log.Log;

/* loaded from: classes5.dex */
public class ChannelFollowViewManager extends SimpleViewManager<CustomChannelFollowView> {
    private static final String TAG = "ChannelFollowViewManager";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ChannelFollowView";
    }

    @ReactProp(name = "buttonStyleType")
    public void setBtnStyle(CustomChannelFollowView customChannelFollowView, String str) {
        if (TextUtils.isEmpty(str) || customChannelFollowView == null) {
            return;
        }
        if ("0".equals(str)) {
            customChannelFollowView.changeIcon(true);
        } else if ("1".equals(str)) {
            customChannelFollowView.changeIcon(false);
        }
    }

    @ReactProp(name = "channelId")
    public void setChannelId(CustomChannelFollowView customChannelFollowView, String str) {
        if (TextUtils.isEmpty(str) || customChannelFollowView == null) {
            return;
        }
        customChannelFollowView.setChannelIdForRN(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CustomChannelFollowView createViewInstance(ThemedReactContext themedReactContext) {
        Log.d(TAG, "createViewInstance and customChannelFollowView");
        return new CustomChannelFollowView(themedReactContext);
    }
}
